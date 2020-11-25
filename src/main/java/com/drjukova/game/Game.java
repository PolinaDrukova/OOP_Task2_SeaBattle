package com.drjukova.game;

import com.drjukova.gameLogic.GameLogic;
import com.drjukova.player.BasePlayer;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final int n = 2;
    private List<BasePlayer> playerList = new ArrayList<>();//список игроков
    private int currentPlayerIndex = 0;
    GameLogic logic = new GameLogic();

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
