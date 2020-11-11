package com.company.GameMap;

import com.company.objects.Cell;
import com.company.objects.Point;

import java.util.ArrayList;
import java.util.List;

public class BattleField {
    public int width = 10;
    public int height = 10;

    protected List<Cell> shotPoints = new ArrayList<Cell>();//список  клеток дступных для обстрела

    private Cell[][] cells = new Cell[height][width];

    public BattleField() {

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Point point = new Point(x, y);
                if (isValidCoord(point)) {
                    this.cells[y][x] = new Cell(point);
                    shotPoints.add(this.cells[y][x]);
                }
            }
        }
    }

    public boolean isValidCoord(Point point) {
        return point.getX() >= 0 && point.getX() < width && point.getY() >= 0 && point.getY() < height;
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
