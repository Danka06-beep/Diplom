package com.example.diplom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton delBtn;
    private ImageView imageViewPin0;
    private ImageView imageViewPin1;
    private ImageView imageViewPin2;
    private ImageView imageViewPin3;
    private Button oneBtn;
    private Button twoBtn;
    private Button threeBtn;
    private Button fourBtn;
    private Button fiveBtn;
    private Button sixBtn;
    private Button sevenBtn;
    private Button eightBtn;
    private Button nineBtn;
    private Button zeroBtn;
    private String inputPin = "";
    private int keyImgPin = 0;
    private ImageView[] imgkey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Initialization();
        setDrawable();

    }

    private void setDrawable(){
        imageViewPin0.setImageResource(R.drawable.ic_circle);
        imageViewPin1.setImageResource(R.drawable.ic_circle);
        imageViewPin2.setImageResource(R.drawable.ic_circle);
        imageViewPin3.setImageResource(R.drawable.ic_circle);
    }
    private void Initialization(){
         ImageButton delBtn = findViewById(R.id.btnDel);
         ImageView imageViewPin0 = findViewById(R.id.PinNul);
         ImageView imageViewPin1 = findViewById(R.id.PinOne);
         ImageView imageViewPin2 = findViewById(R.id.PinTwo);
         ImageView imageViewPin3 = findViewById(R.id.PinThree);
         Button oneBtn = findViewById(R.id.btnOne);
         Button twoBtn = findViewById(R.id.btnTwo);
         Button threeBtn = findViewById(R.id.btnTree);
         Button fourBtn  = findViewById(R.id.btnFour);
         Button fiveBtn = findViewById(R.id.btnFive);
         Button sixBtn = findViewById(R.id.btnSix);
         Button sevenBtn = findViewById(R.id.btnSeven);
         Button eightBtn = findViewById(R.id.btnEight);
         Button nineBtn = findViewById(R.id.btnNine);
         Button zeroBtn = findViewById(R.id.btnZero);
    }

    @Override
    public void onClick(View v) {
   if (inputPin.length() < 4){
       switch (v.getId()){

           case R.id.btnOne:
               replenish();
               inputPin += "1";
               break;
           case R.id.btnTwo:
               replenish();
               inputPin += "2";
               break;
           case R.id.btnTree:
               replenish();
               inputPin += "3";
               break;
           case R.id.btnFour:
               replenish();
               inputPin += "4";
               break;
           case R.id.btnFive:
               replenish();
               inputPin += "5";
               break;
           case R.id.btnSix:
               replenish();
               inputPin += "6";
               break;
           case R.id.btnSeven:
               replenish();
               inputPin += "7";
               break;
           case R.id.btnEight:
               replenish();
               inputPin += "8";
               break;
           case R.id.btnNine:
               replenish();
               inputPin += "9";
               break;
           case R.id.btnZero:
               replenish();
               inputPin += "0";
               break;

       }

   }
    }

    private void replenish() {
        if (keyImgPin < 4) {
            imgkey[keyImgPin].setColorFilter(Color.argb(255, 32, 47, 255));
            keyImgPin++;
        }
    }
    private void clearReplenish() {
        keyImgPin--;
        if (keyImgPin < 0) {
            keyImgPin = 0;
        }
        imgkey[keyImgPin].setColorFilter(null);
        imgkey[keyImgPin].setImageResource(R.drawable.ic_circle);
    }

    }
