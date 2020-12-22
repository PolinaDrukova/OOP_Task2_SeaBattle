package com.drjukova.model;

import com.drjukova.services.GameService;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final int n = 2;
    private List<BasePlayer> playerList = new ArrayList<>();//список игроков
    private int currentPlayerIndex = 0;
    GameService logic = new GameService();

    public Game() {
        logic.setGame(this);
    }

    public List<BasePlayer> getPlayerList() {
        return playerList;
    }

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }
    public void setCurrentPlayerIndex(int currentPlayerIndex) {
        this.currentPlayerIndex = currentPlayerIndex;
    }

    public int getN() {
        return n;
    }
}
