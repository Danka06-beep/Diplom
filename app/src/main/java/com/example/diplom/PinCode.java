package com.example.diplom;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public interface PinCode {
    boolean hashPin();

    boolean checkPin(String pin) ;

    void saveNew(String pin) ;
}
