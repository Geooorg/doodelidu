package de.gs;

import lombok.Getter;

import java.util.function.Predicate;

public class Transition {
    @Getter
    private final GameState targetState;
    private final Predicate<GameContext> condition;

    public Transition(GameState targetState, Predicate<GameContext> condition) {
        this.targetState = targetState;
        this.condition = condition;
    }

    public boolean isAllowed(GameContext context) {
        return condition == null || condition.test(context);
    }

}