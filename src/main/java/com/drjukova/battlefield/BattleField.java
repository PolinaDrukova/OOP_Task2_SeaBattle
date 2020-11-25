package com.drjukova.battlefield;

import com.drjukova.cell.Cell;
import com.drjukova.gameLogic.GameLogic;
import com.drjukova.point.Point;

import java.util.ArrayList;
import java.util.List;

public class BattleField {
    public final int width = 10;
    public final int height = 10;

    protected List<Cell> shotPoints = new ArrayList<Cell>();//список  клеток дступных для обстрела
    public Cell[][] cells = new Cell[height][width];
    GameLogic logic = new GameLogic();

    public BattleField() {
        logic.setBattlefield(this);
    }

    public Cell getCell(Point point) {
        return cells[point.getX()][point.getY()];
    }

    public List<Cell> getShotPoints() {
        return shotPoints;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
