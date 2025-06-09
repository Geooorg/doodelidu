package de.gs;


import de.gs.card.Card;

import java.util.Stack;

public record Player(String name, Stack<Card> cards) {
}
