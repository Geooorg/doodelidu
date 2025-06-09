package de.gs.card;


public record Card(Color color, Symbol symbol) {

    @Override
    public String toString() {
        return color.getUtf8Name() + " " + symbol.getUtf8Name();
    }
}
