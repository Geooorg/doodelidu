package de.gs;

import java.util.ArrayList;
import java.util.List;

public enum GameState {
    INIT,
    NEXT,
    DEAL_CARDS,
    PLAYER_TURN,
    EVALUATE,
    END;

    private final List<Transition> transitions = new ArrayList<>();

    public void addTransition(Transition transition) {
        transitions.add(transition);
    }

    public List<Transition> getTransitions() {
        return transitions;
    }

    public String getName() {
        return this.name();
    }

    @Override
    public String toString() {
        return this.name();
    }
}