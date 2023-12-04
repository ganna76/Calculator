package org.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {

    public int add(String stringOfNumbers) {
        if (stringOfNumbers.isEmpty()) {
            return 0;
        }

        String delimiter = workOutTheDelimiter(stringOfNumbers);
        stringOfNumbers = stringOfNumbers.substring(stringOfNumbers.indexOf("]")+1);
        String[] allNumbersInArray = tidyAndSplit(delimiter, stringOfNumbers);
        int sum = addItAllUp(allNumbersInArray);

        return sum;
    }

    private String[] tidyAndSplit(String delimiter, String numbers) {

        String removeNewlines = numbers.replaceAll("\n", delimiter)
                                       .replaceAll(Pattern.quote(delimiter),",")
                                       .replaceAll(",,",",")
                                       .replaceAll("//", "")
                                       .replaceAll(Pattern.quote("\\[" + delimiter + "]"), "");

        if (removeNewlines.startsWith(",")) {
            removeNewlines = removeNewlines.substring(1);
        }

        // Need to use Pattern.quote() in case the delimiter is a special character
        return removeNewlines.split(",");
    }

    private int addItAllUp(String[] numbers) {
        int total = 0;
        for (String number : numbers) {
            total = total + checkNumberIsAllowed(number);
        }
        return total;
    }

    private int checkNumberIsAllowed(String number) {
        int num = Integer.parseInt(number);
        if (num < 0) {
            throw new IllegalArgumentException("Negatives not allowed: " + num);
        }
        else if (num > 1000) {
            num = 0;
        }
        return num;
    }

    private String workOutTheDelimiter(String numbers) {

        // Define the pattern for the custom delimiter format
        Pattern pattern = Pattern.compile("^//([^\\n]+)\\n");

        // Create a matcher for the input string
        Matcher matcher = pattern.matcher(numbers);

        // Check if the pattern is found in the input
        if (matcher.find()) {
            // Group 1 of the matcher contains the delimiter
            return matcher.group(1).replaceAll("\\[","").replaceAll("]","");
        } else {
            // Return a default delimiter if not found
            return ",";
        }
    }
}