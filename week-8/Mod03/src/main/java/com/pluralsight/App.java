/*
 * Copyright (c) Benjamin Bergman 2024.
 */

package com.pluralsight;

public class App {
    public static void main(String[] args) {
        System.out.println("ID  NAME                                     PRICE   STOCK");
        System.out.println("--- ---------------------------------------- ------- ------");
        for (var row : "[.sql/] SELECT productId, productName, unitPrice, unitsInStock FROM products".fetch())
            System.out.printf("%-3s %-40s %7.2f %-6d%n", row.productid, row.productname, row.unitprice, row.unitsinstock);
    }
}
