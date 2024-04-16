// Copyright (c) Benjamin Bergman 2024.

package com.pluralsight;

import java.util.*;
import java.util.regex.*;

@SuppressWarnings({"WeakerAccess", "StaticMethodOnlyUsedInOneClass"})
class FullName {
    private static final Pattern PARSE_FORMAT = Pattern.compile("([^\\s,]+)\\s+(?:([^\\s,]+)\\s+)?([^\\s,]+)(?:,\\s+([^\\s,]+))?");
    private final String firstName;
    private final String lastName;
    private final String middleName;
    private final String nameSuffix;

    FullName(String first, String middle, String last, String suffix) {
        firstName = clean(Objects.requireNonNull(first)).orElseThrow();
        middleName = clean(middle).orElse(null);
        lastName = clean(Objects.requireNonNull(last)).orElseThrow();
        nameSuffix = clean(suffix).orElse(null);
    }

    private static Optional<String> clean(String s) {
        if (s == null) return Optional.empty();
        String trimmed = s.trim();
        return trimmed.isEmpty() ? Optional.empty() : Optional.of(trimmed);
    }

    public static Optional<FullName> parse(CharSequence fullName) {
        var matcher = PARSE_FORMAT.matcher(fullName);
        if (!matcher.find())
            return Optional.empty();

        return Optional.of(new FullName(matcher.group(1), matcher.group(2), matcher.group(3), matcher.group(4)));
    }

    @SuppressWarnings("SuspiciousGetterSetter")
    public String getFirst() {
        return firstName;
    }

    @SuppressWarnings("SuspiciousGetterSetter")
    public String getLast() {
        return lastName;
    }

    public Optional<String> getMiddle() {
        return Optional.ofNullable(middleName);
    }

    public Optional<String> getSuffix() {
        return Optional.ofNullable(nameSuffix);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(firstName);

        Optional.ofNullable(middleName).ifPresent(s -> builder.append(' ').append(s));

        builder.append(' ').append(lastName);

        Optional.ofNullable(nameSuffix).ifPresent(s -> builder.append(", ").append(s));

        return builder.toString();
    }
}
