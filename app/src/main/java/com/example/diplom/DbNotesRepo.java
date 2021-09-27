package com.example.diplom;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;

import java.util.Date;
import java.util.List;

public class DbNotesRepo  implements NoteRepository{

    private final String LOG = DbNotesRepo.class.getName();
    private  final String NOTES_TABLE_NAME = "MyTable";
    private final SQLiteDatabase db;

    public DbNotesRepo(SQLiteDatabase db){
        this.db = db;
    }
    
    @Override
    public List<Note> getNotes() {
        Cursor cursor = db.query(NOTES_TABLE_NAME,null,null, null,null,null,null);
        if(cursor.moveToFirst()) {
            List<Note> result = new ArrayList<>();
            int id = cursor.getColumnIndex("id");
            int headingColIndex = cursor.getColumnIndex("heading");
            int bodyColIndex = cursor.getColumnIndex("body");
            int dateColIndex = cursor.getColumnIndex("date");
            do {
                String heading = cursor.getString(headingColIndex);
                String body = cursor.getString(bodyColIndex);
                Date date = DateUtil.StringToDate(cursor.getString(dateColIndex));
                int idList = cursor.getInt(id);
                result.add((new Note(heading, body, date, idList)));
            }while (cursor.moveToNext());
            Log.d("list", result.toString());
            Collections.sort(result);
            Log.d("list",result.toString());
            return result;
        }else {
Log.d(LOG,"0 rows");
        }
        return new ArrayList<>();
    }

    @Override
    public void setNotes(Note note) {
        ContentValues contentVal = new ContentValues();
        contentVal.put("heading",note.getHeading());
        contentVal.put("body",note.getBodyOfNote());
        contentVal.put("date", DateUtil.DateToString(note.getDate()));
        long rowID = db.insert(NOTES_TABLE_NAME, null, contentVal);
        Log.d(LOG, "row inserted, ID = " + rowID);
    }

    @Override
    public void removeNotes(int position) {
        int delCount = db.delete(NOTES_TABLE_NAME, "id = " + App.getNoteRepository().getNotes().get(position).getId(), null);
    }

}



