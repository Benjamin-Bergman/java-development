// Copyright (c) Benjamin Bergman 2024.

package com.pluralsight.abilities;

import com.pluralsight.Character;
import com.pluralsight.*;

public class ExponentialAbility implements SpecialAbility {
    private final String name;
    private final String description;
    private final double factor, constant;
    private double previous;

    public ExponentialAbility(String name, String description, double start, double factor, double constant) {
        this.name = name;
        this.description = description;
        previous = start;
        this.factor = factor;
        this.constant = constant;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String description() {
        return description;
    }

    @Override
    public float calculate(Character player, Enemy enemy) {
        previous *= factor;
        return (float) (constant + previous);
    }
}
