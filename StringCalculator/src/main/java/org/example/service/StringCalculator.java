package org.example.service;

import java.util.ArrayList;
import java.util.List;

public class StringCalculator {

    public static int add(String numbers) {
        if (isEmpty(numbers)) {
            return 0;
        }

        List<String> errors = new ArrayList<>();

        checkLastCharacter(numbers);

        int result;
        if (numbers.startsWith("//")) {
            result = sumWithCustomDelimiter(numbers, errors);
        } else {
            result = sumNumbers(numbers.split("[,\n]"), errors);
        }

        handleErrors(errors);

        return result;
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

    private static int sumWithCustomDelimiter(String numbers, List<String> errors) {
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
                String[] numbersWithCustomDelimiter = part.split(String.valueOf(nonNumericChar));
                for (String num : numbersWithCustomDelimiter) {
                    int n = Integer.parseInt(num);
                    if (n < 0) {
                        negatives.add(n);
                        handleNegativeNumbers(negatives, errors);
                    }
                }
                errors.add("‘" + delimiter + "‘ expected but ‘" + nonNumericChar +
                        "‘ found at position " + numbers.substring(4).indexOf(nonNumericChar) + ".");
            } else {
                int num = Integer.parseInt(part);
                if (num < 0) {
                    negatives.add(num);
                    handleNegativeNumbers(negatives, errors);
                } else {
                    sum += num;
                }
            }
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

    private static int sumNumbers(String[] numbersArray, List<String> errors) {
        int sum = 0;
        List<Integer> negatives = new ArrayList<>();
        for (String num : numbersArray) {
            int n = Integer.parseInt(num);
            if (n < 0) {
                negatives.add(n);
            } else {
                sum += n;
            }
        }
        if (!negatives.isEmpty()) {
            handleNegativeNumbers(negatives, errors);
        }
        return sum;
    }

    private static void handleNegativeNumbers(List<Integer> negatives, List<String> errors) {
        StringBuilder message = new StringBuilder("Negative number(s) not allowed: ");
        for (int i = 0; i < negatives.size(); i++) {
            if (i > 0) {
                message.append(", ");
            }
            message.append(negatives.get(i));
        }
        errors.add(message.toString());
    }

    private static void handleErrors(List<String> errors) {
        if (!errors.isEmpty()) {
            throw new IllegalArgumentException(String.join("\n", errors));
        }
    }

}
