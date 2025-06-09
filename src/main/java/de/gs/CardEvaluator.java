package de.gs;

import de.gs.card.Card;
import de.gs.card.Color;
import de.gs.card.Symbol;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class CardEvaluator {

    public EvaluationResult evaluatePlayedCards(Map<Player, Card> cards, int numPlayers) {
        Map<Color, Integer> colorCountMap = new HashMap<>();
        Map<Symbol, Integer> symbolCountMap = new HashMap<>();

        for (Card card : cards.values()) {
            colorCountMap.merge(card.color(), 1, Integer::sum);
            symbolCountMap.merge(card.symbol(), 1, Integer::sum);
        }

        int numColors = colorCountMap.values().stream().mapToInt(i -> i).max().orElse(0);
        int numSymbols = symbolCountMap.values().stream().mapToInt(i -> i).max().orElse(0);
        int numTortoises = Math.toIntExact(cards.values().stream().filter(c -> c.symbol() == Symbol.TORTOISE)
                        .count());
        log.info("Colors: {}, Symbols: {}, 🐢: {}", numColors, numSymbols, numTortoises);

        String whatToSay = determineWhatToSay(cards, numColors, numSymbols, numTortoises, numPlayers);
        return new EvaluationResult(numColors, numSymbols, whatToSay);
    }


    public String determineWhatToSay(
            Map<Player, Card> cards,
            int numColors, int numSymbols, int numTortoises, int numPlayers) {

        return "";
//        if (allSymbolsMatchSize + allColorsMatchSize == 0) {
//            return "Nothing";
//        } else if (allSymbolsMatchSize == 1 && allColorsMatchSize == 1) {
//            return "DooDeliDu!";
//        } else if (allSymbolsMatchSize == 1) {
//            return;
//        }


    }

}
