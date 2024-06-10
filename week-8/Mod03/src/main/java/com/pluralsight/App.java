/*
 * Copyright (c) Benjamin Bergman 2024.
 */

package com.pluralsight;

import java.util.*;

public class App {
    public static void main(String[] args) {
        try (var sc = new Scanner(System.in)) {
            while (true) {
                System.out.print("""
                    What do you want to do?
                      1) Display all products
                      2) Display all customers
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
}
