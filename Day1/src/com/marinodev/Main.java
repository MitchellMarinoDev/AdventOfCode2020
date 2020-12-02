package com.marinodev;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        final int numToAddTo = 2020;
        List<Integer> input = new ArrayList();

        //Get input
        File inputFile = new File(System.getProperty("user.home") + "\\Downloads\\input.txt");
        Scanner inputReader = new Scanner(inputFile);
        while (inputReader.hasNextLine()) {
            input.add(Integer.parseInt(inputReader.nextLine()));
        }
        // solve:
        int firstPT1 = 0, secondPT1 = 0;
        int firstPT2 = 0, secondPT2 = 0, thirdPT2 = 0;
        boolean P1Solved = false, P2Solved = false;
        // loop through array
        for (int i = 0; i < input.size(); i++) {
            // compare i to the rest of the array
            // start j 1 after i to loop through REST of elements in array
            for (int j = i + 1; j < input.size(); j++) {
                if (input.get(i) + input.get(j) == numToAddTo && !P1Solved) {
                    firstPT1  = input.get(i);
                    secondPT1 = input.get(j);
                    P1Solved = true;
                }
                if (!P2Solved) { // if part 2 is solved, don't iterate
                    // start j 1 after k to loop through REST of elements in array
                    for (int k = j + 1; k < input.size(); k++) {
                        if (input.get(i) + input.get(j) + input.get(k) == numToAddTo) {
                            firstPT2 = input.get(i);
                            secondPT2 = input.get(j);
                            thirdPT2 = input.get(k);
                            P2Solved = true;
                        }
                    }
                }
            }
            if (P1Solved && P2Solved) // if problems solved, break
                break;
        }
        System.out.println("Part 1: " + (firstPT1 * secondPT1));
        System.out.println("Part 2: " + (firstPT2 * secondPT2 * thirdPT2));
    }
}