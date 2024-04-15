// Copyright (c) Benjamin Bergman 2024

package com.pluralsight;

import java.util.stream.*;

public class ShoppingList {
    public static void main(String[] args) {
        final String[] items = new String[]
                {
                        "Eggs", "Milk", "Bread", "Chicken", "Sauce",
                        "Soup", "Vegetables", "Apples", "Celery", "Juice"
                };
//        final String[] items = IntStream.range(0, 10).mapToObj(x -> items_i).flatMap(Arrays::stream).toArray(String[]::new);
        final String format = "%" + digitsIn(items.length) + "d. %s";
        System.out.println("My shopping list (" + items.length + " items):");
        System.out.println(
                IntStream.range(0, items.length)
                        .mapToObj(i -> String.format(format, i + 1, items[i]))
                        .collect(Collectors.joining(System.lineSeparator())));
//        System.out.println("1. Eggs\n2. Milk\n3. Bread\n4. Chicken\n5. Sauce\n6. Soup\n7. Vegetables\n8. Apples\n9. Celery\n10.Juice");
    }

    private static int digitsIn(int x) {
        return (int) Math.floor(Math.log10(x) + 1);
    }
}
