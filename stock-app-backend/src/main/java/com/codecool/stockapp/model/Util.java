package com.codecool.stockapp.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
    public static String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy HH-mm-ss");
        return dateFormat.format(new Date());
    }
}