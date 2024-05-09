/*
 * Copyright (c) Benjamin Bergman 2024.
 */

package com.pluralsight;

import java.util.*;

final class Game {
    public static void main(String[] args) {
        System.out.println("Welcome to the Blackjack table.");
        try (Scanner sc = new Scanner(System.in)) {
            do run(sc);
            while (askYN(sc));
        }
        System.out.println("See you next time!");
    }

    private static void run(Scanner sc) {
        Deck deck = Deck.standard();
        deck.shuffle();
        Hand player = new Hand();
        Hand dealer = new Hand();

        Card visible;
        player.add(deck.draw());
        dealer.add(visible = deck.draw());
        player.add(deck.draw());
        dealer.add(deck.draw());

        System.out.println("The dealer has $visible and an unknown card.");
        System.out.println("Here's your hand: $player");

        if (player.getScore() == 21) {
            if (dealer.getScore() == 21)
                System.out.println("Blackjack! Unfortunately, the dealer also had one. You lose.");
            else System.out.println("Blackjack! You win!");
            return;
        }

        boolean askingPlayer = true, askingDealer = true;
        while (askingPlayer || askingDealer) {
            if (askingPlayer) {
                askingPlayer = askHit(sc);
                if (askingPlayer) {
                    Card c = deck.draw();
                    player.add(c);
                    System.out.println("You drew $c. Your score is now ${player.getScore()}.");
                    if (player.getScore() > 21) {
                        System.out.println("Bust! You lose.");
                        return;
                    }
                }
            }
            if (askingDealer) {
                Card c = deck.draw();
                dealer.add(c);
                // The house has an advantage
                if (dealer.getScore() <= 21)
                    System.out.println("The dealer draws.");
                else {
                    System.out.println("The dealer stands.");
                    dealer.remove(c);
                    deck.replace(c);
                    askingDealer = false;
                }
            }
        }

        System.out.println("Here's the dealer's hand: $dealer");
        if (dealer.getScore() > 21) System.out.println("The dealer busts! You win!");
        else if (player.getScore() > dealer.getScore()) System.out.println("You win!");
        else if (player.getScore() == dealer.getScore()) System.out.println("Tie. You lose.");
        else System.out.println("You lose.");
    }

    private static boolean askHit(Scanner sc) {
        while (true) {
            System.out.print("Hit or Stand? ");
            var in = sc.nextLine().trim().toLowerCase();
            if ("hit".equals(in) || "h".equals(in))
                return true;
            if ("stand".equals(in) || "s".equals(in))
                return false;
            System.out.println("Unkown command \"$in\".");
        }
    }

    private static boolean askYN(Scanner sc) {
        while (true) {
            System.out.print("Would you like to play again? ");
            var in = sc.nextLine().trim().toLowerCase();
            if ("yes".equals(in) || "y".equals(in))
                return true;
            if ("no".equals(in) || "n".equals(in))
                return false;
            System.out.println("Unkown command \"$in\".");
        }
    }
}
