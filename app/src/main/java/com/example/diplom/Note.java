package com.example.diplom;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Date;
import java.util.Objects;

public class Note implements Comparable<Note>{
    String heading;
    String bodyOfNote;
    Date date;
    int id;

    public Note(String heading, String bodyOfNote, Date date, int id) {
        this.heading = heading;
        this.bodyOfNote = bodyOfNote;
        this.date = date;
        this.id = id;
    }

    public String getHeading() {
        return heading;
    }

    public String getBodyOfNote() {
        return bodyOfNote;
    }

    public Date getDate() {
        return date;
    }

    public int getId() {
        return id;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public void setBodyOfNote(String bodyOfNote) {
        this.bodyOfNote = bodyOfNote;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setId(int id) {
        this.id = id;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return id == note.id &&
                Objects.equals(heading, note.heading) &&
                Objects.equals(bodyOfNote, note.bodyOfNote) &&
                Objects.equals(date, note.date);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(heading, bodyOfNote, date, id);
    }

    public int compareTo(Note o){
        if (this.getDate() == null && o.getDate() == null){
            return -1;
        } else if (this.getDate() != null && o.getDate() == null){
            return -1;
        } else if (this.getDate() == null && o.getDate() != null){
            return 1;
        }
        return this.getDate().compareTo(o.getDate());
    }
}
