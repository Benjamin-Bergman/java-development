// Copyright (c) Benjamin Bergman 2024.

package com.pluralsight;

final class Enemy {
    private int health = 100;
    private String name;
    private int damage;

    public Enemy(String name) {
        this(name, 8);
    }

    public Enemy(String name, int damage) {
        this.name = name;
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = Math.max(health, 0);
    }

    public String getName() {
        return name;
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

    public int attack(Character c) {
        return attack(c, 1);
    }

    public int attack(Character c, float specialAbilityMultiplier) {

        var damage = (int) (getDamage() * specialAbilityMultiplier);
        c.setHealth(c.getHealth() - damage);
        return damage;
    }
}
