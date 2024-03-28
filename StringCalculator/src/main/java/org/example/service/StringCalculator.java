package org.example.service;

public class StringCalculator {

    public static int add(String numbers) {
        if (isEmpty(numbers)) {
            return 0;
        }

        checkLastCharacter(numbers);

        String[] numbersArray = numbers.split("[,\n]");
        return sumNumbers(numbersArray);
    }

    private static boolean isEmpty(String input) {
        return input.isEmpty();
    }

    private static void checkLastCharacter(String input) {
        char lastChar = input.charAt(input.length() - 1);
        if (lastChar == ',' || lastChar == '\n') {
            throw new IllegalArgumentException("Input cannot end with a separator.");
        }
    }

    private static int sumNumbers(String[] numbersArray) {
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
