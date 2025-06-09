package de.gs;

import lombok.Getter;

@Getter
public enum State {
    INIT,
    DISTRIBUTE_CARDS,
    NEXT_PLAYER,
    NEXT_ROUND,
    EVALUATE,
    END;

    public String getName() {
        return this.name();
    }

    @Override
    public String toString() {
        return this.name();
    }
}