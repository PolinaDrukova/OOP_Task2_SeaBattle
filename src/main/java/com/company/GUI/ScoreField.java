package com.company.GUI;

import com.company.GameMap.Game;
import com.company.objects.Ship;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

public  class ScoreField extends JPanel {
    private static final long serialVersionUID = 1L;
    private Game game;
    private int ships;
    private int player;

    public ScoreField(Game model, int player) {
        this.game = model;
        this.player = player;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int[] m = new int[4];

        int i;
        for ( i = 0; i < m.length; ++i) {
            m[i] = 0;
        }

        this.ships = 0;
        Iterator<Ship> var4 = game.getPlayers(player).getShips().iterator();

        while (var4.hasNext()) {
            Ship ship = var4.next();
            if (ship.isAliveShip()) {
                ++m[ship.getDecks().size() - 1];
                ++this.ships;
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


        String st = "Корабли : ".concat(String.valueOf(this.ships));
        g.drawString(st, 10, 100);

    }
}

