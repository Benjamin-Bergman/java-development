/*
 * Copyright (c) Benjamin Bergman 2024.
 */

package com.pluralsight;

final class Employee {
    private static final int OVERTIME_LIMIT = 40;
    private static final double OVERTIME_BONUS = 1.5;
    private final int employeeId;
    private final String name, department;
    private double payRate;
    private double hoursWorked;
    private Double punchedIn;

    Employee(int employeeId, String name, String department, double payRate, double hoursWorked) {
        this.employeeId = employeeId;
        this.name = name;
        this.department = department;
        this.payRate = payRate;
        this.hoursWorked = hoursWorked;
        punchedIn = null;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public double getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public double getPayRate() {
        return payRate;
    }

    public void setPayRate(double payRate) {
        this.payRate = payRate;
    }

    public double getTotalPay() {
        return payRate * getRegularHours()
               + OVERTIME_BONUS * payRate * getOvertimeHours();
    }

    public double getRegularHours() {
        return Math.min(hoursWorked, OVERTIME_LIMIT);
    }

    public double getOvertimeHours() {
        return Math.max(hoursWorked, OVERTIME_LIMIT) - OVERTIME_LIMIT;
    }

    public void punchIn(double time) {
        assert punchedIn == null : "Employee already punched in";
        punchedIn = time;
    }

    public void punchOut(double time) {
        assert punchedIn != null : "Employee not punched in";
        punchTimeCard(punchedIn, time);
        punchedIn = null;
    }

    public void punchTimeCard(double start, double end) {
        assert end > start : "Negative time worked";
        hoursWorked += end - start;
    }
}
