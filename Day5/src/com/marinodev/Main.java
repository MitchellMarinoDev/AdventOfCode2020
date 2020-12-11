package com.marinodev;


import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        // get input
        File inputFile = new File(System.getProperty("user.home") + "\\Downloads\\input.txt");
        BufferedReader inputReader = new BufferedReader(new FileReader(inputFile));
        List<String> input = new ArrayList<>();
        while (inputReader.ready()) {
            input.add(inputReader.readLine());
        }

        // TESTS:
        // input = Arrays.asList("BFFFBBFRRR", "FFFBBBFRRR", "BBFFBBFRLL");

        /* Expected output:
        Pass: BFFFBBFRRR
        Row: 70
        Column: 7
        SeatID: 567

        Pass: FFFBBBFRRR
        Row: 14
        Column: 7
        SeatID: 119

        Pass: BBFFBBFRLL
        Row: 102
        Column: 4
        SeatID: 820

        Highest: 820
         */

        HashSet<Integer> takenSeats = new HashSet<>();
        int highest = 0;
        // run your sanity check
        for (String pass : input) {
            // first 7 chars are Front Back
            // next  2 chars are Left Right
            //System.out.println("Pass: " + pass);

            // ROW
            int row = binSpacePart(pass.substring(0, 7), 'F', 'B');
            //System.out.println("Row: " + row);

            // COLUMN
            int col = binSpacePart(pass.substring(7, 10), 'L', 'R');
            //System.out.println("Column: " + col);

            int seatID = (row * 8) + col;
            //System.out.println("SeatID: " + seatID);
            //System.out.println();

            takenSeats.add(seatID);

            if (seatID > highest)
                highest = seatID;
        }
        System.out.println("Part 1: " + highest);
        for (int i = 0; i < highest; i++) {
            // if there is a untaken seat that meets the criteria
            if (!takenSeats.contains(i) && takenSeats.contains(i-1) && takenSeats.contains(i+1)) {
                System.out.println("Part 2: " + i);
                break;
            }
        }
    }


    // infers the max value using the string length then solves
    public static int binSpacePart(String string, char lower, char upper) {
        // max = 2^string.length() - 1
        int max = (int) Math.pow(2, string.length()) - 1; // 0 index
        int min = 0;
        //System.out.println("assumed max: " + max + " and min: 0");
        return binSpacePart(string, lower, upper, min, max);
    }

    // solves a binary space partitioning set
    private static int binSpacePart(String string, char lower, char upper, int min, int max) {
        int range = max - min;
        if (string.charAt(0) == lower) {
            // if it is lower, take the "floor"
            max = ((int) (Math.floor((float) range / 2.0f)) + min);
        } else if (string.charAt(0) == upper) {
            // if it is upper, take the "ceiling"
            min = ((int) (Math.ceil((float) range / 2.0f)) + min);
        }
        // pop the used char
        string = string.substring(1);
        // if there are more chars, continue solving
        if (string.length() != 0)
            return binSpacePart(string, lower, upper, min, max);
        // otherwise, just return min
        return min;
    }
}
