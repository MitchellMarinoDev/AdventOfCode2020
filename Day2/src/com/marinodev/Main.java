package com.marinodev;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        File inputFile = new File(System.getProperty("user.home") + "\\Downloads\\input.txt");
        Scanner inputScanner = new Scanner(inputFile);
        // vars from input file
        List<Integer> lower = new ArrayList<>();
        List<Integer> upper = new ArrayList<>();
        List<Character> letter = new ArrayList<>();
        List<String> password = new ArrayList<>();

        // write the data to the lists
        while (inputScanner.hasNext()) {
            String in = inputScanner.nextLine();
            String[] split = in.split(" ");
            String[] range = split[0].split("-");
            lower.add(Integer.parseInt(range[0]));
            upper.add(Integer.parseInt(range[1]));
            letter.add(split[1].charAt(0));
            password.add(split[2]);
        }
        // check if they ar correct
        int correctPasswordsPt1 = 0;
        int correctPasswordsPt2 = 0;
        for (int i = 0; i < lower.size(); i++) {
            if (checkPasswordPt1(lower.get(i), upper.get(i), letter.get(i), password.get(i)))
                correctPasswordsPt1++;
            if (checkPasswordPt2(lower.get(i), upper.get(i), letter.get(i), password.get(i)))
                correctPasswordsPt2++;
        }
        // Print results
        System.out.println("Part 1: " + correctPasswordsPt1);
        System.out.println("Part 2: " + correctPasswordsPt2);
    }

    private static boolean checkPasswordPt1(int lower, int upper, char letter, String password) {
        int count = 0; // num of times the letter shows up in the password
        for (char c : password.toCharArray()) {
            if (letter == c)
                count++;
        }
        return lower <= count && count <= upper;
    }

    private static boolean checkPasswordPt2(int index1, int index2, char letter, String password) {
        // adjust for no 0-indexing;
        // ^ is the Java XOR operator
        return (password.charAt(index1 - 1) == letter ^ password.charAt(index2 - 1) == letter);
    }
}
