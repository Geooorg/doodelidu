package de.gs;

import de.gs.card.Card;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

@Data
public class GameContext {

    private final PlayersRing players;

    public GameContext(PlayersRing players) {
        this.players = players;
    }

    private int round = 0;
    private Player currentPlayer;

    private Stack<Card> cards = new Stack<>();
    private Map<Player, Card> playedCards = new HashMap<>();

    public void incrementRound() {
        round++;
    }

}