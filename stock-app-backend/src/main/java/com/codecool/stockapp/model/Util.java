package com.codecool.stockapp.model;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class Util {

    public static String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy  HH:mm:ss");
        return dateFormat.format(new Date());
    }

    public static Double calculateProfit() {
        return 1.0;
    }
}
