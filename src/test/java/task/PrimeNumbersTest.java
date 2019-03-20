package task;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PrimeNumbersTest {
    static Stream givenNumbers() {
        return Stream.of(
                Arguments.of(1, false),
                Arguments.of(2, true),
                Arguments.of(3, true),
                Arguments.of(11, true),
                Arguments.of(13, true),
                Arguments.of(125, false),
                Arguments.of(1300, false),
                Arguments.of(122, false)
        );
    }

    @ParameterizedTest
    @MethodSource(value = "givenNumbers")
    void whetherTheNumberIsThePrimeNumber(int number, boolean expectedValue) {
        boolean value = PrimeNumbers.IsPrimeNumber(number);

        assertEquals(expectedValue, value);
    }

    @ParameterizedTest
    @CsvSource({"1,false", "2, true", "53, true", "100, false", "59, true"})
    void OrMyNumberIsPrime(int number, boolean expected) {
        boolean isPrime = PrimeNumbers.IsPrimeNumber(number);

        assertEquals(expected, isPrime);
    }

}