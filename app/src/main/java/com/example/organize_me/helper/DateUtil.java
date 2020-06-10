package com.example.organize_me.helper;

import java.text.SimpleDateFormat;
import java.util.logging.SimpleFormatter;

public class DateUtil {

    public static String dataAtual(){

        long data = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dataString = simpleDateFormat.format( data );
        return dataString;
    }

}
