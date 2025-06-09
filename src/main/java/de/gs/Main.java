package de.gs;

public class Main {

    public static void main(String[] args) {
        GameState init = new GameState("INIT");
        GameState deal = new GameState("DEAL_CARDS");
        GameState turn = new GameState("PLAYER_TURN");
        GameState evaluate = new GameState("EVALUATE");
        GameState end = new GameState("END");

        init.addTransition(new Transition(deal, ctx -> true));
        deal.addTransition(new Transition(turn, ctx -> true));
        turn.addTransition(new Transition(evaluate, ctx -> true));
        evaluate.addTransition(new Transition(turn, ctx -> ctx.playerMadeMistake));
        evaluate.addTransition(new Transition(end, ctx -> ctx.round >= 24));
        evaluate.addTransition(new Transition(turn, ctx -> ctx.round < 24 && !ctx.playerMadeMistake));

        GameContext context = new GameContext();
        StateMachine sm = new StateMachine(init, context);

        while (!sm.getCurrentState().getName().equals("END")) {
            System.out.println("Aktueller Zustand: " + sm.getCurrentState());

            context.round++;
            context.playerMadeMistake = context.round == 10;

            if (!sm.next()) {
                System.out.println("Kein gültiger Übergang möglich!");
                break;
            }
        }

        System.out.println("Spiel beendet nach " + context.round + " Runden.");
    }
}