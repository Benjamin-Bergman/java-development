/*
 * Copyright (c) Benjamin Bergman 2024.
 */

package com.pluralsight;

import java.time.*;

final class Employee {
    private static final int OVERTIME_LIMIT = 40;
    private static final double OVERTIME_BONUS = 1.5;
    private final int employeeId;
    private final String name, department;
    private double payRate;
    private double hoursWorked;
    private LocalDateTime punchedIn;

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

    public void punchIn() {
        punchIn(LocalDateTime.now());
    }

    public void punchIn(LocalDateTime time) {
        assert punchedIn == null : "Employee already punched in";
        punchedIn = time;
    }

    public void punchOut() {
        punchOut(LocalDateTime.now());
    }

    public void punchOut(LocalDateTime time) {
        assert punchedIn != null : "Employee not punched in";
        punchTimeCard(punchedIn, time);
        punchedIn = null;
    }

    public void punchTimeCard(LocalDateTime start, LocalDateTime end) {
        assert end > start : "Negative time worked";
        var dur = Duration.between(start, end);
        // Times less than a second can probably be ignored, but I don't want to make my employees mad.
        hoursWorked += dur.getSeconds() / 3_600.0
                       + dur.getNano() / 3_600_000_000_000.0;
    }
}
