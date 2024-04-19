// Copyright (c) Benjamin Bergman 2024.

package com.pluralsight;

import com.pluralsight.abilities.*;

import java.util.*;

public final class Enemy {
    private SpecialAbility ability;
    private int health = 100;
    private String name;
    private int damage;

    public Enemy(String name) {
        this(name, 8);
    }

    public Enemy(String name, int damage) {
        this(name, 8, SpecialAbility.none());
    }

    public Enemy(String name, int damage, SpecialAbility ability) {
        this.name = name;
        this.damage = damage;
        setAbility(ability);
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = Math.max(health, 0);
    }

    public String getName() {
        return ability.name().isEmpty() ? name : (name + ' ' + ability.name());
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public OptionalInt attack(Character c) {
        return attack(c, getAbility().calculate(c, this));
    }

    public OptionalInt attack(Character c, float specialAbilityMultiplier) {
        var damage = (int) (getDamage() * specialAbilityMultiplier);
        c.setHealth(c.getHealth() - damage);
        return c.dodge() ? OptionalInt.empty() : OptionalInt.of(damage);
    }

    public SpecialAbility getAbility() {
        return ability;
    }

    public void setAbility(SpecialAbility ability) {
        this.ability = ability;
    }
}
