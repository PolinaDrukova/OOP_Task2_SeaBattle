package com.drjukova.model;

public class Cell {
    private Point position;
    private CellState state;

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
