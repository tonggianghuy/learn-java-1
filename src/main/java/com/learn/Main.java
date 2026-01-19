package com.learn;

/**
 * Main class to demonstrate calculator functionality.
 */
public class Main {
    public static void main(String[] args) {
        Calculator calc = new Calculator();

        System.out.println("=== Calculator Demo ===");
        System.out.println();

        System.out.println("Basic Operations:");
        System.out.println("10 + 5 = " + calc.add(10, 5));
        System.out.println("10 - 5 = " + calc.subtract(10, 5));
        System.out.println("10 * 5 = " + calc.multiply(10, 5));
        System.out.println("10 / 5 = " + calc.divide(10, 5));

        System.out.println();
        System.out.println("Advanced Operations:");
        System.out.println("10 % 3 = " + calc.modulo(10, 3));
        System.out.println("2 ^ 8 = " + calc.power(2, 8));
        System.out.println("√144 = " + calc.squareRoot(144));
        System.out.println("5 ^ 3 = " + calc.power(5, 3));
        System.out.println("√25 = " + calc.squareRoot(25));
    }
}
