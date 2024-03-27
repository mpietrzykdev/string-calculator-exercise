package org.example.service;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringCalculatorTest {

    @Test
    void shouldReturn0WhenAddEmptyString() {
        //given
        String inputForTest = "";
        int expectedResult = 0;

        //when
        int result = StringCalculator.add(inputForTest);

        //then
        assertEquals(expectedResult, result);
    }

    @Test
    void shouldReturn5WhenAddSingleNumberEqualTo5() {
        //given
        String inputForTest = "5";
        int expectedResult = 5;

        //when
        int result = StringCalculator.add(inputForTest);

        //then
        assertEquals(expectedResult, result);
    }

    @Test
    void shouldReturn8WhenAddTwoNumbersEqualTo3And5SeparatedByComa() {
        //given
        String inputForTest = "3,5";
        int expectedResult = 8;

        //when
        int result = StringCalculator.add(inputForTest);

        //then
        assertEquals(expectedResult, result);
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenAddMoreThanTwoNumbers() {
        //given
        String inputForTest = "1,2,3,4";

        //when
        Exception e = assertThrows(IllegalArgumentException.class, () -> StringCalculator.add(inputForTest));

        //then
        SoftAssertions sa = new SoftAssertions();
        sa.assertThat(e).isExactlyInstanceOf(IllegalArgumentException.class);
        sa.assertThat(e).hasMessage("Input can contain up to two numbers separated by commas.");
        sa.assertAll();
    }

}