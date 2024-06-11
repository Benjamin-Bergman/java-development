/*
 * Copyright (c) Benjamin Bergman 2024.
 */

package com.pluralsight;

public class App {
    public static void main(String[] args) {
        "[.sql/] SELECT * FROM products".fetch().forEach(System.out::println);
    }
}
