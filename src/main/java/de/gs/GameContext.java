package de.gs;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class GameContext {

    private int round = 0;
    private Player currentPlayer;

    private List<Player> players = new LinkedList<>();

    public void incrementRound() {
        round++;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }


}