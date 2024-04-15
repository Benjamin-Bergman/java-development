// Copyright (c) Benjamin Bergman 2024.

package com.pluralsight;

import java.util.*;

@SuppressWarnings({"MissingJavadoc", "UtilityClass", "FeatureEnvy"})
public final class FullNameParser {
    public static void main(String[] args) {
        System.out.print("What is your full name? ");
        try (Scanner sc = new Scanner(System.in)) {
            FullName.parse(sc.nextLine())
                    .ifPresentOrElse(name -> System.out.printf("""
                                            First: %s
                                            Middle: %s
                                            Last: %s
                                            Suffix: %s""",
                                    name.getFirst(),
                                    name.getMiddle().orElse("(None)"),
                                    name.getLast(),
                                    name.getSuffix().orElse("(None)")),

                            () -> System.out.println("Sorry, I don't understand."));
        }
    }
}
