/*
 * Copyright (c) Benjamin Bergman 2024.
 */

package com.pluralsight;

final class Hotel {
    private final String name;
    private final int numberOfSuites;
    private final int numberOfRooms;
    private int bookedSuites;
    private int bookedRooms;

    public Hotel(String name, int numberOfSuites, int numberOfRooms) {
        this(name, numberOfSuites, numberOfRooms, 0, 0);
    }

    public Hotel(String name, int numberOfSuites, int numberOfRooms, int bookedSuites, int bookedRooms) {
        this.name = name;
        this.numberOfSuites = numberOfSuites;
        this.numberOfRooms = numberOfRooms;
        this.bookedSuites = bookedSuites;
        this.bookedRooms = bookedRooms;
    }

    public boolean bookRoom(int count, boolean isSuite) {
        boolean success = count <= (isSuite ? getAvailableSuites() : getAvailableRooms());

        if (success && isSuite)
            bookedSuites += count;
        else if (success) bookedRooms += count;

        return success;
    }

    public int getAvailableSuites() {
        return numberOfSuites - bookedSuites;
    }

    public int getAvailableRooms() {
        return numberOfRooms - bookedRooms;
    }
}
