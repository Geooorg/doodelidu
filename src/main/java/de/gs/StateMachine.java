package de.gs;

public class StateMachine {
    private GameState currentState;
    private final GameContext context;

    public StateMachine(GameState initialState, GameContext context) {
        this.currentState = initialState;
        this.context = context;
    }

    public boolean next() {
        for (Transition t : currentState.getTransitions()) {
            if (t.isAllowed(context)) {
                currentState = t.getTargetState();
                return true;
            }
        }
        return false;
    }

    public GameState getCurrentState() {
        return currentState;
    }
}