package com.example.jores.androidlabs;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

public class ListItemsActivity extends Activity {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    String tag = "ListItemActivity";
    CheckBox cb;
    Switch s;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_items);
        Log.i(tag, "In the onCreate() event");

        ImageButton imgBtn = findViewById(R.id.imageButton);

        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                if (i.resolveActivity(getPackageManager()) != null){
                    startActivityForResult(i, REQUEST_IMAGE_CAPTURE);
                }

            }
        });

        s = findViewById(R.id.switch1);
        s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Context context = getApplicationContext();
                CharSequence text = (isChecked?"Switch is On":"Switch is off");
                int duration = Toast.LENGTH_SHORT ;
                Toast toast = Toast.makeText(context,text, duration);
                toast.show();
            }
        });
        cb =  findViewById(R.id.checkBox);
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                AlertDialog.Builder builder =  new AlertDialog.Builder(ListItemsActivity.this);
                builder.setMessage(R.string.dialog_message).setTitle(R.string.dialog_title).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent resultIntent =  new Intent();
                        resultIntent.putExtra("Response", "Here is my response");
                        setResult(Activity.RESULT_OK, resultIntent);
                        finish();
                    }
                }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();
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
        ImageButton imgBtn = findViewById(R.id.imageButton);
        if (requestCode == REQUEST_IMAGE_CAPTURE && responseCode == RESULT_OK){
            Log.i(tag, "Returned to ListItemActivity.onActivityResult");
            Bundle extras =  data.getExtras();

            Bitmap imageBitmap;
            imageBitmap = (Bitmap) extras.get("data");
            imgBtn.setImageBitmap(imageBitmap);

        }
    }


}
