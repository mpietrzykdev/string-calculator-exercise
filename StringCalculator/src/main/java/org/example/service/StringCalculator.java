package org.example.service;

public class StringCalculator {

    public static int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }

        char lastChar = numbers.charAt(numbers.length() - 1);
        if (lastChar == ',' || lastChar == '\n') {
            throw new IllegalArgumentException("Input cannot end with a separator.");
        }

        String[] numbersArray = numbers.split("[,\n]");
        int sum = 0;
        for (String num : numbersArray) {
            try {
                sum += Integer.parseInt(num);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid input format.");
            }
        }
        return sum;
    }

}
