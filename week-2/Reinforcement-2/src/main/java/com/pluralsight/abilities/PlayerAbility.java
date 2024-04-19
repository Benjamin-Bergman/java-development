// Copyright (c) Benjamin Bergman 2024.

package com.pluralsight.abilities;

import com.pluralsight.Character;
import com.pluralsight.*;

import java.util.function.*;

public final class PlayerAbility implements SpecialAbility {
    private final String name;
    private final String description;
    private final ToDoubleFunction<? super Character> func;

    public PlayerAbility(String name, String description,
                         ToDoubleFunction<? super Character> func) {
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
        return (float) func.applyAsDouble(player);
    }
}
