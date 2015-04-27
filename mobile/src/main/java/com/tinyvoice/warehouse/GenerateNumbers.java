package com.tinyvoice.warehouse;

/**
 * Created by segeoma on 2015-04-27.
 */
public class GenerateNumbers {
    public static String [] getNumbers(){
        int[] listOfInts = new int[1001];
        String[] listOfStrings = new String [1001];

        for(int i = 0; i <1001; i++){
            listOfInts[i] = i;
            listOfStrings[i] = String.valueOf(listOfInts[i]);
        }
        return listOfStrings;
    }
}