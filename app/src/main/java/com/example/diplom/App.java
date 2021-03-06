package com.example.diplom;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;



public class App extends Application {

    private static NoteRepository noteRepository;
    private static PinCode pincode;


    public void onCreate() {
        super.onCreate();
        MyDbHelper myDbHelper = new MyDbHelper(this);
        SQLiteDatabase writableDatabase = myDbHelper.getWritableDatabase();
        noteRepository = new DbNotesRepo(writableDatabase);
        pincode = new JustPinCode(this);
    }
    public static NoteRepository getNoteRepository() {
        return noteRepository;
    }
    public static PinCode getKeystore() {
        return pincode;
    }
}