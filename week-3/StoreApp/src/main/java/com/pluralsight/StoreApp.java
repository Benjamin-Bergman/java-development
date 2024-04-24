// Copyright (c) Benjamin Bergman 2024.

package com.pluralsight;

import java.io.*;
import java.util.*;

@SuppressWarnings("UtilityClass")
final class StoreApp {
    @SuppressWarnings("StaticCollection")
    private static final HashMap<Integer, Product> INVENTORY =
        new HashMap<>();

    public static void main(String[] args) {
        loadInventory();
        try (Scanner scanner = new Scanner(System.in)) {
            do productSearch(scanner); while (willContinue(scanner));
        }
    }

    private static boolean willContinue(Scanner scanner) {
        //noinspection HardcodedFileSeparator
        System.out.print("Check another item? [y/N] ");
        var response = scanner.nextLine().trim();
        return !response.isEmpty() && (response.toLowerCase().charAt(0) == 'y');
    }

    private static void productSearch(Scanner scanner) {
        System.out.print("What item # are you interested in? ");

        int id = scanner.nextInt();
        scanner.nextLine();

        Product matchedProduct = INVENTORY.get(id);
        if (matchedProduct == null) {
            System.out.println("We don't carry that product");
            return;
        }
        System.out.printf("We carry %s and the price is $%.2f%n",
            matchedProduct.getName(), matchedProduct.getPrice());
    }

    private static void loadInventory() {
        try (var fr = new FileReader("inventory.csv");
             var br = new BufferedReader(fr)) {

            br.lines()
                .map(s -> s.split("\\|"))
                .map(arr -> new Product(Integer.parseInt(arr[0]), arr[1], Double.parseDouble(arr[2])))
                .forEach(p -> INVENTORY.put(p.getId(), p));

        } catch (IOException e) {
            System.out.println("Cannot open inventory.csv");
            throw new UncheckedIOException("IO Error.", e);
        } catch (NumberFormatException e) {
            System.out.println("Error parsing inventory.csv");
            throw new InputFormatException("Bad number format.", e);
        }
    }

    @SuppressWarnings("UncheckedExceptionClass")
    private static class InputFormatException extends RuntimeException {
        InputFormatException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}