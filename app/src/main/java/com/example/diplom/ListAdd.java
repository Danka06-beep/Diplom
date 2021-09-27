package com.example.diplom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import java.text.SimpleDateFormat;
import java.util.Calendar;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class ListAdd extends AppCompatActivity implements View.OnClickListener{

    private Toolbar toolbar;
    private EditText heading;
    private EditText bodyText;
    private TextView dateTxt;
    private ImageButton dateBtn;
    private CheckBox checkDeadline;
    MyDbHelper dbHelper;
    Calendar dateAndTime = Calendar.getInstance();
    int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_add);
        Initialization();
        setSupportActionBar(toolbar);

        position = -1;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Bundle extras = this.getIntent().getExtras();

        if (extras != null) {
            checkDeadline.setChecked(true);
            dateTxt.setVisibility(VISIBLE);
            dateBtn.setVisibility(VISIBLE);
            position = extras.getInt("position");
            heading.setText(App.getNoteRepository().getNotes().get(position).getHeading());
            bodyText.setText(App.getNoteRepository().getNotes().get(position).getBodyOfNote());
            dateTxt.setText(DateUtil.DateToString(App.getNoteRepository().getNotes().get(position).getDate()));
            Toast.makeText(this, "Редактирование заметки", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Новая заметка", Toast.LENGTH_SHORT).show();
        }
        dateBtn.setOnClickListener(this);
        checkDeadline.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    dateTxt.setVisibility(VISIBLE);
                    dateBtn.setVisibility(VISIBLE);
                }else{
                    dateTxt.setVisibility(GONE);
                    dateBtn.setVisibility(GONE);
                }
            }
        });

    }



    private void Initialization() {
        toolbar = findViewById(R.id.toolbar);
        heading = findViewById(R.id.heading);
        bodyText = findViewById(R.id.bodyText);
        dateTxt = findViewById(R.id.date);
        dateBtn = findViewById(R.id.dateBtn);
        checkDeadline = findViewById(R.id.checkDeadline);
        dbHelper = new MyDbHelper(this);
    }

    @Override
    public void onClick(View v) {
     switch(v.getId()){
         case R.id.dateBtn:
             setDate(v);
             break;
     }
    }

    private void setDate(View v) {
        new DatePickerDialog(ListAdd.this, d,
                dateAndTime.get(Calendar.YEAR),
                dateAndTime.get(Calendar.MONTH),
                dateAndTime.get(Calendar.DAY_OF_MONTH))
                .show();

    }
    private void setInitialDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy  HH:mm");
        dateTxt.setText(dateFormat.format(dateAndTime.getTimeInMillis()));
    }

    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            dateAndTime.set(Calendar.YEAR, year);
            dateAndTime.set(Calendar.MONTH, monthOfYear);
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            dateAndTime.set(Calendar.HOUR_OF_DAY, 23);
            dateAndTime.set(Calendar.MINUTE, 59);
            setInitialDateTime();
        }
    };
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_save) {
            String headline = heading.getText().toString();
            String body = bodyText.getText().toString();
            java.util.Date date = DateUtil.StringToDate(dateTxt.getText().toString());
            if (position != -1) {
                App.getNoteRepository().removeNotes(position);
            }
            App.getNoteRepository().setNotes(new Note(headline, body, date, App.getNoteRepository().getNotes().size()));

            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}