package de.gs;

import de.gs.card.Card;
import lombok.Getter;
import lombok.ToString;

import java.util.Objects;
import java.util.Stack;

@Getter
@ToString
public class Player {

    private final String name;
    private final Stack<Card> cards;

    public Player(String name) {
        this.name = name;
        this.cards = new Stack<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player player)) return false;
        return Objects.equals(name, player.name); // nur der Name zählt
    }

    @Override
    public int hashCode() {
        return Objects.hash(name); // nur der Name zählt
    }
}
