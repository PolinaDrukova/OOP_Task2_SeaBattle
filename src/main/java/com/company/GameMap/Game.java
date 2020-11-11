package com.company.GameMap;

import com.company.Logic.Service_BusinessLogic;
import com.company.Player.BasePlayer;

public class Game {
    private int n = 2;
    private BasePlayer[] players = new BasePlayer[n];
    private int currentIndex = 0;
    Service_BusinessLogic logic = new Service_BusinessLogic();

    public Game() {
        for (int i = 0; i < n; i++) {
            players[i] = new BasePlayer();
            logic.fill(players[i].field, players[i].ships);
        }
    }

    public void step() {
        currentIndex++;
        if (currentIndex >= players.length) {
            currentIndex = 0;
        }
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public BasePlayer getPlayers(int i) {
        return players[i];
    }

    public BasePlayer[] getPlayers() {
        return players;
    }

}
