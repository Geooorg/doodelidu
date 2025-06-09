package de.gs;

import de.gs.state.GameState;
import de.gs.state.StateMachine;
import de.gs.state.StateMachineFactory;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

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

        var playerA = new Player("A");
        var playerB = new Player("B");
        var playerC = new Player("C");
        context.setPlayers(new PlayersRing(List.of(playerA, playerB, playerC)));
        context.setCurrentPlayer(playerA);

        this.stateMachine = StateMachineFactory.create(context);

        log.info("Spiel gestartet.");

        while (stateMachine.getCurrentState() != GameState.END) {
            log.info("Aktueller Zustand: " + stateMachine.getCurrentState());

            context.incrementRound();

            if (!stateMachine.next()) {
                log.info("Kein gültiger Übergang möglich - ENDE");
                stateMachine.setCurrentState(GameState.END);
                break;
            }
        }

        log.info("Spiel beendet nach " + context.getRound() + " Runden.");
        return context;
    }


}
