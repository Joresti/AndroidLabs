package com.example.jores.androidlabs;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class StartActivity extends Activity {

    Button startBtn;
    Button startChat;

    String tag = "StartActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        startBtn = findViewById(R.id.button);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(StartActivity.this, ListItemsActivity.class);
                startActivityForResult(i,50);

            }
        });
        startChat = findViewById(R.id.Start_Chat);
        startChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =  new Intent(StartActivity.this, ChatWindow.class);
                startActivity(i);
                Log.i(tag,"User clicked start chat");
            }
        });

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
    @Override
    protected void onActivityResult(int requestCode, int responseCode, Intent data){
        if (requestCode == 50 && responseCode == Activity.RESULT_OK){
            Log.i(tag, "Returned to StartActivity.onActivityResult");
            CharSequence text = data.getStringExtra("Response");
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT ;
            Toast toast = Toast.makeText(context,text, duration);
            toast.show();
        }
    }

}
