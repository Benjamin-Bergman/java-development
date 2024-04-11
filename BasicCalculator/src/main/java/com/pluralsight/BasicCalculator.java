package com.pluralsight;

import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Represents a simple four-function calculator command line application.
 */
public class BasicCalculator {
    /**
     * Runs a basic calculator program.
     *
     * @param args Unused
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        float a = untilNonEmpty(askFor("First number: ", () -> tryRead(sc::nextFloat, e -> {
            System.out.println("Bad number format! Please try again.");
            sc.next();
        })));
        float b = untilNonEmpty(askFor("Second number: ", () -> tryRead(sc::nextFloat, e -> {
            System.out.println("Bad number format! Please try again.");
            sc.next();
        })));

        //noinspection SpellCheckingInspection
        Operation op = untilNonEmpty(askFor("""
                Possible calculations:
                (A)dd
                (S)ubtract
                (M)ultiply
                (D)ivide
                Please select an option:\s""", () -> tryRead(() -> getOperation(sc.next()), e -> System.out.println("Bad operation! Please try again."))));

        System.out.println();

        switch (op) {
            case Multiply -> System.out.printf("%.2f * %.2f = %.2f%n", a, b, a * b);
            case Divide -> System.out.printf("%.2f / %.2f = %.2f%n", a, b, a / b);
            case Add -> System.out.printf("%.2f + %.2f = %.2f%n", a, b, a + b);
            case Subtract -> System.out.printf("%.2f - %.2f = %.2f%n", a, b, a - b);
        }
    }
    
    /**
     * Gets an Operation from a String.
     *
     * @param s The string to parse.
     * @return The operation represented by s.
     */
    private static Operation getOperation(String s) throws InputMismatchException {
        if (s.length() != 1) throw new InputMismatchException();

        return Operation.from(s.charAt(0));
    }

    /**
     * Generates a function which prints a message to stdout, then runs f.
     *
     * @param message The message to print.
     * @param f       The function to run.
     * @param <T>     The type returned by f.
     * @return The return value of f.
     */
    private static <T> Supplier<T> askFor(String message, Supplier<T> f) {
        return () -> {
            System.out.print(message);
            return f.get();
        };
    }

    /**
     * Repeats an operation until it returns a non-empty value.
     *
     * @param f   The function which may or may not return a value.
     * @param <T> The type optionally returned by f.
     * @return The first return value of f which is not empty.
     */
    private static <T> T untilNonEmpty(Supplier<Optional<T>> f) {
        Optional<T> op;
        do {
            op = f.get();
        } while (op.isEmpty());
        return op.get();
    }

    /**
     * Safely runs a function that may throw.
     *
     * @param f         The function to run.
     * @param onFailure The function to run if f throws.
     * @param <T>       The type returned by f.
     * @return The return value of f. If f throws, Optional.empty() instead.
     */
    private static <T> Optional<T> tryRead(Supplier<T> f, Consumer<Throwable> onFailure) {
        try {
            return Optional.of(f.get());
        } catch (Exception e) {
            onFailure.accept(e);
            return Optional.empty();
        }
    }

    /**
     * Represents an operation in a four-function calculator.
     */
    private enum Operation {
        /**
         * Multiply two numbers.
         */
        Multiply,
        /**
         * Divide two numbers.
         */
        Divide,
        /**
         * Add two numbers.
         */
        Add,
        /**
         * Subtract two numbers.
         */
        Subtract;

        /**
         * Gets an Operation from a representative byte.
         *
         * @param op The byte to check.
         * @return An Operation based on the give byte.
         */
        public static Operation from(char op) throws InputMismatchException {
            return switch (op) {
                case 'a', 'A' -> Add;
                case 's', 'S' -> Subtract;
                case 'm', 'M' -> Multiply;
                case 'd', 'D' -> Divide;
                default -> throw new InputMismatchException();
            };
        }
    }
}
