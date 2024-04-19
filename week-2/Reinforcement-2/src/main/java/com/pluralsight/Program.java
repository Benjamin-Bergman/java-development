// Copyright (c) Benjamin Bergman 2024.

package com.pluralsight;

import java.util.*;

public class Program {
    private static final String[] ENEMY_NAMES = "Goblin;Dragon;Orc;Warrior;Knight;Vampire;Werewolf;Lizard;Cockroach;Drake;Dinosaur"
            .split(";");

    public static void main(String[] args) {
        var player = new Character("Ben");
        var enemies = chooseEnemies(3);

        System.out.println(player.getFullStatus());
        System.out.println();
        System.out.println(Arrays.stream(enemies).reduce(
                new StringBuilder(),
                (sb, e) -> sb.append(e.getName()).append(": ").append(e.getHealth()).append(" HP, ").append(e.getDamage()).append(" ATK").append(System.lineSeparator()),
                StringBuilder::append));

        battle(player, enemies);
    }

    private static Enemy[] chooseEnemies(int count) {
        var enemies = new Enemy[count];
        Arrays.setAll(enemies, i -> chooseEnemy());
        return enemies;
    }

    private static Enemy chooseEnemy() {
        var name = ENEMY_NAMES[(int) (Math.random() * ENEMY_NAMES.length)];
        var enemy = new Enemy(name, (int) (Math.random() * 11) + 5);
        enemy.setHealth((int) (Math.random() * 50) + 75);
        return enemy;
    }

    private static boolean battle(Character player, Enemy[] enemies) {
        for (var enemy : enemies)
            if (!battle(player, enemy)) {
                System.out.println("You died...");
                return false;
            }
        System.out.println("You win!");
        return true;
    }

    private static boolean battle(Character player, Enemy enemy) {
        while ((player.getHealth() > 0) && (enemy.getHealth() > 0)) {
            int p = player.attack(enemy);
            int e = enemy.attack(player);
            System.out.printf(
                    "%s dealt %d damage to the %s, but took %d damage.%n%d Health remaining (%d damage left to do)%n",
                    player.getDescription(),
                    p,
                    enemy.getName(),
                    e,
                    player.getHealth(),
                    enemy.getHealth());
        }
        if (enemy.getHealth() == 0)
            System.out.printf("The %s has been defeated!%n%n", enemy.getName());
        return player.getHealth() != 0;
    }
}
