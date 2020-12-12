package com.marinodev;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        List<List<String>> parsedRule = getInput();

        List<Bag> bags = new ArrayList<>();
        Map<String, Bag> bagFromName = new HashMap<>();
        // create bags
        for (List<String> rule : parsedRule) {
            Bag bag = new Bag(rule.get(0));
            bags.add(bag);
            bagFromName.put(rule.get(0), bag);
        }

        // create references
        for (List<String> rule : parsedRule) {
            Bag bag = bagFromName.get(rule.get(0));
            for (int i = 1; i < rule.size() - 1; i += 2) {
                Bag child = bagFromName.get(rule.get(i+1));
                bag .children.add(child);
                child.parents.add(bag);
                bag.numOfChildrenCanHold.add(Integer.parseInt(rule.get(i)));
            }
        }
        // get the total unique parent the shiny gold bag has
        System.out.println("Part 1: " + bagFromName.get("shiny gold").deepGetNumParents());
        System.out.println("Part 2: " + bagFromName.get("shiny gold").deepGetNumChildren());

    }

    private static List<List<String>> getInput() throws IOException {
        File inputFile = new File(System.getProperty("user.home") + "\\Downloads\\input.txt");
        BufferedReader inputReader = new BufferedReader(new FileReader(inputFile));
        List<List<String>> input = new ArrayList<>();
        // return [bag1] [amount] [bag2] <amount> <bag3> ...

        while (inputReader.ready()) {
            String line = inputReader.readLine();
            String[] otherBags = line.split(",");
            String[] split = otherBags[0].split(" ");
            List<String> inputLine = new ArrayList<>(); // First bag + 2 x other bags
            inputLine.add(split[0] + " " + split[1]); // 1st bag name
            if (!split[4].equals("no")) {
                inputLine.add(split[4]); // amount
                inputLine.add(split[5] + " " + split[6]); // 2nd bag name
                // now add other lines
                for(int i = 1; i < otherBags.length; i++) {
                    split =  otherBags[i].strip().split(" ");
                    inputLine.add(split[0]); // amount
                    inputLine.add(split[1] + " " + split[2]);
                }
            }
            input.add(inputLine);
        }
        inputReader.close();
        return input;
    }
}

