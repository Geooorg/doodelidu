package de.gs;

import lombok.Getter;

import java.util.List;

public class PlayersRing {

    private final List<Player> players;
    @Getter
    private int currentIndex = 0;

    public PlayersRing(List<Player> players) {
        if (players == null || players.isEmpty()) {
            throw new IllegalArgumentException("Spielerliste darf nicht leer sein.");
        }
        this.players = players;
    }

    public Player getCurrentPlayer() {
        return players.get(currentIndex);
    }

    public Player next() {
        currentIndex = (currentIndex + 1) % players.size();
        return getCurrentPlayer();
    }

    public List<Player> getAllPlayers() {
        return players;
    }

    public void reset() {
        currentIndex = 0;
    }

    public int size() {
        return players.size();
    }
}
