package de.gs;

import de.gs.card.Card;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class GameContext {

    private int round = 0;
    private Player currentPlayer;

    private PlayersRing players = new PlayersRing(List.of());
    private LinkedList<Card> cards = new LinkedList<>();

    public void incrementRound() {
        round++;
    }

}