package com.drjukova.model;

import java.util.List;

public class Ship {
    private Orientation orientation;
    private DeckCount deckCount;
    private List<Cell> decks;


    public Ship(Orientation orientation, DeckCount deckCount, List<Cell> cells) {
        this.orientation = orientation;
        this.deckCount = deckCount;
        this.decks = cells;
        for (Cell cell : decks) {
            cell.setState(CellState.alive);
        }
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public DeckCount getDeckCount() {
        return deckCount;
    }

    public List<Cell> getDecks() {
        return decks;
    }

}
