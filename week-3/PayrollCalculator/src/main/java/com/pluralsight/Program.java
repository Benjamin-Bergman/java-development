// Copyright (c) Benjamin Bergman 2024.

package com.pluralsight;

import java.io.*;

final class Program {
    public static void main(String[] args) {
        try (var fr = new FileReader("employees.csv")) {
            try (var reader = new BufferedReader(fr)) {
                reader.lines()
                        .map(Program::parseLine)
                        .forEachOrdered(e ->
                                System.out.printf("%s %s %.2f%n", e.getEmployeeId(), e.getEmployeeName(), e.getGrossPay())
                        );
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException e) {
            System.out.println("IO error.");
        }
    }

    private static Employee parseLine(String s) {
        String[] parts = s.split("\\|");
        return new Employee(parts[0], parts[1], Double.parseDouble(parts[2]), Double.parseDouble(parts[3]));
    }
}
