package com.example.diplom;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;



public class App extends Application {

    private static NoteRepository noteRepository;
    private static PinCode pincode;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate();
        MyDbHelper myDbHelper = new MyDbHelper(this);

    }
    public static NoteRepository getNoteRepository() {
        return noteRepository;
    }
    public static PinCode getKeystore() {
        return pincode;
    }
}