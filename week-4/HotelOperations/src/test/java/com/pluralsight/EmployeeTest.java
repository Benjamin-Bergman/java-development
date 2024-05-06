/*
 * Copyright (c) Benjamin Bergman 2024.
 */

package com.pluralsight;

import org.junit.jupiter.api.*;

import java.time.*;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    @Test
    void punchIn() {
        var employee = new Employee(0, "", "", 0, 0);

        assertDoesNotThrow(() -> employee.punchIn(), "Employees should be able to punch in");
        assertThrows(RuntimeException.class, employee::punchIn, "Employees should not be able to punch in repeatedly");
    }

    @Test
    void punchOut_allowed() {
        var employee = new Employee(0, "", "", 0, 0);
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = start.plusHours(1);
        employee.punchIn(start);

        assertDoesNotThrow(() -> employee.punchOut(end), "Employees should be able to punch out");
        assertThrows(RuntimeException.class, () -> employee.punchOut(end), "Employees should not be able to punch out repeatedly");
    }

    @Test
    void punchOut_correct() {
        final int hours = 3;

        var employee = new Employee(0, "", "", 0, 0);
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = start.plusHours(hours);

        employee.punchIn(start);
        employee.punchOut(end);

        assertEquals(hours, employee.getHoursWorked(), "Punching should add the correct number of hours");
    }

    @Test
    void punchOut_impossible() {
        var employee = new Employee(0, "", "", 0, 0);
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = start.minusHours(1);

        employee.punchIn(start);
        assertThrows(RuntimeException.class, () -> employee.punchOut(end), "Backwards times should not be allowed");
    }
}