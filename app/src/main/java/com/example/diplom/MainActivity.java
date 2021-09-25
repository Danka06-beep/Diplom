package com.example.diplom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton delBtn;
    private ImageView imageViewPin0;
    private ImageView imageViewPin1;
    private ImageView imageViewPin2;
    private ImageView imageViewPin3;
    private Button btnOne;
    private Button btnTwo;
    private Button btnTree;
    private Button btnFour;
    private Button btnFive;
    private Button btnSix;
    private Button btnSeven;
    private Button btnEight;
    private Button btnNine;
    private Button btnZero;
    private String inputPin = "";
    private int keyImgPin = 0;
    private ImageView[] imgkey = new ImageView[4];
Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!App.getKeystore().hashPin()) {
            Intent intent = new Intent(MainActivity.this, Setings.class);
            startActivity(intent);
        }

        initialization();

        btnOne.setOnClickListener(this);
        btnTwo.setOnClickListener(this);
        btnTree.setOnClickListener(this);
        btnFour.setOnClickListener(this);
        btnFive.setOnClickListener(this);
        btnSix.setOnClickListener(this);
        btnSeven.setOnClickListener(this);
        btnEight.setOnClickListener(this);
        btnNine.setOnClickListener(this);
        btnZero.setOnClickListener(this);
        delBtn.setOnClickListener(this);

        setDrawable();

    }

    private void setDrawable() {
        imageViewPin0.setImageResource(R.drawable.ic_circle);
        imageViewPin1.setImageResource(R.drawable.ic_circle);
        imageViewPin2.setImageResource(R.drawable.ic_circle);
        imageViewPin3.setImageResource(R.drawable.ic_circle);
    }

    private void initialization() {
        delBtn = findViewById(R.id.btnDel);
        imageViewPin0 = findViewById(R.id.PinNul);
        imageViewPin1 = findViewById(R.id.PinOne);
        imageViewPin2 = findViewById(R.id.PinTwo);
        imageViewPin3 = findViewById(R.id.PinThree);
        btnOne = findViewById(R.id.btnOne);
        btnTwo = findViewById(R.id.btnTwo);
        btnTree = findViewById(R.id.btnTree);
        btnFour = findViewById(R.id.btnFour);
        btnFive = findViewById(R.id.btnFive);
        btnSix = findViewById(R.id.btnSix);
        btnSeven = findViewById(R.id.btnSeven);
        btnEight = findViewById(R.id.btnEight);
        btnNine = findViewById(R.id.btnNine);
        btnZero = findViewById(R.id.btnZero);
        imgkey[0] = imageViewPin0;
        imgkey[1] = imageViewPin1;
        imgkey[2] = imageViewPin2;
        imgkey[3] = imageViewPin3;

    }

    @Override
    public void onClick(View v) {
        if (inputPin.length() < 4) {
            switch (v.getId()) {

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
                case R.id.btnDel:
                    clearReplenish();
                    LastDelite();
                    Toast toast = Toast.makeText(getApplicationContext(), inputPin, Toast.LENGTH_LONG);
                    toast.show();
                    break;
            }

            if (inputPin.length() == 4) {
                try {

                    if (App.getKeystore().checkPin(inputPin)) {
                        Intent intent = new Intent(MainActivity.this, ListToActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    } else {
                        Toast.makeText(this, "Неверный пин-код", Toast.LENGTH_SHORT).show();

                        inputPin = "";
                        for (int i = 0; i < 4; i++) {
                            clearReplenish();
                        }
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
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

    private void LastDelite() {
        if (inputPin.length() > 0) {
            inputPin = inputPin.substring(0, (inputPin.length() - 1));
        }
    }
}

