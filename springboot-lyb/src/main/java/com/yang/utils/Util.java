package com.yang.utils;

import java.util.Random;

public class Util {
    public static int getRandomNumber(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("min =" + min + ">" + "max=" + max);
        }
        Random rnd = new Random();
        int randomNumber = rnd.nextInt(max - min + 1);
        return min + randomNumber;
    }
}
