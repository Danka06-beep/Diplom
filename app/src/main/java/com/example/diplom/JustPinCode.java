package com.example.diplom;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static android.content.Context.MODE_PRIVATE;
public class JustPinCode implements PinCode{


    private SharedPreferences sharedPreferences;
    private static final String SETTINGS = "settings";
    private static final String PIN = "Pin";

    JustPinCode(Context context) {
        sharedPreferences = context.getSharedPreferences(SETTINGS, MODE_PRIVATE);

    }
    @Override
    public boolean hashPin() {
        return sharedPreferences.contains(PIN);
    }

    @Override
    public boolean checkPin(String pin) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String savedPin = sharedPreferences.getString(PIN, "");
        return getHash(pin).equals(savedPin);
    }

    @Override
    public void saveNew(String pin) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PIN, getHash(pin));
        editor.apply();

    }
    private static String getHash(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest m = MessageDigest.getInstance("MD5");
        m.reset();
        m.update(str.getBytes("utf-8"));
        StringBuilder s2 = new StringBuilder(new BigInteger(1, m.digest()).toString(16));
        while (s2.length() < 32) {
            s2.insert(0, "0");
        }
        return s2.toString();
    }
}
