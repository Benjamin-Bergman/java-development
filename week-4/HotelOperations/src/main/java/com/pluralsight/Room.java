/*
 * Copyright (c) Benjamin Bergman 2024.
 */

package com.pluralsight;

final class Room {
    private int numberOfBeds;
    private double price;
    private boolean occupied;
    private boolean dirty;

    Room(int numberOfBeds, double price, boolean occupied, boolean dirty) {
        this.numberOfBeds = numberOfBeds;
        this.price = price;
        this.occupied = occupied;
        this.dirty = dirty;
    }

    public boolean isDirty() {
        return dirty;
    }

    public void setDirty(boolean dirty) {
        this.dirty = dirty;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public boolean isAvailable() {
        return !occupied && !dirty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public void setNumberOfBeds(int numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    public void checkIn() {
        assert isAvailable() : "Double-booked room";
        occupied = true;
        dirty = true;
    }

    public void checkout() {
        assert occupied : "Room already checked out";
        occupied = false;
    }

    public void cleanRoom() {
        assert !occupied : "Cleaning an occupied room";
        dirty = false;
    }
}
