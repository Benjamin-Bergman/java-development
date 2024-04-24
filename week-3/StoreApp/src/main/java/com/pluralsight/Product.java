// Copyright (c) Benjamin Bergman 2024.

package com.pluralsight;

final class Product {
    private final int id;
    private final String name;
    private final double price;

    Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    int getId() {
        return id;
    }

    String getName() {
        return name;
    }

    double getPrice() {
        return price;
    }
}