// Copyright (c) Benjamin Bergman 2024.

package com.pluralsight;

final class Employee {
    private final String employeeId;
    private final String employeeName;
    private double hoursWorked;
    private double payRate;

    Employee(String employeeId, String employeeName, double hoursWorked, double payRate) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.hoursWorked = hoursWorked;
        this.payRate = payRate;
    }

    String getEmployeeId() {
        return employeeId;
    }

    String getEmployeeName() {
        return employeeName;
    }

    double getHoursWorked() {
        return hoursWorked;
    }

    void setHoursWorked(double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    double getPayRate() {
        return payRate;
    }

    void setPayRate(double payRate) {
        this.payRate = payRate;
    }

    double getGrossPay() {
        return payRate * hoursWorked;
    }
}
