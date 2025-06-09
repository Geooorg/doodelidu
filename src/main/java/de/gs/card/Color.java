package de.gs.card;


public enum Color {
    GREEN, PINK, YELLOW;

    public String getUtf8Name() {
        return switch (this) {
            case GREEN -> "🟩";
            case PINK -> "🩷";
            case YELLOW -> "🟨";
        };
    }
}
