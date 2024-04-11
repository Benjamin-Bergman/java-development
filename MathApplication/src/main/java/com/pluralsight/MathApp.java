// Copyright (c) Benjamin Bergman 2024

package com.pluralsight;

public class MathApp {
    public static void main(String[] args) {
        float bobSalary = 10_000f, garySalary = 22_000f;
        float highestSalary = Math.max(bobSalary, garySalary);
        System.out.println("The highest salary is " + highestSalary);

        float carPrice = 6_000f, truckPrice = 8_500f, lowestPrice = Math.min(carPrice, truckPrice);
        System.out.println("The cheapest vehicle costs " + lowestPrice);

        double r = 7.25;
        System.out.println("The area is " + (Math.PI * r * r));

        double x = 5;
        System.out.println("The square root of x is " + Math.sqrt(x));

        float x1 = 5, y1 = 10, x2 = 85, y2 = 50;
        float dx = x2 - x1, dy = y2 - y1;
        System.out.println("The distance is " + Math.sqrt(dx * dx + dy * dy));

        float v = -3.8f;
        System.out.println("That absolute value is " + Math.abs(v));

        System.out.println("You random number is " + Math.random());
    }
}
