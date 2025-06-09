package de.gs.state;

import de.gs.GameContext;

public class StateMachineFactory {

    public  static StateMachine create( GameContext context ) {
        var initState = GameState.INIT;
        var dealCardsState = GameState.DEAL_CARDS;
        var nextPlayerState = GameState.NEXT_PLAYER;
        var nextRoundState = GameState.NEXT_ROUND;
        var evaluateState = GameState.EVALUATE;
        var endState = GameState.END;

        initState.addTransition( new Transition( dealCardsState, ctx -> true ) );
        dealCardsState.addTransition( new Transition( nextPlayerState, ctx -> true ) );
        nextPlayerState.addTransition( new Transition( evaluateState, ctx -> true ) );
        // evaluateTurnState.addTransition( new Transition( nextPlayerState, ctx -> ctx.playerMadeMistake ) );
        evaluateState.addTransition( new Transition( endState, ctx -> ctx.getRound() >= 24 ) );
        // evaluateState.addTransition( new Transition( nextPlayerState, ctx -> ctx.getRound() < 24 && !ctx.playerMadeMistake ) );

        return new StateMachine( GameState.INIT, context );
    }
}
