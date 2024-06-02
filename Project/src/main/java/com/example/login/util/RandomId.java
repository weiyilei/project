package com.example.login.util;

import java.util.Random;

public class RandomId {

    public static String getUniqueKey(){
        Random random = new Random();
        Integer number = random.nextInt(90) + 10;
        return System.currentTimeMillis() + String.valueOf(number);
    }
}
