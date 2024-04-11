package com.pluralsight;

import java.util.Scanner;

public class SandwichShop {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Sandwich size (1 or 2): ");
        int size = sc.nextInt();
        if (size != 1 && size != 2) throw new IllegalArgumentException("Sandwich size must be 1 or 2");
        System.out.print("Age (years): ");
        int age = sc.nextInt();
        if (age < 0) throw new IllegalArgumentException("Age cannot be negative");

        float price = size == 1 ? 5.45f : 8.95f;
        float discount = age <= 17 ? .9f : age >= 65 ? .8f : 1f;

        System.out.println();
        System.out.printf("You are paying $%.2f.%n", price * discount);
    }
}
