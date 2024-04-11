// Copyright (c) Benjamin Bergman 2024package com.pluralsight;

import java.util.Scanner;

public class PayrollCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("What is your name? ");
        String name = sc.nextLine();

        System.out.print("How many hours have you worked? ");
        float hours = sc.nextFloat();

        System.out.print("What is your pay rate? ");
        float rate = sc.nextFloat();

        System.out.println();
        System.out.printf("%s, your gross pay is %.2f.%n", name, calculate(hours, rate));
    }

    private static float calculate(float hours, float rate) {
        return Math.min(hours, 40) * rate  // Normal rate up to 40 hours
               + (Math.max(hours, 40) - 40) * 1.5f * rate; // Any overtime at 1.5x rate
    }
}
