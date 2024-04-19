// Copyright (c) Benjamin Bergman 2024.

package com.pluralsight.abilities;

import com.pluralsight.*;

import java.util.function.*;

public final class EnemyAbility implements SpecialAbility {
    private final String name;
    private final String description;
    private final ToDoubleFunction<? super Enemy> func;

    public EnemyAbility(String name, String description,
                        ToDoubleFunction<? super Enemy> func) {
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
    public float calculate(com.pluralsight.Character player, Enemy enemy) {
        return (float) func.applyAsDouble(enemy);
    }
}
