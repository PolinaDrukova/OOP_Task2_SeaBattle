package com.company.objects;

import com.company.enum_state.CellState;
import com.company.enum_state.DeckCount;
import com.company.enum_state.Orientation;

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


    public boolean isAliveShip() {
        for (Cell deck : decks) {
            if (deck.getState() == CellState.alive) {
                return true;
            }
        }
        return false;
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
