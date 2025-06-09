package de.gs;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

    public static void main(String[] args) {

        var initState = GameState.INIT;
        var dealCardsState = GameState.DEAL_CARDS;
        var turnPlayerState = GameState.PLAYER_TURN;
        var evaluateTurnState = GameState.EVALUATE;
        var endState = GameState.END;

        initState.addTransition(new Transition(dealCardsState, ctx -> true));
        dealCardsState.addTransition(new Transition(turnPlayerState, ctx -> true));
        turnPlayerState.addTransition(new Transition(evaluateTurnState, ctx -> true));
        evaluateTurnState.addTransition(new Transition(turnPlayerState, ctx -> ctx.playerMadeMistake));
        evaluateTurnState.addTransition(new Transition(endState, ctx -> ctx.round >= 24));
        evaluateTurnState.addTransition(new Transition(turnPlayerState, ctx -> ctx.round < 24 && !ctx.playerMadeMistake));

        var context = new GameContext();
        var stateMachine = new StateMachine(initState, context);

        while (stateMachine.getCurrentState() != GameState.END) {
            log.info("Aktueller Zustand: " + stateMachine.getCurrentState());

            context.round++;
            context.playerMadeMistake = context.round == 10;

            if (!stateMachine.next()) {
                log.info("Kein gültiger Übergang möglich");
                break;
            }
        }

        log.info("Spiel beendet nach " + context.round + " Runden.");
    }
}