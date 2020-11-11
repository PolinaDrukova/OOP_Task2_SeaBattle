package com.company.enum_state;

public enum  DeckCount {
    Invalid(-1),
    One(1),
    Two(2),
    Three(3),
    Four(4);

    private final int value;
    private static DeckCount[] decks;

    static {
        decks = new DeckCount[5];
        int counter = 0;
        for (DeckCount d : DeckCount.values()) {
            decks[counter++] = d;
        }
    }

    DeckCount(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static DeckCount valueOf(int i) {
        if (i >= 0 && i < 5) {
            return decks[i];
        }
        return Invalid;
    }
}
