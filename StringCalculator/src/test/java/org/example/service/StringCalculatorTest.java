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
    void shouldReturn10WhenAddMultipleNumbersFrom1To4SeparatedByComa() {
        //given
        String inputForTest = "1,2,3,4";
        int expectedResult = 10;

        //when
        int result = StringCalculator.add(inputForTest);

        //then
        assertEquals(expectedResult, result);
    }

    @Test
    public void shouldReturn10WhenAddNumbersFrom1To4SeparatedByComaAndNewline() {
        //given
        String inputForTest = "1,2\n3,4";
        int expectedResult = 10;

        //when
        int result = StringCalculator.add(inputForTest);

        //then
        assertEquals(expectedResult, result);
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenComaAtTheEnd() {
        //given
        String inputForTest = "1,2,";

        //when
        Exception e = assertThrows(IllegalArgumentException.class, () -> StringCalculator.add(inputForTest));

        //then
        SoftAssertions sa = new SoftAssertions();
        sa.assertThat(e).isExactlyInstanceOf(IllegalArgumentException.class);
        sa.assertThat(e).hasMessage("Input cannot end with a separator.");
        sa.assertAll();
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenNewlineAtTheEnd() {
        //given
        String inputForTest = "1,2\n";

        //when
        Exception e = assertThrows(IllegalArgumentException.class, () -> StringCalculator.add(inputForTest));

        //then
        SoftAssertions sa = new SoftAssertions();
        sa.assertThat(e).isExactlyInstanceOf(IllegalArgumentException.class);
        sa.assertThat(e).hasMessage("Input cannot end with a separator.");
        sa.assertAll();
    }

    @Test
    void shouldReturn8WhenAddTwoNumbersEqualTo3And5WithCustomDelimiter() {
        //given
        String inputForTest = "//;\n3;5";
        int expectedResult = 8;

        //when
        int result = StringCalculator.add(inputForTest);

        //then
        assertEquals(expectedResult, result);
    }

    @Test
    void shouldReturn10WhenAddNumbersFrom1To4WithCustomDelimiter() {
        //given
        String inputForTest = "//|\n1|2|3|4";
        int expectedResult = 10;

        //when
        int result = StringCalculator.add(inputForTest);

        //then
        assertEquals(expectedResult, result);
    }

    @Test
    void shouldReturn10WhenAddNumbersFrom1To4WithLiteralCustomDelimiter() {
        //given
        String inputForTest = "//sep\n1sep2sep3sep4";
        int expectedResult = 10;

        //when
        int result = StringCalculator.add(inputForTest);

        //then
        assertEquals(expectedResult, result);
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenVariousCustomDelimiters() {
        //given
        String inputForTest = "//|\n1|2,3";

        //when
        Exception e = assertThrows(IllegalArgumentException.class, () -> StringCalculator.add(inputForTest));

        //then
        SoftAssertions sa = new SoftAssertions();
        sa.assertThat(e).isExactlyInstanceOf(IllegalArgumentException.class);
        sa.assertThat(e).hasMessage("‘|‘ expected but ‘,‘ found at position 3.");
        sa.assertAll();
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWithRightMessageWhenInputContainsNegativeNumber() {
        //given
        String inputForTest = "1,-2";

        //when
        Exception e = assertThrows(IllegalArgumentException.class, () -> StringCalculator.add(inputForTest));

        //then
        SoftAssertions sa = new SoftAssertions();
        sa.assertThat(e).isExactlyInstanceOf(IllegalArgumentException.class);
        sa.assertThat(e).hasMessage("Negative number(s) not allowed: -2");
        sa.assertAll();
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWithRightMessageWhenInputContainsMultipleNegativeNumbers() {
        //given
        String inputForTest = "2,-4,-9";

        //when
        Exception e = assertThrows(IllegalArgumentException.class, () -> StringCalculator.add(inputForTest));

        //then
        SoftAssertions sa = new SoftAssertions();
        sa.assertThat(e).isExactlyInstanceOf(IllegalArgumentException.class);
        sa.assertThat(e).hasMessage("Negative number(s) not allowed: -4, -9");
        sa.assertAll();
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWithRightMessageWhenInputWithCustomDelimiterContainsNegativeNumber() {
        //given
        String inputForTest = "//;\n3;-2";

        //when
        Exception e = assertThrows(IllegalArgumentException.class, () -> StringCalculator.add(inputForTest));

        //then
        SoftAssertions sa = new SoftAssertions();
        sa.assertThat(e).isExactlyInstanceOf(IllegalArgumentException.class);
        sa.assertThat(e).hasMessage("Negative number(s) not allowed: -2");
        sa.assertAll();
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWithAllErrorMessagesSeparatedByNewlinesWhenMultipleErrorsOccurs() {
        //given
        String inputForTest = "//|\n1|2,-3";

        //when
        Exception e = assertThrows(IllegalArgumentException.class, () -> StringCalculator.add(inputForTest));

        //then
        SoftAssertions sa = new SoftAssertions();
        sa.assertThat(e).isExactlyInstanceOf(IllegalArgumentException.class);
        sa.assertThat(e).hasMessage("Negative number(s) not allowed: -3\n" +
                "‘|‘ expected but ‘,‘ found at position 3.");
        sa.assertAll();
    }

    @Test
    void shouldIgnoreNumberGreaterThan1000AndReturn3WhenAddTwoNumbers() {
        //given
        String inputForTest = "3,1005";
        int expectedResult = 3;

        //when
        int result = StringCalculator.add(inputForTest);

        //then
        assertEquals(expectedResult, result);
    }

    @Test
    void shouldIgnoreNumberGreaterThan1000AndReturn6WhenAddNumbersWithCustomDelimiter() {
        //given
        String inputForTest = "//|\n1|2|3|1004";
        int expectedResult = 6;

        //when
        int result = StringCalculator.add(inputForTest);

        //then
        assertEquals(expectedResult, result);
    }

}