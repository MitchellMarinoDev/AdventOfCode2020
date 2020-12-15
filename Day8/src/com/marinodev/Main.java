package com.marinodev;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        File inputFile = new File(System.getProperty("user.home") + "\\Downloads\\input.txt");
        BufferedReader inputReader = new BufferedReader(new FileReader(inputFile));
        List<String> instructions = new ArrayList<>();
        // read input
        while (inputReader.ready()) {
            Collections.addAll(instructions, inputReader.readLine());
        }
        // Part 1
        // run program to figure out what is wrong
        var simulator = new Simulator(instructions);
        int accumulator = simulator.simulate();
        System.out.println("Part 1: " + accumulator);
        // Part 2
        // change 1 instruction at a time.
        int changedOp = 0;
        int acc = 0;
        while (changedOp < instructions.size()) {
            // create a copy of the instructions that we can mutilate
            List<String> currentInstructions = new ArrayList<>(instructions);
            // get the next non "acc" instruction
            while (instructions.get(changedOp).split(" ")[0].equals("acc"))
                changedOp++;
            // change the instruction
            String[] split = instructions.get(changedOp).split(" ");
            currentInstructions.set(changedOp, (split[0].equals("nop") ? "jmp" : "nop") + " " + split[1]);
            // simulate the instructions
            simulator.setInstructions(currentInstructions);
            acc = simulator.simulate();
            if (simulator.didComplete()) {
                System.out.println("program terminated by changing instruction " + changedOp + " to " + currentInstructions.get(changedOp));
                break;
            }
            // increment changed op
            changedOp++;
        }
        System.out.println("Part 2: " + acc);
    }
}