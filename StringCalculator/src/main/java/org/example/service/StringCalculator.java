package org.example.service;

public class StringCalculator {

    public static int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }

        String[] numbersArray = numbers.split(",");
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
