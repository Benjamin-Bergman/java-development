/*
 * Copyright (c) Benjamin Bergman 2024.
 */

package com.pluralsight;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {
    @Test
    void isAvailable() {
        var room = new Room(0, 0, false, false);

        assertTrue(room.isAvailable(), "Empty, clean room should be available");
    }

    @Test
    void isUnavailable_when_occupied() {
        var room = new Room(0, 0, true, false);

        assertFalse(room.isAvailable(), "Full room should be unavailable");
    }

    @Test
    void isUnavailable_when_dirty() {
        var room = new Room(0, 0, false, true);

        assertFalse(room.isAvailable(), "Dirty room should be unavailable");
    }

    @Test
    void checkIn() {
        var room = new Room(0, 0, false, false);

        room.checkIn();

        assertTrue(room.isOccupied(), "Room should be occupied after checking in");
        assertTrue(room.isDirty(), "Room should be dirty after checking in");
    }

    @Test
    void checkIn_when_occupied() {
        var room = new Room(0, 0, true, false);

        assertThrows(AssertionError.class, room::checkIn, "Checking in an occupied room should not be permitted");
    }

    @Test
    void checkout() {
        var room = new Room(0, 0, true, false);

        room.checkout();

        assertFalse(room.isOccupied(), "Room should not be occupied after checking out");
    }

    @Test
    void checkout_when_unoccupied() {
        var room = new Room(0, 0, false, false);

        assertThrows(AssertionError.class, room::checkout, "Checking out an empty room should no be permitted");
    }

    @Test
    void cleanRoom() {
        var room = new Room(0, 0, false, true);

        room.cleanRoom();

        assertFalse(room.isDirty(), "Room should not be dirty after being cleaned");
    }

    @Test
    void cleanRoom_when_clean() {
        var room = new Room(0, 0, false, false);

        assertDoesNotThrow(room::cleanRoom, "Cleaning a clean room should be allowed");
    }

    @Test
    void cleanRoom_when_occupied() {
        var room = new Room(0, 0, true, true);

        assertThrows(AssertionError.class, room::cleanRoom, "Cleaning an occupied room should not be allowed");
    }
}