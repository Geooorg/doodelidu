package de.gs;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StateMachineTest {

    @Test
    public void testInitialStateTransition() {
        var initState = GameState.INIT;
        var nextState = GameState.NEXT;
        var ctx = new GameContext();
        initState.addTransition(new Transition(nextState, c -> true));
        StateMachine sm = new StateMachine(initState, ctx);

        assertEquals(GameState.INIT, sm.getCurrentState().getName());
        assertTrue(sm.next());
        assertEquals(GameState.NEXT, sm.getCurrentState().getName());
    }

    @Test
    public void testBlockedTransition() {
        var initState = GameState.INIT;
        var nextState = GameState.NEXT;
        GameContext ctx = new GameContext();
        initState.addTransition(new Transition(nextState, c -> false));
        StateMachine sm = new StateMachine(initState, ctx);

        assertFalse(sm.next());
        assertEquals("INIT", sm.getCurrentState().getName());
    }
}