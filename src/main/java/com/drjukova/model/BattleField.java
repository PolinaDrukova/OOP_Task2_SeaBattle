package com.drjukova.model;

import com.drjukova.services.GameService;

import java.util.ArrayList;
import java.util.List;

public class BattleField {
    public final int width = 10;
    public final int height = 10;
    protected List<Cell> shotPoints = new ArrayList<Cell>();//список  клеток дступных для обстрела
    public Cell[][] cells = new Cell[height][width];


    public BattleField() {
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

    public Cell[][] getCells() {
        return cells;
    }

    public void setShotPoints(List<Cell> cells){
        shotPoints = cells;
    }

    public void setBattlefieldCells(Cell[][] cells) {
        this.cells = cells;
    }
}
