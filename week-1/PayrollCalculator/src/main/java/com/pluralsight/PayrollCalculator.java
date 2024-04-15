// Copyright (c) Benjamin Bergman 2024

package com.pluralsight;

import java.util.*;

public final class PayrollCalculator {
    private PayrollCalculator() {
    }

    public static void main(final String[] args) {
        final Scanner sc = new Scanner(System.in);
        System.out.print("What is your name? ");
        final String name = sc.nextLine();

        System.out.print("How many hours have you worked? ");
        final float hours = sc.nextFloat();

        System.out.print("What is your pay rate? ");
        final float rate = sc.nextFloat();

        System.out.println();
        System.out.printf("%s, your gross pay is %.2f.%n", name, calculate(hours, rate));
        System.out.printf("Howdy %s, your gross pay is %.2f.%n", name, hours * rate);
    }

    private static float calculate(final float hours, final float rate) {
        return (Math.min(hours, 40) * rate)  // Normal rate up to 40 hours
               + ((Math.max(hours, 40) - 40) * 1.5f * rate); // Any overtime at 1.5x rate
    }
}
