/*
 * Copyright (c) Benjamin Bergman 2024.
 */

package com.pluralsight;

import com.pluralsight.Card.*;

import java.util.*;

final class Deck {
    private final Stack<Card> cards;

    private Deck(Stack<Card> cards) {
        this.cards = cards;
    }

    public static Deck standard() {
        Stack<Card> cards = new Stack<>();
        for (var suit : Suit.values())
            for (var rank : Rank.values())
                cards.push(new Card(rank, suit));
        return new Deck(cards);
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card draw() {
        return cards.pop();
    }

    public void replace(Card c) {
        cards.push(c);
    }
}
