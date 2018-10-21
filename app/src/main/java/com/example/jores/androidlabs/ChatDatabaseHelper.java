package com.example.jores.androidlabs;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ChatDatabaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "Lab5";
    public static int VERSION_NUM = 2;
    protected final static String KEY_ID = "key_id";
    protected final static String KEY_MESSAGE = "key_msg";
    public String tag = "ChatDatabase Helper";

    public ChatDatabaseHelper(Context ctx) {
        super(ctx, DATABASE_NAME, null, VERSION_NUM);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE ChatHistory (" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_MESSAGE + " TEXT);");
        Log.i(tag, ", Calling on Create()");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVer, int newVer) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS ChatHistory;");
        this.onCreate(sqLiteDatabase);
        Log.i(tag, ", Calling onUpgrade, oldVersion=)" + oldVer + " newVersion=" + newVer);
    }
}
