package de.gs.state;

import de.gs.GameContext;
import lombok.Getter;
import lombok.Setter;

public class StateMachine {
    @Getter @Setter
    private GameState currentState;
    private final GameContext context;

    public StateMachine(GameState initialState, GameContext context) {
        this.currentState = initialState;
        this.context = context;
    }

    public boolean next() {
        for (Transition transition : currentState.getTransitions()) {
            if (transition.isAllowed(context)) {
                currentState = transition.getTargetState();
                return true;
            }
        }
        return false;
    }

}