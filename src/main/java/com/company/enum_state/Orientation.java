package com.company.enum_state;

import com.company.objects.Point;

public enum Orientation {
    None(-1),
    Horizontal(0),
    Vertical(1);

    private final int value;

    private Orientation( int value ) {
        this.value = value;
    }

    public Point getDirection() {

        return value > 0 ? new Point(0, 1) : new Point(1, 0);
    }
}
