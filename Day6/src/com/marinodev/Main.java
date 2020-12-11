package com.marinodev;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
	    File inputFile = new File(System.getProperty("user.home") + "\\Downloads\\input.txt");
        BufferedReader inputReader = new BufferedReader(new FileReader(inputFile));

        // get input from file
        // groups[group][person in group]
        List<List<String>> groups = new ArrayList<>();
        groups.add(new ArrayList<>());
        while (inputReader.ready()) {
            String line = inputReader.readLine();
            // if the line is empty start a new group.
            if (line.isBlank()) {
                groups.add(new ArrayList<>());
            } else {
                // else add line to last group
                // System.out.println("adding " + line + " to the " + (groups.size() - 1) + " group");
                groups.get(groups.size() - 1).add(line);
            }
        }
        // System.out.println(groups);

        // SOLVE
        int PT1 = 0;
        int PT2 = 0;
        // for each group
        for (List<String> group : groups) {
            HashSet<Character> questionsPT1 = new HashSet<>();
            HashSet<Character> questionsPT2 = new HashSet<>();

            // start questionsPT2 with person1's answers
            for (char c : group.get(0).toCharArray()) {
                questionsPT2.add(c);
            }

            // for each person in group
            for (String person : group) {
                // PT1 : char in any person in group
                for (char c : person.toCharArray())
                    questionsPT1.add(c);
                // PT2 : char in all people in group
                HashSet<Character> newQuestionPT2 = new HashSet<>();
                for (char c : person.toCharArray()) {
                    // if all other people have the char, add it to the new set
                    if (questionsPT2.contains(c))
                        newQuestionPT2.add(c);
                }
                // finally switch the sets
                questionsPT2 = newQuestionPT2;
            }
            // increment vars based on hash sets
            PT1 += questionsPT1.size();
            PT2 += questionsPT2.size();

        }

        System.out.println("Part 1: " + PT1);
        System.out.println("Part 2: " + PT2);
    }
}
