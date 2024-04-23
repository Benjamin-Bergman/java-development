// Copyright (c) Benjamin Bergman 2024.

package com.pluralsight;

import java.io.*;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

@SuppressWarnings("UtilityClass")
final class Program {
    public static void main(String[] args) {
        File in, out;
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Input file: ");
            in = new File(sc.nextLine());
            if (!in.exists() || in.isDirectory()) {
                System.out.println("File not found.");
                return;
            }
            System.out.print("Output file: ");
            out = new File(sc.nextLine());
            if (out.isDirectory()) {
                System.out.println("Can't overwrite a directory.");
                return;
            }
            if (out.exists()) System.out.println("Overwriting " + out);
        }

        Function<? super Collection<Employee>, String> formatter =
            out.getName().endsWith(".json")
                ? Program::toJSON
                : Program::toCSV;

        try {
            copy(in, out, formatter);
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException e) {
            System.out.println("IO error.");
        }
    }

    @SuppressWarnings("FeatureEnvy")
    private static String toCSV(Collection<Employee> employees) {
        return employees
            .stream()
            .map(e -> "%s, %s, %f".formatted(e.getEmployeeId(), e.getEmployeeName(), e.getGrossPay()))
            .collect(Collectors.joining(System.lineSeparator()));
    }

    @SuppressWarnings("FeatureEnvy")
    private static String toJSON(Collection<Employee> employees) {
        return
            '[' +
            employees
                .stream()
                .map(e -> "{\"employeeId\":\"%s\",\"name\":\"%s\",\"grossPay\":%f]}".formatted(e.getEmployeeId(), e.getEmployeeName(), e.getGrossPay()))
                .collect(Collectors.joining(",")) +
            ']';
    }

    private static void copy(File from, File to, Function<? super Collection<Employee>, String> formatter) throws IOException {
        try (var fr = new FileReader(from);
             var reader = new BufferedReader(fr);
             var fw = new FileWriter(to);
             var writer = new BufferedWriter(fw)) {
            writer.write(formatter.apply(
                reader.lines()
                    .map(Program::parseLine)
                    .collect(Collectors.toList())
            ));
        }
    }

    private static Employee parseLine(String s) {
        String[] parts = s.split("\\|");
        return new Employee(parts[0], parts[1], Double.parseDouble(parts[2]), Double.parseDouble(parts[3]));
    }
}
