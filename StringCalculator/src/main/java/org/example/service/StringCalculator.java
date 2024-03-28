package org.example.service;

import java.util.ArrayList;
import java.util.List;

public class StringCalculator {

    public static int add(String numbers) {
        if (isEmpty(numbers)) {
            return 0;
        }

        checkLastCharacter(numbers);

        if (numbers.startsWith("//")) {
            return sumWithCustomDelimiter(numbers);
        } else {
            String[] numbersArray = numbers.split("[,\n]");
            return sumNumbers(numbersArray);
        }
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

    private static int sumWithCustomDelimiter(String numbers) {
        int delimiterIndex = numbers.indexOf("\n");
        if (delimiterIndex == -1) {
            throw new IllegalArgumentException("Invalid input format");
        }
        String delimiter = numbers.substring(2, delimiterIndex);
        String[] parts = numbers.substring(delimiterIndex + 1).split("\\Q" + delimiter + "\\E");
        int sum = 0;
        List<Integer> negatives = new ArrayList<>();
        for (String part : parts) {
            char nonNumericChar = findNonNumericChar(part);
            if (nonNumericChar != 0) {
                throw new IllegalArgumentException("‘" + delimiter + "‘ expected but ‘" + nonNumericChar +
                        "‘ found at position " + numbers.substring(4).indexOf(nonNumericChar) + ".");
            }
            try {
                int num = Integer.parseInt(part);
                if (num < 0) {
                    negatives.add(num);
                } else {
                    sum += num;
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid input format.");
            }
        }
        if (!negatives.isEmpty()) {
            handleNegativeNumbers(negatives);
        }
        return sum;
    }

    private static char findNonNumericChar(String str) {
        boolean isFirstChar = true;
        for (char c : str.toCharArray()) {
            if (isFirstChar && c == '-') {
                isFirstChar = false;
                continue;
            }
            if (!Character.isDigit(c)) {
                return c;
            }
            isFirstChar = false;
        }
        return 0;
    }

    private static int sumNumbers(String[] numbersArray) {
        int sum = 0;
        List<Integer> negatives = new ArrayList<>();
        for (String num : numbersArray) {
            try {
                int n = Integer.parseInt(num);
                if (n < 0) {
                    negatives.add(n);
                } else {
                    sum += n;
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid input format.");
            }
        }
        if (!negatives.isEmpty()) {
            handleNegativeNumbers(negatives);
        }
        return sum;
    }

    private static void handleNegativeNumbers(List<Integer> negatives) {
        StringBuilder message = new StringBuilder("Negative number(s) not allowed: ");
        for (int i = 0; i < negatives.size(); i++) {
            if (i > 0) {
                message.append(", ");
            }
            message.append(negatives.get(i));
        }
        throw new IllegalArgumentException(message.toString());
    }

}
