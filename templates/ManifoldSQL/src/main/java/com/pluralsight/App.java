/*
 * Copyright (c) Benjamin Bergman 2024.
 */

package com.pluralsight;

public class App {
    public static void main(String[] args) {
        for (var row : "[.sql/] SELECT * FROM products".fetch())
            System.out.println(row);
    }
}
