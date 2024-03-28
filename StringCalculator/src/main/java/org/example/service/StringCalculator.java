package org.example.service;

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
        for (String part : parts) {
            char nonNumericChar = findNonNumericChar(part);
            if (nonNumericChar != 0) {
                throw new IllegalArgumentException("‘" + delimiter + "‘ expected but ‘" + nonNumericChar +
                        "‘ found at position " + numbers.substring(4).indexOf(nonNumericChar) + ".");
            }
            try {
                sum += Integer.parseInt(part);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid input format.");
            }
        }
        return sum;
    }

    private static char findNonNumericChar(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return c;
            }
        }
        return 0;
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
