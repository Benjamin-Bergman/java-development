// Copyright (c) Benjamin Bergman 2024.

package com.pluralsight.abilities;

import com.pluralsight.Character;
import com.pluralsight.*;

import java.util.function.*;

public final class ComplexAbility implements SpecialAbility {
    private final String name;
    private final String description;
    private final ToDoubleBiFunction<? super Character, ? super Enemy> func;

    public ComplexAbility(String name, String description,
                          ToDoubleBiFunction<? super Character, ? super Enemy> func) {
        this.name = name;
        this.description = description;
        this.func = func;
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
        return (float) func.applyAsDouble(player, enemy);
    }
}
