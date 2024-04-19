// Copyright (c) Benjamin Bergman 2024.

package com.pluralsight;

final class Character {
    private int health = 100;
    private String name;
    private int strength;
    private int agility;

    public Character(String name) {
        this(name, 30, 15);
    }

    public Character(String name, int strength, int agility) {
        setName(name);
        setStrength(strength);
        setAgility(agility);
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

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public String getDescription() {
        var s = getName();
        return s.substring(0, 1).toUpperCase()
               + s.substring(1).toLowerCase();
    }

    public String getHealthStatus() {
        return "%d HP".formatted(health);
    }

    public String getFullStatus() {
        return """
                %s:
                %s
                %d STR
                %d AGI"""
                .formatted(getDescription(), getHealthStatus(), getStrength(), getAgility());
    }

    public int attack(Enemy e) {
        return attack(e, 1);
    }

    public int attack(Enemy e, float weaponMultiplier) {
        var damage = (int) (getStrength() * weaponMultiplier);
        e.setHealth(e.getHealth() - damage);
        return damage;
    }
}
