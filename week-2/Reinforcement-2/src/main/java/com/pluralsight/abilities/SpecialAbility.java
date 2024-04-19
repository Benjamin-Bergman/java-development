// Copyright (c) Benjamin Bergman 2024.

package com.pluralsight.abilities;

import com.pluralsight.Character;
import com.pluralsight.*;

public interface SpecialAbility {
    static SpecialAbility none() {
        return new PlayerAbility("", "", c -> 1);
    }

    String name();

    String description();

    float calculate(Character player, Enemy enemy);
}
