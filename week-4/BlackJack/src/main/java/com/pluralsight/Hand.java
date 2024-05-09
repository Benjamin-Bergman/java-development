/*
 * Copyright (c) Benjamin Bergman 2024.
 */

package com.pluralsight;

import com.pluralsight.Card.*;

import java.util.*;
import java.util.stream.*;

final class Hand {
    private final List<Card> cards = new ArrayList<>();
    private boolean anyAce;

    public void add(Card c) {
        cards.add(c);
        if (c.rank() == Rank.ACE)
            anyAce = true;
    }

    public void remove(Card c) {
        cards.remove(c);
    }

    public int getScore() {
        var score = cards
            .stream()
            .mapToInt(card -> card.rank().getValue())
            .sum();
        return score > 21 && anyAce ? score - 10 : score;
    }

    @Override
    public String toString() {
        return cards.stream().map(Card::toString).collect(Collectors.joining(", ")) + " = ${getScore()} points.";
    }
}
