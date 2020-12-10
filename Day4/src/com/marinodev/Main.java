package com.marinodev;


import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {
        final String[] REQUIRED_KEYS = {"byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"};
        File inputFile = new File(System.getProperty("user.home") + "\\Downloads\\input.txt");
        List<String> passports = listFromFile(inputFile);
        System.out.println(passports);

        int nValidKeyPassports = 0;
        int nValidPassports = 0;
        for (String passport : passports) {
            // each key value pair is split by a space
            Map<String, String> credentials = new HashMap<>();
            String[] passportData = passport.split("[ :]");
            for (int i = 0; i < passportData.length; i+= 2) {
                credentials.put(passportData[i], passportData[i+1]);
            }
            System.out.println(credentials);

            // innocent until proven guilty, true until proven false :)
            boolean hasAllRequiredKeys = true;
            boolean hasValidValues     = true;
            // check for each required key
            for (String requiredKey : REQUIRED_KEYS) {
                // if it doesn't have the key
                if (!credentials.containsKey(requiredKey)) {
                    hasAllRequiredKeys = false;
                    hasValidValues = false;
                    break;
                }
                if (!valueIsValid(requiredKey, credentials.get(requiredKey))) {
                    hasValidValues = false;
                    System.out.println(requiredKey + " isn't valid for the" + passports.indexOf(passport) + " passport.");
                }
            }
            // increment if true
            if (hasAllRequiredKeys)
                nValidKeyPassports++;
            if (hasValidValues)
                nValidPassports++;
        }

        System.out.println("Part 1: " + nValidKeyPassports);
        System.out.println("Part 2: " + nValidPassports);
    }

    // logic to verify if a value is valid for a given key
    private static boolean valueIsValid(String key, String value) {
        switch (key) {
            case "byr":
                try {
                    int valueI = Integer.parseInt(value);
                    return valueI >= 1920 && valueI <= 2002;
                } catch (NumberFormatException e) {
                    return false;
                }
            case "iyr":
                try {
                    int valueI = Integer.parseInt(value);
                    return valueI >= 2010 && valueI <= 2020;
                } catch (NumberFormatException e) {
                    return false;
                }
            case "eyr":
                try {
                    int valueI = Integer.parseInt(value);
                    return valueI >= 2020 && valueI <= 2030;
                } catch (NumberFormatException e) {
                    return false;
                }
            case "hgt":
                if (value.contains("in")) { // if in inches
                    value = value.replace("in", ""); // remove the "in" tag at the end
                    int height = Integer.parseInt(value);
                    return height >= 59 && height <= 79;
                }
                if (value.contains("cm")) { // if in centimeters
                    value = value.replace("cm", ""); // remove the "in" tag at the end
                    int height = Integer.parseInt(value);
                    return height >= 150 && height <= 193;
                }
                return false;
            case "hcl":
                if (value.charAt(0) != '#') {// check first char
                    return false;
                }
                try {
                    Integer.decode(value); // if it can decode the hex
                } catch (NumberFormatException e) {
                    return false;
                }
                return true;
            case "pid":
                if (value.length() != 9) { // 9 = PID number length
                    return false;
                }
                try {
                    Integer.parseInt(value);
                } catch (NumberFormatException e) {
                    return false;
                }
                return true;
            case "ecl":
                return value.equals("amb") || value.equals("blu") || value.equals("brn") || value.equals("gry") || value.equals("grn") || value.equals("hzl") || value.equals("oth");
            case "cid":
                return true;
            default:
                throw new IllegalArgumentException("key \"" + key + "\" not recognised");
        }
    }

    // gets a list of passport strings from a file
    private static List<String> listFromFile(File file) throws IOException {
        BufferedReader inputReader = new BufferedReader(new FileReader(file));

        // read in passports that are separated with a blank line
        List<String> passports = new ArrayList<>();
        StringBuilder currentPassport = new StringBuilder();
        while (inputReader.ready()) {
            String line = inputReader.readLine();
            if (line.isBlank()) { // passport done. next passport
                passports.add(currentPassport.toString()); // add passport
                currentPassport.setLength(0); // clear current passport
            } else { // line contains data. Add it to the passport
                currentPassport.append(line).append(" ");
            }
        }
        return passports;
    }
}
