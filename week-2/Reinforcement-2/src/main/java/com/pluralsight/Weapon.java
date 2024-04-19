// Copyright (c) Benjamin Bergman 2024.

package com.pluralsight;

final class Weapon {
    private static final String[] ADJECTIVES = ";Magic ;Flame ;Ice ;Electric ;Powerful ;Rare ;Legendary "
            .split(";");
    private static final String[] NOUNS = "Sword;Greatsword;Staff;Spear;Bow;Katana;Bola;Slingshot"
            .split(";");
    private final String name;
    private final float strength;

    private Weapon(String name, float strength) {
        this.name = name;
        this.strength = strength;
    }

    public static Weapon random(float quality) {
        var name = ADJECTIVES[(int) (Math.random() * ADJECTIVES.length)] + NOUNS[(int) (Math.random() * NOUNS.length)];
        return new Weapon(name, ((float) Math.random() + quality) - 0.5f);
    }

    public static Weapon fists() {
        return new Weapon("Your Fists", 1);
    }

    public String name() {
        return name;
    }

    public float strength() {
        return strength;
    }

    @Override
    public String toString() {
        return "%s, %.2f strength".formatted(name, strength);
    }
}
