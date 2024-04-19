// Copyright (c) Benjamin Bergman 2024.

package com.pluralsight;

import java.util.*;
import java.util.function.*;
import java.util.regex.*;

final class Program {
    private static final String[] ENEMY_NAMES = "Goblin;Dragon;Orc;Warrior;Knight;Vampire;Werewolf;Lizard;Cockroach;Drake;Dinosaur"
            .split(";");
    private static final Pattern YN_PATTERN = Pattern.compile("^(?:y(?:es)?|no?)$", Pattern.CASE_INSENSITIVE);

    public static void main(String[] args) throws InterruptedException {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Hello, challenger! Please enter your name: ");
            var name = sc.nextLine();
            var player = new Character(name);
            System.out.print("How many enemies would you like to face today?: ");
            OptionalInt enemyCount;
            do enemyCount = tryReadInt(sc, count -> count > 0);
            while (enemyCount.isEmpty());
            sc.nextLine();

            var enemies = chooseEnemies(enemyCount.getAsInt());

            if (enemyCount.getAsInt() == 1)
                System.out.printf("%nHere is the upcoming enemy:%n");
            else
                System.out.printf("%nHere are the upcoming enemies:%n");

            System.out.println(Arrays.stream(enemies).reduce(
                    new StringBuilder(),
                    (sb, e) -> sb.append(e.getName()).append(": ").append(e.getHealth()).append(" HP, ").append(e.getDamage()).append(" ATK").append(System.lineSeparator()),
                    StringBuilder::append));

            System.out.print("Here are your stats, ");
            System.out.println(player.getFullStatus());

            if (queryYN(sc, "Would you like to customize them? [y/n]: "))
                customize(player, sc);
            System.out.printf("Let the battle%s begin!%n", (enemies.length == 1) ? "" : "s");
            System.out.println();
            battle(player, enemies, sc);
        }
    }

    private static boolean queryYN(Scanner sc, String message) {
        System.out.print(message);

        while (true) {
            String command = sc.nextLine().toLowerCase();
            if (YN_PATTERN.asPredicate().test(command))
                return command.charAt(0) == 'y';
            System.out.printf("Unknown input \"%s\". Please try again.%n%s", command, message);
        }
    }

    private static void customize(Character player, Scanner sc) {
        while (true) {
            System.out.println("""
                    What to change?
                    (1) Name
                    (2) Strength
                    (3) Health
                    (4) Agility
                    (X) Stop customizing""");
            var command = sc.nextLine().trim();
            switch (command.toLowerCase()) {
                case "1":
                    System.out.print("Enter the new name (empty to cancel): ");
                    var name = sc.nextLine();
                    if (!name.isEmpty() && !name.isBlank()) {
                        player.setName(name);
                        System.out.printf("Your name is now %s.%n", player.getDescription());
                    } else System.out.println("Name not changed.");
                    break;
                case "2":
                    System.out.print("Enter the new strength (empty to cancel): ");
                    var str = tryParse(sc.nextLine());
                    if (str.isPresent() && (str.getAsInt() > 0)) {
                        player.setStrength(str.getAsInt());
                        System.out.printf("Your strength is now %d.%n", player.getStrength());
                    } else System.out.println("Strength not changed.");
                    break;
                case "3":
                    System.out.print("Enter the new health (empty to cancel): ");
                    var hlt = tryParse(sc.nextLine());
                    if (hlt.isPresent() && (hlt.getAsInt() > 0)) {
                        player.setHealth(hlt.getAsInt());
                        System.out.printf("Your health is now %d.%n", player.getHealth());
                    } else System.out.println("Health not changed.");
                    break;
                case "4":
                    System.out.print("Enter the new agility (empty to cancel): ");
                    var agi = tryParse(sc.nextLine());
                    if (agi.isPresent() && (agi.getAsInt() > 0)) {
                        player.setAgility(agi.getAsInt());
                        System.out.printf("Your agility is now %d.%n", player.getAgility());
                    } else System.out.println("Agility not changed.");
                    break;
                case "x":
                    if (queryYN(sc, "Are you sure you want to stop customizing? [y/n]: "))
                        return;
                    break;
                default:
                    System.out.printf("Unknown option \"%s\".%n", command);
                    break;
            }
        }
    }

    private static OptionalInt tryParse(String s) {
        try {
            return OptionalInt.of(Integer.parseInt(s));
        } catch (NumberFormatException e) {
            return OptionalInt.empty();
        }
    }

    private static OptionalInt tryReadInt(Scanner sc, IntPredicate filter) {
        try {
            int res = sc.nextInt();
            if (filter.test(res))
                return OptionalInt.of(res);
            System.out.print("Bad number! Please try again.: ");
            return OptionalInt.empty();
        } catch (InputMismatchException e) {
            sc.next();
            System.out.print("Error! Please try again.: ");
            return OptionalInt.empty();
        }
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

    private static boolean battle(Character player, Enemy[] enemies, Scanner sc) throws InterruptedException {
        for (var enemy : enemies) {
            if (!battle(player, enemy, sc)) {
                System.out.println("You died...");
                return false;
            }
            Thread.sleep(1_000);
        }
        System.out.println("You win!");
        return true;
    }

    private static boolean battle(Character player, Enemy enemy, Scanner sc) throws InterruptedException {
        while ((player.getHealth() > 0) && (enemy.getHealth() > 0)) {
            int p = player.attack(enemy);
            var e = enemy.attack(player);
            if (e.isPresent())
                System.out.printf(
                        "%s dealt %d damage to the %s, but took %d damage.%n%d Health remaining (%d damage left to do)%n",
                        player.getDescription(),
                        p,
                        enemy.getName(),
                        e.getAsInt(),
                        player.getHealth(),
                        enemy.getHealth());
            else
                System.out.printf(
                        "%s dealt %d damage to the %s. You dodged the enemy attack!%n(%d damage left to do)%n",
                        player.getDescription(),
                        p,
                        enemy.getName(),
                        enemy.getHealth());
            Thread.sleep((long) (Math.random() * 500) + 500);
        }
        if (enemy.getHealth() == 0)
            System.out.printf("The %s has been defeated!%n%n", enemy.getName());

        if ((player.getHealth() != 0) && (Math.random() < 0.5)) {
            var current = player.getWeapon();
            var newWeapon = Weapon.random(current.strength());
            System.out.printf("The enemy dropped a weapon:%n%s%nYou currently have:%n%s%n", newWeapon, current);
            if (queryYN(sc, "Would you like to take the new weapon? [y/n]: ")) {
                player.setWeapon(newWeapon);
                System.out.printf("You are now using the %s.%n", newWeapon);
            } else
                System.out.printf("You left the %s behind.%n", newWeapon);
        }

        return player.getHealth() != 0;
    }
}
