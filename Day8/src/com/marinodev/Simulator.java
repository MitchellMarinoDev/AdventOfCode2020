package com.marinodev;

import java.util.List;

public class Simulator {

    private int accumulator = 0;
    private int instructionPointer = 0;
    private boolean didComplete = false;
    private List<String> instructions;

    public Simulator(List<String> instructions) {
        this.instructions = instructions;
    }

/*
Returns the accumulator of the simulation after completing
didComplete is set according to if the simulation looped to infinity or completed successfully
*/
    public int simulate() {
        accumulator = 0;
        instructionPointer = 0;
        boolean[] hasBeenTo = new boolean[instructions.size()];
        while (instructionPointer < instructions.size() && !hasBeenTo[instructionPointer]) {
            hasBeenTo[instructionPointer] = true;
            doOp(instructions.get(instructionPointer));
        }
        didComplete = instructionPointer >= instructions.size();
        return accumulator;
    }

    // executes 1 operation
    private void doOp(String op) {
        String[] split = op.split(" ");
        String instruction = split[0];
        int arg = Integer.parseInt(split[1]);
        switch (instruction) {
            case "nop":
                instructionPointer ++;
                return;
            case "acc":
                instructionPointer ++;
                accumulator += arg;
                return;
            case "jmp":
                instructionPointer += arg;
                return;
        }
        throw new IllegalStateException("Could not interpret instruction. Got: " + instruction);
    }


    // GETTERS AND SETTERS
    public int getAccumulator() {
        return accumulator;
    }
    public int getInstructionPointer() {
        return instructionPointer;
    }
    public boolean didComplete() {
        return didComplete;
    }
    public void setInstructions(List<String> instructions) {
        this.instructions = instructions;
    }
}