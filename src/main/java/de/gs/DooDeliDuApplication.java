package de.gs;

import de.gs.state.GameState;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Stack;

@Slf4j
public class DooDeliDuApplication {

    private GameContext context;

    public static void main(String[] args) {

        DooDeliDuApplication application = new DooDeliDuApplication();
        application.startGame();
    }

    public void startGame() {

        var playerA = new Player("A", new Stack<>());
        var playerB = new Player("B", new Stack<>());
        var playerC = new Player("C", new Stack<>());

        this.context = new GameContext(new PlayersRing(List.of(playerA, playerB, playerC)));
        context.setCurrentPlayer(playerA);

        var controller = new GameController(context);
        log.info("Game will begin...");

        while (controller.getCurrentState() != GameState.END) {
            log.debug("Current state" + controller.getCurrentState());
            controller.nextState();
        }

        log.info("Game ended");
        controller.nextState();

    }


}
