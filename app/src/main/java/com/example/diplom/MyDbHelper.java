package com.example.diplom;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyDbHelper extends SQLiteOpenHelper {

    final static String LOG = "MyLog";

    public MyDbHelper(Context context) {
        super(context, "DB",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(LOG, "create DateBase");
        db.execSQL("create table mytable (id integer primary key autoincrement,heading text, body text, date text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
