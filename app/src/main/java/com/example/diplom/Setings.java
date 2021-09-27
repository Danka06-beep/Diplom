package com.example.diplom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class Setings extends AppCompatActivity implements View.OnClickListener{

    private EditText pinText;
    private ImageButton hideAndShow;
    private Button saveBtn;
private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setings);
        Initialization();
        setSupportActionBar(toolbar);
        hideAndShow.setOnClickListener(this);
        saveBtn.setOnClickListener(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    private void Initialization() {
        toolbar = findViewById(R.id.toolbar);
        pinText = findViewById(R.id.PinText);
        saveBtn = findViewById(R.id.saveBtn);
        hideAndShow = findViewById(R.id.hideAndShow);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.saveBtn:
                if(pinText.getText().toString().length() == 4){
                    try {
                        App.getKeystore().saveNew(pinText.getText().toString());
                    } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    }
                    Intent intent = new Intent(Setings.this, MainActivity.class);
                    startActivity(intent);
                    Log.d("LOG","ggf");
                }else{
                    Toast.makeText(this, "Вы ввели не достаточно символов", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.hideAndShow:
                if (pinText.getInputType() == InputType.TYPE_CLASS_NUMBER) {
                    pinText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
                    hideAndShow.setImageResource(R.drawable.ic_baseline_visibility_24);
                } else {
                    pinText.setInputType(InputType.TYPE_CLASS_NUMBER);
                    hideAndShow.setImageResource(R.drawable.ic_baseline_visibility_off_24);
                }
                break;
        }

    }
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if(id == android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}