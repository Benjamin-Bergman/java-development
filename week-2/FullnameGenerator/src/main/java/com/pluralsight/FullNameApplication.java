// Copyright (c) Benjamin Bergman 2024.

package com.pluralsight;

import java.util.*;

@SuppressWarnings({"UtilityClass", "MissingJavadoc"})
public final class FullNameApplication {
    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            System.out.println("Please enter your name.");

            System.out.print("First: ");
            String first = in.nextLine();

            System.out.print("Middle: ");
            String middle = in.nextLine();

            System.out.print("Last: ");
            String last = in.nextLine();

            System.out.print("Suffix: ");
            String suffix = in.nextLine();

            System.out.println();
            System.out.println("Full name: " + new FullName(first, middle, last, suffix));
        }
    }
}
