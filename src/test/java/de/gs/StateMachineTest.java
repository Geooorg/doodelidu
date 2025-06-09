package de.gs;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StateMachineTest {

    @Test
    public void testInitialStateTransition() {
        GameState init = new GameState("INIT");
        GameState next = new GameState("NEXT");
        GameContext ctx = new GameContext();
        init.addTransition(new Transition(next, c -> true));
        StateMachine sm = new StateMachine(init, ctx);

        assertEquals("INIT", sm.getCurrentState().getName());
        assertTrue(sm.next());
        assertEquals("NEXT", sm.getCurrentState().getName());
    }

    @Test
    public void testBlockedTransition() {
        GameState init = new GameState("INIT");
        GameState next = new GameState("NEXT");
        GameContext ctx = new GameContext();
        init.addTransition(new Transition(next, c -> false));
        StateMachine sm = new StateMachine(init, ctx);

        assertFalse(sm.next());
        assertEquals("INIT", sm.getCurrentState().getName());
    }
}