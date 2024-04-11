package com.pluralsight;

import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.BinaryOperator;
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

        System.out.printf("%.2f %s %.2f = %.2f%n", a, op, b, op.run(a, b));
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
        do op = f.get(); while (op.isEmpty());
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
        Multiply((a, b) -> a * b),
        /**
         * Divide two numbers.
         */
        Divide((a, b) -> a / b),
        /**
         * Add two numbers.
         */
        Add(Float::sum),
        /**
         * Subtract two numbers.
         */
        Subtract((a, b) -> a - b);

        private final BinaryOperator<Float> func;

        Operation(BinaryOperator<Float> f) {
            func = f;
        }

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

        public float run(float a, float b) {
            return func.apply(a, b);
        }

        @Override
        public String toString() {
            return switch (this) {
                case Multiply -> "*";
                case Divide -> "/";
                case Add -> "+";
                case Subtract -> "-";
            };
        }
    }
}
