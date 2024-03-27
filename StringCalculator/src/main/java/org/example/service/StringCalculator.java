package org.example.service;

public class StringCalculator {

    public static int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }

        String[] numbersArray = numbers.split(",");
        if (numbersArray.length > 2) {
            throw new IllegalArgumentException("Input can contain up to two numbers separated by commas.");
        }

        int sum = 0;
        for (String num : numbersArray) {
            sum += Integer.parseInt(num);
        }
        return sum;
    }

}
