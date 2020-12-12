package com.marinodev;

import java.io.*;

public class Main {
    static final int ROWS = 323;
    static final int COLS = 31;
    public static void main(String[] args) throws IOException {
        // Get input
        File inputFile = new File(System.getProperty("user.home") + "\\Downloads\\input.txt");
        BufferedReader inputReader = new BufferedReader(new FileReader(inputFile));
        // [Row][Col]
        boolean[][] input = new boolean[ROWS][COLS];
        int row = 0;
        while (inputReader.ready()) {
            String line = inputReader.readLine();
            for (int i = 0; i < line.length(); i++) {
                input[row][i] = (line.charAt(i) == '#');
            }
            row++;
        }
        // Slopes
        int[] xSlopes = {1, 3, 5, 7, 1};
        int[] ySlopes = {1, 1, 1, 1, 2};
        // solve

        long totalTreesHitMult = 1;
        for (int i = 0; i < xSlopes.length; i++) {
            int treesHit = simulateSlope(input, xSlopes[i], ySlopes[i]);
            totalTreesHitMult *= treesHit;
            if (i == 1) System.out.println("Part 1: " + treesHit);
        }
        System.out.println(totalTreesHitMult);

    }

    private static int simulateSlope(boolean[][] input, int xSlope, int ySlope) {
        int x = 0;
        int y = 0;
        int treesHit = 0;

        while (true) {
            x += xSlope;
            y += ySlope;

            if (y >= ROWS) // if out of bounds, return
                break;

            if (input[y][x % COLS])
                treesHit++;
        }
        return treesHit;
    }
}