package com.drjukova.cell;

import com.drjukova.point.Point;

public class Cell {
    private Point position;
    public CellState state;

    public Cell(Point position) {
        this.position = position;
        this.state = CellState.empty;
    }

    public CellState getState() {
        return state;
    }

    public Point getPosition() {
        return position;
    }

    public void setState(CellState state) {
        this.state = state;
    }

}
