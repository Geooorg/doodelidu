package de.gs;

import java.util.function.Predicate;

public class Transition {
    private final GameState targetState;
    private final Predicate<GameContext> condition;

    public Transition(GameState targetState, Predicate<GameContext> condition) {
        this.targetState = targetState;
        this.condition = condition;
    }

    public boolean isAllowed(GameContext context) {
        return condition == null || condition.test(context);
    }

    public GameState getTargetState() {
        return targetState;
    }
}