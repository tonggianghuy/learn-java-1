package com.learn;

/**
 * A simple calculator class for basic arithmetic operations.
 */
public class Calculator {

    /**
     * Adds two numbers.
     */
    public double add(double a, double b) {
        return a + b;
    }

    /**
     * Subtracts b from a.
     */
    public double subtract(double a, double b) {
        return a - b;
    }

    /**
     * Multiplies two numbers.
     */
    public double multiply(double a, double b) {
        return a * b;
    }

    /**
     * Divides a by b.
     * @throws ArithmeticException if b is zero
     */
    public double divide(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        }
        return a / b;
    }

    /**
     * Calculates the modulo (remainder) of a divided by b.
     * @throws ArithmeticException if b is zero
     */
    public double modulo(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Cannot calculate modulo with zero divisor");
        }
        return a % b;
    }

    /**
     * Calculates a raised to the power of b (a^b).
     */
    public double power(double a, double b) {
        return Math.pow(a, b);
    }

    /**
     * Calculates the square root of a number.
     * @throws IllegalArgumentException if a is negative
     */
    public double squareRoot(double a) {
        if (a < 0) {
            throw new IllegalArgumentException("Cannot calculate square root of negative number");
        }
        return Math.sqrt(a);
    }
}
