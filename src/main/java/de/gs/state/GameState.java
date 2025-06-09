package de.gs.state;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public enum GameState {
    INIT,
    DISTRIBUTE_CARDS,
    NEXT_PLAYER,
    NEXT_ROUND,
    EVALUATE,
    END;

    private final List<Transition> transitions = new ArrayList<>();

    public void addTransition(Transition transition) {
        transitions.add(transition);
    }

    public String getName() {
        return this.name();
    }

    @Override
    public String toString() {
        return this.name();
    }
}