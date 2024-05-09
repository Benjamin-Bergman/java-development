/*
 * Copyright (c) Benjamin Bergman 2024.
 */

package com.pluralsight;

record Card(Rank rank, Suit suit) {
    @Override
    public String toString() {
        return "$rank $suit";
    }

    public enum Suit {
        SPADES("♠"),
        HEARTS("♥"),
        CLUBS("♣"),
        DIAMONDS("♦");

        private final String display;

        Suit(String display) {
            this.display = display;
        }

        @Override
        public String toString() {
            return display;
        }
    }

    public enum Rank {
        ACE("A", 11),
        TWO("2", 2),
        THREE("3", 3),
        FOUR("4", 4),
        FIVE("5", 5),
        SIX("6", 6),
        SEVEN("7", 7),
        EIGHT("8", 8),
        NINE("9", 9),
        TEN("10", 10),
        JACK("J", 10),
        QUEEN("Q", 10),
        KING("K", 10);

        private final String display;
        private final int value;

        Rank(String display, int value) {
            this.display = display;
            this.value = value;
        }

        @Override
        public String toString() {
            return display;
        }

        public int getValue() {
            return value;
        }
    }
}
