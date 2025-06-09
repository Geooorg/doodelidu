package de.gs;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DooDeliDuApplication {

    private GameContext context;
    private StateMachine stateMachine;

    public static void main(String[] args) {

        DooDeliDuApplication application = new DooDeliDuApplication();
        application.startGame();
    }

    public GameContext startGame() {
        this.context = new GameContext();
        this.stateMachine = StateMachineFactory.create(context);

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
        return context;
    }

}
