package com.example.jores.androidlabs;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
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

    ArrayList<String> msgArrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);

        listView = findViewById(R.id.listView);
        final ChatAdapter messageAdapter = new ChatAdapter(this);
        listView.setAdapter(messageAdapter);
        editText= findViewById((R.id.editText));
        sendBtn = findViewById(R.id.sendBtn);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                msgArrayList.add(editText.getText().toString());
                messageAdapter.notifyDataSetChanged();
                editText.setText("");

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
        Log.i(tag, "In the onDestroy() event");
    }

    public class ChatAdapter extends ArrayAdapter<String> {

        public ChatAdapter(Context ctx){
            super(ctx,0);
        }

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
