package de.gs;

public class StateMachineFactory {

    public  static StateMachine create( GameContext context ) {
        var initState = GameState.INIT;
        var dealCardsState = GameState.DEAL_CARDS;
        var turnPlayerState = GameState.PLAYER_TURN;
        var evaluateTurnState = GameState.EVALUATE;
        var endState = GameState.END;

        initState.addTransition( new Transition( dealCardsState, ctx -> true ) );
        dealCardsState.addTransition( new Transition( turnPlayerState, ctx -> true ) );
        turnPlayerState.addTransition( new Transition( evaluateTurnState, ctx -> true ) );
        evaluateTurnState.addTransition( new Transition( turnPlayerState, ctx -> ctx.playerMadeMistake ) );
        evaluateTurnState.addTransition( new Transition( endState, ctx -> ctx.round >= 24 ) );
        evaluateTurnState.addTransition( new Transition( turnPlayerState, ctx -> ctx.round < 24 && !ctx.playerMadeMistake ) );

        return new StateMachine( GameState.INIT, context );
    }
}
