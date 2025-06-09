package de.gs;

import de.gs.state.GameState;
import de.gs.state.StateMachine;
import de.gs.state.Transition;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StateMachineTest {

    @Test
    public void testInitialStateTransition() {
        var initState = GameState.INIT;
        var nextState = GameState.NEXT_PLAYER;
        var ctx = new GameContext();
        initState.addTransition(new Transition(nextState, c -> true));
        StateMachine sm = new StateMachine(initState, ctx);

        assertEquals(GameState.INIT, sm.getCurrentState().getName());
        assertTrue(sm.next());
        assertEquals(GameState.NEXT_PLAYER, sm.getCurrentState().getName());
    }

    @Test
    public void testBlockedTransition() {
        var initState = GameState.INIT;
        var nextState = GameState.NEXT_PLAYER;
        GameContext ctx = new GameContext();
        initState.addTransition(new Transition(nextState, c -> false));
        StateMachine sm = new StateMachine(initState, ctx);

        assertFalse(sm.next());
        assertEquals("INIT", sm.getCurrentState().getName());
    }
}