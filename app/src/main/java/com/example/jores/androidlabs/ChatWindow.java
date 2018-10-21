package com.example.jores.androidlabs;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;

public class ChatWindow extends Activity {

    String tag = "ChatWindow: ";

    ListView listView;
    EditText editText;
    Button sendBtn;
    private SQLiteDatabase database;
    protected String TABLE_NAME = "ChatHistory";
    public static String DATABASE_NAME = "Lab5";
    public static int VERSION_NUM =1;
    protected final static String KEY_ID = "key_id";
    protected final static String  KEY_MESSAGE = "key_msg";

    ArrayList<String> msgArrayList = new ArrayList<>();

    public void chatHistoryDatabase(){
        ChatDatabaseHelper chatDatabaseHelper =  new ChatDatabaseHelper(this);
        database = chatDatabaseHelper.getWritableDatabase();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);

        chatHistoryDatabase();

        String[] columns =new String[]{KEY_ID,KEY_MESSAGE};
        Cursor cursor = database.rawQuery("SELECT * FROM ChatHistory;", null);
        while(cursor.moveToNext()){
            String msg = cursor.getString(1);
            Log.i(tag,"MSG" +msg +" :  :");
            msgArrayList.add(msg);
            Log.i(tag,"SQL MESSASGE: "+msg);}
        Log.i(tag,"Cursor's column count="+cursor.getColumnCount());
        for(int i=0; i<cursor.getColumnCount();i++){Log.i(tag,"Column name at index " + i +": "+cursor.getColumnName(i));}

        listView = findViewById(R.id.listView);
        final ChatAdapter messageAdapter = new ChatAdapter(this);
        listView.setAdapter(messageAdapter);
        editText= findViewById((R.id.editText));




        sendBtn = findViewById(R.id.sendBtn);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String editTextMsg = editText.getText().toString();
                msgArrayList.add(editText.getText().toString());
                messageAdapter.notifyDataSetChanged();
                editText.setText("");
                ContentValues cv = new ContentValues();
                cv.put(KEY_MESSAGE, editTextMsg);
                database.insert(TABLE_NAME,null,cv);


            }
        });

        Log.i(tag, "In the onCreate() event");
    }

    public void onStart(){
        super.onStart();
        Log.i(tag, "In the onStart() event");

    }
    public void onRestart(){

        super.onRestart();
        Log.i(tag, "In the onRestart() event");
    }

    public void onResume(){
        super.onResume();
        Log.i(tag, "In the onResume() event");
    }
    public void onPause(){
        super.onPause();
        Log.i(tag, "In the onPause() event");
    }
    public void onStop(){
        super.onStop();
        Log.i(tag, "In the onStop() event");
    }
    public void onDestroy(){
        super.onDestroy();
        database.close();
        Log.i(tag, "In the onDestroy() event");
    }




    public class ChatAdapter extends ArrayAdapter<String> {

        public ChatAdapter(Context ctx){super(ctx,0);}

        @Override
        public int getCount() {
            return msgArrayList.size();
        }

        public String getItem(int position){
            return msgArrayList.get(position);
        }
        public View getView(int position, View convertView, ViewGroup parent){

            LayoutInflater inflater = ChatWindow.this.getLayoutInflater();

            View result = null;
            if(position %2==0){ result = inflater.inflate(R.layout.chat_row_incoming,null);}
            else{result = inflater.inflate(R.layout.chat_row_outgoing, null);}

            TextView message = (TextView)result.findViewById(R.id.message_text);
            message.setText(getItem(position));
            return result;
        }

        public long getItemId(int position){
            return new Long(position);
        }






    }
}
