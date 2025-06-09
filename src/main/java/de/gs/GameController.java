package de.gs;


import de.gs.card.Card;
import de.gs.card.Color;
import de.gs.card.Symbol;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.security.SecureRandom;
import java.util.Stack;

@Slf4j
public class GameController {

    private final static int NUMBER_OF_CARDS = 36;
    private final GameContext context;

    @Getter
    private State currentState;

    public GameController(GameContext context) {
        this.context = context;
        this.currentState = State.INIT;
    }

    public void nextState() {
        log.info("\nCurrent State: {}", currentState);

        switch (currentState) {
            case INIT:
                log.info("Generating cards");
                var cards = generateRandomCardDeck(NUMBER_OF_CARDS);
                context.setCards(cards);
                currentState = State.DISTRIBUTE_CARDS;
                break;

            case DISTRIBUTE_CARDS:
                log.info("Distributing cards");
                distributeCards();
                currentState = State.NEXT_PLAYER;
                break;

            case NEXT_PLAYER:
                var nextPlayer = context.getPlayers().next();
                context.setCurrentPlayer(nextPlayer);

                log.info("Player {} - {} cards left", nextPlayer.name(), nextPlayer.cards().size());
                playCard();
                currentState = State.EVALUATE;
                break;

            case EVALUATE:
                evaluateTurn();
                currentState = anyPlayerHasCardsLeft() ? State.NEXT_PLAYER : State.END;
                break;

            case END:
                log.info("======== Game ended ===========");
                var winner = determineWinner();
                log.info("Player {} won the game", winner.name());
                break;
        }
    }


    private void playCard() {
        var player = context.getCurrentPlayer();
        var card = player.cards().pop();
        context.getPlayedCards().remove(player);
        context.getPlayedCards().put(player, card);
        log.info("Player {} played card {}", player.name(), card);
    }

    private boolean anyPlayerHasCardsLeft() {
        return context.getPlayers().getAllPlayers().stream().anyMatch(player -> !player.cards().isEmpty());
    }

    private void distributeCards() {
        var cards = context.getCards();
        var players = context.getPlayers();

        while (!cards.isEmpty()) {
            var card = cards.pop();
            players.getCurrentPlayer().cards().push(card);
            players.next();
        }

        log.info("Cards distributed");
        for (var player : players.getAllPlayers()) {
            log.info("Player {} has {} cards", player.name(), player.cards().size());
        }
    }

    private void evaluateTurn() {
        log.info("Evaluating turn");
        var cardsOnTable = context.getPlayedCards();
        var evaluator = new CardEvaluator();
        var result = evaluator.evaluatePlayedCards(cardsOnTable, context.getPlayers().size());

        log.info("What to say: {}", result.whatToSay());
    }

    private Stack<Card> generateRandomCardDeck(int numberOfCards) {
        Stack<Card> stack = new Stack<>();
        final var random = new SecureRandom();

        Color[] colors = Color.values();
        Symbol[] symbols = Symbol.values();

        for (int i = 0; i < numberOfCards; i++) {
            var color = colors[random.nextInt(colors.length)];
            var symbol = symbols[random.nextInt(symbols.length)];
            stack.add(new Card(color, symbol));
        }

        return stack;
    }

    private Player determineWinner() {
        return context.getPlayers().getAllPlayers().stream()
                .filter(player -> player.cards()
                .isEmpty()).findFirst().orElseThrow();

    }
}
