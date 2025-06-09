package de.gs;

import java.util.ArrayList;
import java.util.List;

public class GameState {
    private final String name;
    private final List<Transition> transitions = new ArrayList<>();

    public GameState(String name) {
        this.name = name;
    }

    public void addTransition(Transition transition) {
        transitions.add(transition);
    }

    public List<Transition> getTransitions() {
        return transitions;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}