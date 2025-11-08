package com.calculator;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Comprehensive test suite for Calculator class with 50+ test cases.
 */
@DisplayName("Calculator Test Suite")
public class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Nested
    @DisplayName("Addition Tests")
    class AdditionTests {

        @Test
        @DisplayName("Should add two positive numbers")
        void testAddPositiveNumbers() {
            assertEquals(8.0, calculator.add(5, 3), 0.001);
        }

        @Test
        @DisplayName("Should add negative numbers")
        void testAddNegativeNumbers() {
            assertEquals(-8.0, calculator.add(-5, -3), 0.001);
        }

        @Test
        @DisplayName("Should add positive and negative numbers")
        void testAddMixedNumbers() {
            assertEquals(2.0, calculator.add(5, -3), 0.001);
        }

        @Test
        @DisplayName("Should add zero")
        void testAddZero() {
            assertEquals(5.0, calculator.add(5, 0), 0.001);
        }

        @Test
        @DisplayName("Should add decimal numbers")
        void testAddDecimals() {
            assertEquals(7.7, calculator.add(5.5, 2.2), 0.001);
        }
    }

    @Nested
    @DisplayName("Subtraction Tests")
    class SubtractionTests {

        @Test
        @DisplayName("Should subtract two positive numbers")
        void testSubtractPositiveNumbers() {
            assertEquals(2.0, calculator.subtract(5, 3), 0.001);
        }

        @Test
        @DisplayName("Should subtract negative numbers")
        void testSubtractNegativeNumbers() {
            assertEquals(-2.0, calculator.subtract(-5, -3), 0.001);
        }

        @Test
        @DisplayName("Should subtract to get negative result")
        void testSubtractToNegative() {
            assertEquals(-2.0, calculator.subtract(3, 5), 0.001);
        }

        @Test
        @DisplayName("Should subtract zero")
        void testSubtractZero() {
            assertEquals(5.0, calculator.subtract(5, 0), 0.001);
        }

        @Test
        @DisplayName("Should subtract decimal numbers")
        void testSubtractDecimals() {
            assertEquals(3.3, calculator.subtract(5.5, 2.2), 0.001);
        }
    }

    @Nested
    @DisplayName("Multiplication Tests")
    class MultiplicationTests {

        @Test
        @DisplayName("Should multiply two positive numbers")
        void testMultiplyPositiveNumbers() {
            assertEquals(15.0, calculator.multiply(5, 3), 0.001);
        }

        @Test
        @DisplayName("Should multiply negative numbers")
        void testMultiplyNegativeNumbers() {
            assertEquals(15.0, calculator.multiply(-5, -3), 0.001);
        }

        @Test
        @DisplayName("Should multiply by negative number")
        void testMultiplyByNegative() {
            assertEquals(-15.0, calculator.multiply(5, -3), 0.001);
        }

        @Test
        @DisplayName("Should multiply by zero")
        void testMultiplyByZero() {
            assertEquals(0.0, calculator.multiply(5, 0), 0.001);
        }

        @Test
        @DisplayName("Should multiply decimal numbers")
        void testMultiplyDecimals() {
            assertEquals(12.1, calculator.multiply(5.5, 2.2), 0.001);
        }
    }

    @Nested
    @DisplayName("Division Tests")
    class DivisionTests {

        @Test
        @DisplayName("Should divide two positive numbers")
        void testDividePositiveNumbers() {
            assertEquals(2.5, calculator.divide(5, 2), 0.001);
        }

        @Test
        @DisplayName("Should divide negative numbers")
        void testDivideNegativeNumbers() {
            assertEquals(2.5, calculator.divide(-5, -2), 0.001);
        }

        @Test
        @DisplayName("Should divide by negative number")
        void testDivideByNegative() {
            assertEquals(-2.5, calculator.divide(5, -2), 0.001);
        }

        @Test
        @DisplayName("Should throw exception when dividing by zero")
        void testDivideByZero() {
            assertThrows(ArithmeticException.class, () -> calculator.divide(5, 0));
        }

        @Test
        @DisplayName("Should divide decimal numbers")
        void testDivideDecimals() {
            assertEquals(2.5, calculator.divide(5.5, 2.2), 0.001);
        }
    }

    @Nested
    @DisplayName("Power Tests")
    class PowerTests {

        @Test
        @DisplayName("Should calculate positive power")
        void testPositivePower() {
            assertEquals(8.0, calculator.power(2, 3), 0.001);
        }

        @Test
        @DisplayName("Should calculate power of zero")
        void testPowerOfZero() {
            assertEquals(1.0, calculator.power(5, 0), 0.001);
        }

        @Test
        @DisplayName("Should calculate negative power")
        void testNegativePower() {
            assertEquals(0.125, calculator.power(2, -3), 0.001);
        }

        @Test
        @DisplayName("Should calculate decimal power")
        void testDecimalPower() {
            assertEquals(5.656, calculator.power(4, 1.5), 0.01);
        }

        @Test
        @DisplayName("Should calculate power of negative base")
        void testNegativeBasePower() {
            assertEquals(-8.0, calculator.power(-2, 3), 0.001);
        }
    }

    @Nested
    @DisplayName("Square Root Tests")
    class SquareRootTests {

        @Test
        @DisplayName("Should calculate square root of positive number")
        void testSqrtPositive() {
            assertEquals(4.0, calculator.sqrt(16), 0.001);
        }

        @Test
        @DisplayName("Should calculate square root of zero")
        void testSqrtZero() {
            assertEquals(0.0, calculator.sqrt(0), 0.001);
        }

        @Test
        @DisplayName("Should throw exception for negative square root")
        void testSqrtNegative() {
            assertThrows(ArithmeticException.class, () -> calculator.sqrt(-16));
        }

        @Test
        @DisplayName("Should calculate square root of decimal")
        void testSqrtDecimal() {
            assertEquals(3.316, calculator.sqrt(11), 0.01);
        }
    }

    @Nested
    @DisplayName("Modulo Tests")
    class ModuloTests {

        @Test
        @DisplayName("Should calculate modulo of positive numbers")
        void testModuloPositive() {
            assertEquals(2.0, calculator.modulo(17, 5), 0.001);
        }

        @Test
        @DisplayName("Should calculate modulo with negative dividend")
        void testModuloNegativeDividend() {
            assertEquals(-2.0, calculator.modulo(-17, 5), 0.001);
        }

        @Test
        @DisplayName("Should throw exception for modulo by zero")
        void testModuloByZero() {
            assertThrows(ArithmeticException.class, () -> calculator.modulo(17, 0));
        }

        @Test
        @DisplayName("Should calculate modulo of decimal numbers")
        void testModuloDecimals() {
            assertEquals(0.1, calculator.modulo(5.5, 2.7), 0.01);
        }
    }

    @Nested
    @DisplayName("Factorial Tests")
    class FactorialTests {

        @Test
        @DisplayName("Should calculate factorial of small number")
        void testFactorialSmall() {
            assertEquals(120, calculator.factorial(5));
        }

        @Test
        @DisplayName("Should calculate factorial of zero")
        void testFactorialZero() {
            assertEquals(1, calculator.factorial(0));
        }

        @Test
        @DisplayName("Should calculate factorial of one")
        void testFactorialOne() {
            assertEquals(1, calculator.factorial(1));
        }

        @Test
        @DisplayName("Should throw exception for negative factorial")
        void testFactorialNegative() {
            assertThrows(IllegalArgumentException.class, () -> calculator.factorial(-5));
        }

        @Test
        @DisplayName("Should calculate factorial of large number")
        void testFactorialLarge() {
            assertEquals(3628800, calculator.factorial(10));
        }
    }

    @Nested
    @DisplayName("Absolute Value Tests")
    class AbsoluteValueTests {

        @Test
        @DisplayName("Should return absolute value of positive number")
        void testAbsPositive() {
            assertEquals(5.0, calculator.abs(5), 0.001);
        }

        @Test
        @DisplayName("Should return absolute value of negative number")
        void testAbsNegative() {
            assertEquals(5.0, calculator.abs(-5), 0.001);
        }

        @Test
        @DisplayName("Should return zero for zero")
        void testAbsZero() {
            assertEquals(0.0, calculator.abs(0), 0.001);
        }
    }

    @Nested
    @DisplayName("Max/Min Tests")
    class MaxMinTests {

        @Test
        @DisplayName("Should return maximum of two numbers")
        void testMax() {
            assertEquals(10.0, calculator.max(5, 10), 0.001);
        }

        @Test
        @DisplayName("Should return minimum of two numbers")
        void testMin() {
            assertEquals(5.0, calculator.min(5, 10), 0.001);
        }

        @Test
        @DisplayName("Should handle equal numbers for max")
        void testMaxEqual() {
            assertEquals(5.0, calculator.max(5, 5), 0.001);
        }

        @Test
        @DisplayName("Should handle equal numbers for min")
        void testMinEqual() {
            assertEquals(5.0, calculator.min(5, 5), 0.001);
        }

        @Test
        @DisplayName("Should handle negative numbers for max")
        void testMaxNegative() {
            assertEquals(-5.0, calculator.max(-10, -5), 0.001);
        }

        @Test
        @DisplayName("Should handle negative numbers for min")
        void testMinNegative() {
            assertEquals(-10.0, calculator.min(-10, -5), 0.001);
        }
    }

    @Nested
    @DisplayName("Rounding Tests")
    class RoundingTests {

        @Test
        @DisplayName("Should round up")
        void testRoundUp() {
            assertEquals(8, calculator.round(7.6));
        }

        @Test
        @DisplayName("Should round down")
        void testRoundDown() {
            assertEquals(7, calculator.round(7.4));
        }

        @Test
        @DisplayName("Should round halfway up")
        void testRoundHalfway() {
            assertEquals(8, calculator.round(7.5));
        }

        @Test
        @DisplayName("Should round negative number")
        void testRoundNegative() {
            assertEquals(-8, calculator.round(-7.6));
        }
    }

    @Nested
    @DisplayName("Edge Cases and Integration Tests")
    class EdgeCasesTests {

        @Test
        @DisplayName("Should handle very large numbers")
        void testLargeNumbers() {
            assertEquals(2000000.0, calculator.add(1000000, 1000000), 0.001);
        }

        @Test
        @DisplayName("Should handle very small decimals")
        void testSmallDecimals() {
            assertEquals(0.0003, calculator.add(0.0001, 0.0002), 0.0001);
        }

        @Test
        @DisplayName("Should handle mixed operations")
        void testMixedOperations() {
            double result = calculator.add(calculator.multiply(5, 3), calculator.divide(10, 2));
            assertEquals(20.0, result, 0.001);
        }

        @Test
        @DisplayName("Should handle double precision")
        void testDoublePrecision() {
            assertEquals(0.3, calculator.add(0.1, 0.2), 0.001);
        }
    }
}
