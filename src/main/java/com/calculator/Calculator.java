package com.calculator;

/**
 * A comprehensive calculator class with various arithmetic operations.
 * Supports basic operations, advanced operations, and scientific calculations.
 */
public class Calculator {

    /**
     * Adds two numbers.
     * @param a First number
     * @param b Second number
     * @return Sum of a and b
     */
    public double add(double a, double b) {
        return a + b;
    }

    /**
     * Subtracts second number from first number.
     * @param a First number
     * @param b Second number
     * @return Difference of a and b
     */
    public double subtract(double a, double b) {
        return a - b;
    }

    /**
     * Multiplies two numbers.
     * @param a First number
     * @param b Second number
     * @return Product of a and b
     */
    public double multiply(double a, double b) {
        return a * b;
    }

    /**
     * Divides first number by second number.
     * @param a Dividend
     * @param b Divisor
     * @return Quotient of a divided by b
     * @throws ArithmeticException if b is zero
     */
    public double divide(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        }
        return a / b;
    }

    /**
     * Calculates the power of a number.
     * @param base Base number
     * @param exponent Exponent
     * @return base raised to the power of exponent
     */
    public double power(double base, double exponent) {
        return Math.pow(base, exponent);
    }

    /**
     * Calculates the square root of a number.
     * @param a Number to find square root of
     * @return Square root of a
     * @throws ArithmeticException if a is negative
     */
    public double sqrt(double a) {
        if (a < 0) {
            throw new ArithmeticException("Cannot calculate square root of negative number");
        }
        return Math.sqrt(a);
    }

    /**
     * Calculates the modulo (remainder) of division.
     * @param a Dividend
     * @param b Divisor
     * @return Remainder of a divided by b
     * @throws ArithmeticException if b is zero
     */
    public double modulo(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Cannot perform modulo with zero divisor");
        }
        return a % b;
    }

    /**
     * Calculates the factorial of a non-negative integer.
     * @param n Number to calculate factorial of
     * @return Factorial of n
     * @throws IllegalArgumentException if n is negative
     */
    public long factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Factorial is not defined for negative numbers");
        }
        if (n == 0 || n == 1) {
            return 1;
        }
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    /**
     * Calculates the absolute value of a number.
     * @param a Number
     * @return Absolute value of a
     */
    public double abs(double a) {
        return Math.abs(a);
    }

    /**
     * Returns the maximum of two numbers.
     * @param a First number
     * @param b Second number
     * @return Maximum of a and b
     */
    public double max(double a, double b) {
        return Math.max(a, b);
    }

    /**
     * Returns the minimum of two numbers.
     * @param a First number
     * @param b Second number
     * @return Minimum of a and b
     */
    public double min(double a, double b) {
        return Math.min(a, b);
    }

    /**
     * Rounds a number to the nearest integer.
     * @param a Number to round
     * @return Rounded value
     */
    public long round(double a) {
        return Math.round(a);
    }

    /**
     * Main method for demonstration purposes.
     */
    public static void main(String[] args) {
        Calculator calc = new Calculator();
        
        System.out.println("=== Calculator Demo ===");
        System.out.println("5 + 3 = " + calc.add(5, 3));
        System.out.println("10 - 4 = " + calc.subtract(10, 4));
        System.out.println("6 * 7 = " + calc.multiply(6, 7));
        System.out.println("20 / 4 = " + calc.divide(20, 4));
        System.out.println("2^8 = " + calc.power(2, 8));
        System.out.println("√16 = " + calc.sqrt(16));
        System.out.println("17 % 5 = " + calc.modulo(17, 5));
        System.out.println("5! = " + calc.factorial(5));
        System.out.println("|−15| = " + calc.abs(-15));
        System.out.println("max(10, 20) = " + calc.max(10, 20));
        System.out.println("min(10, 20) = " + calc.min(10, 20));
        System.out.println("round(7.6) = " + calc.round(7.6));
    }
}
