package com.drjukova.gui;

import com.drjukova.model.Game;
import com.drjukova.services.GameService;
import com.drjukova.model.Ship;

import javax.swing.*;
import java.awt.*;

public class ScoreField extends JPanel {
    private Game game;
    private int player;
    private GameService logic;

    public ScoreField(Game model, int player) {
        this.game = model;
        this.player = player;
        this.logic = new GameService();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int[] m = new int[4];
        int i;
        int ships = 0;

        for (Ship ship : game.getPlayerList().get(player).getShips()) {

            if (ship != null) {
                if (logic.isAliveShip(ship)) {
                    ships++;
                    m[ship.getDecks().size() - 1]++;
                }
            }
        }
        for (i = 0; i < 4; ++i) {
            for (int j = 0; j < i + 1; ++j) {
                g.setColor(Color.gray);
                g.fillRect(j * 15 + 10, i * 15 + 5, 13, 13);
            }

            g.setColor(Color.black);
            g.drawString(String.valueOf(m[i]), 75, i * 15 + 16);
        }


        String st = "Корабли : ".concat(String.valueOf(ships));
        g.drawString(st, 10, 100);

    }
}

