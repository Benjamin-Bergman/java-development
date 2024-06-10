/*
 * Copyright (c) Benjamin Bergman 2024.
 */

package com.pluralsight;

import com.pluralsight.northwind.*;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class App {
    public static void main(String[] args) {
        try (var sc = new Scanner(System.in)) {
            while (true) {
                System.out.print("""
                    What do you want to do?
                      1) Display all products
                      2) Display all customers
                      3) Display all categories
                      0) Exit
                    Select an option:\s""");
                while (!sc.hasNextInt())
                    sc.next();
                var choice = sc.nextInt();

                switch (choice) {
                    case 0 -> {
                        System.out.println("Goodbye!");
                        return;
                    }
                    case 1 -> displayProducts();
                    case 2 -> displayCustomers();
                    case 3 -> displayCategories(sc);
                    default -> System.out.println("Unknown option \"$choice\".");
                }
            }
        }
    }

    private static void displayProducts() {
        System.out.println("ID  NAME                                     PRICE   STOCK");
        System.out.println("--- ---------------------------------------- ------- ------");
        for (var row : "[.sql/] SELECT productId, productName, unitPrice, unitsInStock FROM products".fetch())
            System.out.printf("%-3s %-40s %7.2f %-6d%n", row.productid, row.productname, row.unitprice, row.unitsinstock);
    }

    private static void displayCustomers() {
        System.out.println("CONTACT                        COMPANY                                  CITY            COUNTRY         PHONE");
        System.out.println("------------------------------ ---------------------------------------- --------------- --------------- ------------------------");
        for (var row : "[.sql/] SELECT contactName, companyName, city, country, phone FROM customers".fetch())
            System.out.printf("%-30s %-40s %-15s %-15s %-24s%n", row.contactname, row.companyname, row.city, row.country, row.phone);
    }

    private static void displayCategories(Scanner sc) {
        System.out.println("ID  NAME");
        System.out.println("--- ---------------");

        // A more specific SELECT clause doesn't work for some reason
        // This can't be inlined for some reason
        var iterableCategories = "[.sql/] SELECT * FROM categories".fetch();
        var categories = StreamSupport.stream(
                iterableCategories.spliterator(),
                false)
            .toList();
        for (var row : categories)
            System.out.printf("%-3d %-15s%n", row.categoryid, row.categoryname);

        System.out.print("Enter the category ID to find items for: ");
        //noinspection ReassignedVariable
        int choice;
        do {
            while (!sc.hasNextInt())
                sc.next();
            choice = sc.nextInt();
        } while (categories.stream().noneMatch(filterById(choice)));

        System.out.println("ID  NAME                                     PRICE   STOCK");
        System.out.println("--- ---------------------------------------- ------- ------");
        for (var row : "[.sql/] SELECT productId, productName, unitPrice, unitsInStock FROM products WHERE categoryId = :category".fetch(choice))
            System.out.printf("%-3s %-40s %7.2f %-6d%n", row.productid, row.productname, row.unitprice, row.unitsinstock);
    }

    private static Predicate<Northwind.Categories> filterById(int id) {
        //noinspection DataFlowIssue
        return cat -> cat.categoryid == id;
    }
}
