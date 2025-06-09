package de.gs.card;


public enum Symbol {
    CAMEL, FLAMINGO, PENGUIN, TORTOISE, ZEBRA; // CROCO

    public String getUtf8Name() {
            return switch (this) {
                case CAMEL -> "🐪";
                case FLAMINGO -> "🦩";
                case PENGUIN -> "🐧";
                case TORTOISE -> "🐢";
                case ZEBRA -> "🦓";
            };
        }
}
