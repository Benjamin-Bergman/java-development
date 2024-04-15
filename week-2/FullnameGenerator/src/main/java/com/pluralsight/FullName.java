// Copyright (c) Benjamin Bergman 2024.

package com.pluralsight;

import java.util.*;

@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
class FullName {
    private final String firstName;
    private final String lastName;
    private final Optional<String> middleName;
    private final Optional<String> nameSuffix;

    FullName(String first, String middle, String last, String suffix) {
        firstName = clean(Objects.requireNonNull(first)).orElseThrow();
        middleName = clean(middle);
        lastName = clean(Objects.requireNonNull(last)).orElseThrow();
        nameSuffix = clean(suffix);
    }

    private static Optional<String> clean(String s) {
        if (s == null) return Optional.empty();
        String trimmed = s.trim();
        return trimmed.isEmpty() ? Optional.empty() : Optional.of(trimmed);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(firstName);

        middleName.ifPresent(s -> builder.append(' ').append(s));

        builder.append(' ').append(lastName);

        nameSuffix.ifPresent(s -> builder.append(", ").append(s));

        return builder.toString();
    }
}
