package com.drjukova.gui;

import com.drjukova.cell.CellState;
import com.drjukova.game.Game;
import com.drjukova.point.Point;

import java.awt.*;

public class Field extends PanelField {
    private int player;

    public Field(Game game, int player) {
        super(game);
        this.player = player;
    }

    private Color getColorByStateElement(CellState state) {
        switch (state) {
            case empty:
                return new Color(215, 215, 255);
            case alive:
                return Color.green;
            case injured:
                return Color.red;
            case missed:
                return Color.BLUE;
        }
        return Color.blue;
    }

    @Override
    public void paintElement(Graphics g, int i, int j) {
        Point point = new Point(i, j);
        CellState state = game.getPlayerList().get(player).getBattleField().getCell(point).getState();
        g.setColor(getColorByStateElement(state));
        if (state == CellState.missed) {
            g.fillRect(i * 15 + 6, j * 15 + 6, 4, 4);
        } else {
            g.fillRect(i * 15 + 1, j * 15 + 1, 14, 14);
        }

    }
}

