package de.gs;

import de.gs.card.Card;
import de.gs.card.Color;
import de.gs.card.Symbol;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class CardEvaluator {

    public EvaluationResult evaluatePlayedCards(Map<Player, Card> cards) {
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

        log.debug("Colors: {}, Symbols: {}, 🐢: {}", numColors, numSymbols, numTortoises);

        String whatToSay = determineWhatToSay(cards, numColors, numSymbols, numTortoises);
        return new EvaluationResult(numColors, numSymbols, whatToSay);
    }


    public String determineWhatToSay(
            Map<Player, Card> cards,
            int numColors, int numSymbols, int numTortoises) {

        var sb = new StringBuilder();
        sb.append("öh ".repeat(Math.max(0, numTortoises)));

        if (cards.size() <= 2) {
            return sb.append("Nothing.").toString();
        }
        if (numColors == numSymbols) {
            return sb.append("DooDeliDu!").toString();
        }
        if (numColors > numSymbols) {
            var mostCommonColor = findCardWithMostCommonColor(cards.values()).get();
            return sb.append(mostCommonColor.color()).toString();
        }
        else {
            var mostCommonSymbol = findCardWithMostCommonSymbol(cards.values()).get();
            return sb.append(mostCommonSymbol.symbol()).toString();
        }
    }

    private static Optional<Card> findCardWithMostCommonColor(Collection<Card> cards) {
        if (cards.isEmpty()) return Optional.empty();

        Map<Color, Long> colorCounts = cards.stream()
                .collect(Collectors.groupingBy(Card::color, Collectors.counting()));
        long maxCount = Collections.max(colorCounts.values());

        return cards.stream()
                .filter(card -> colorCounts.get(card.color()) == maxCount)
                .findFirst();
    }

    public static Optional<Card> findCardWithMostCommonSymbol(Collection<Card> cards) {
        if (cards.isEmpty()) return Optional.empty();

        // Häufigstes Symbol ermitteln
        Map<Symbol, Long> symbolCounts = cards.stream()
                .collect(Collectors.groupingBy(Card::symbol, Collectors.counting()));

        long maxCount = Collections.max(symbolCounts.values());

        // Finde erste Karte mit diesem Symbol
        return cards.stream()
                .filter(card -> symbolCounts.get(card.symbol()) == maxCount)
                .findFirst();
    }
}
