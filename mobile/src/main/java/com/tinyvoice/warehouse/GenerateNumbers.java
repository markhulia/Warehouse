package com.tinyvoice.warehouse;


public class GenerateNumbers {
    public static String[] getNumbers() {
        int[] listOfIntegers = new int[1001];
        String[] listOfStrings = new String[1001];

        for (int i = 0; i < 1001; i++) {
            listOfIntegers[i] = i;
            listOfStrings[i] = String.valueOf(listOfIntegers[i]);
        }
        return listOfStrings;
    }
}