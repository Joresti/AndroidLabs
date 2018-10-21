package com.example.jores.androidlabs;

import android.app.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity {

    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Email = "DefaultEmail";

    String tag = "LoginActivity";
    Button loginButton =null;
    EditText emailEditText;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(tag, "in the onCreate() event");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginButton = findViewById(R.id.login_button);
        emailEditText = findViewById(R.id.email_input);

        SharedPreferences sp = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        emailEditText.setText(sp.getString(Email, "email@domain.com"));

        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {


                SharedPreferences.Editor e = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE).edit();
                e.putString(Email, String.valueOf(emailEditText.getText()));
                e.apply();

                Intent intent = new Intent(LoginActivity.this, StartActivity.class);
                startActivity(intent);
                //System.out.print(loginEdit.getText());

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

}
