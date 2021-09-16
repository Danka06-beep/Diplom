package com.example.diplom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class Setings extends AppCompatActivity implements View.OnClickListener{

    private EditText pinText;
    private ImageButton hideAndShow;
    private Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setings);
        Initialization();
        hideAndShow.setOnClickListener(this);
        saveBtn.setOnClickListener(this);
        Bundle additionally = this.getIntent().getExtras();

    }

    private void Initialization() {
        pinText = findViewById(R.id.PinText);
        saveBtn = findViewById(R.id.saveBtn);
        hideAndShow = findViewById(R.id.hideAndShow);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.saveBtn:
                if(pinText.getText().toString().length() == 4){
                    Intent intent = new Intent(Setings.this, MainActivity.class);
                    startActivity(intent);
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
}