/*
 * Copyright (c) Benjamin Bergman 2024.
 */

package com.pluralsight;

final class Reservation {

    private RoomType roomType;
    private int numberOfNights;
    private boolean weekend;

    Reservation(RoomType roomType, int numberOfNights, boolean weekend) {
        this.roomType = roomType;
        this.numberOfNights = numberOfNights;
        this.weekend = weekend;
    }

    public boolean isWeekend() {
        return weekend;
    }

    public void setWeekend(boolean weekend) {
        this.weekend = weekend;
    }

    public int getNumberOfNights() {
        return numberOfNights;
    }

    public void setNumberOfNights(int numberOfNights) {
        this.numberOfNights = numberOfNights;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public double getReservationTotal() {
        return roomType.getCostPerNight() * (weekend ? 1.1 : 1) * numberOfNights;
    }

    public enum RoomType {
        KING(139),
        DOUBLE(124);

        private final double costPerNight;

        RoomType(double costPerNight) {
            this.costPerNight = costPerNight;
        }

        private double getCostPerNight() {
            return costPerNight;
        }
    }
}
