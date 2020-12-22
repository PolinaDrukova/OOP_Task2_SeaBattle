package com.drjukova.model;

import java.util.ArrayList;
import java.util.List;

public class BasePlayer {
    public BattleField field = new BattleField();
    public List<Ship> ships = new ArrayList<>();

    public BattleField getBattleField() {
        return field;
    }

    public List<Ship> getShips() {
        return ships;
    }
}
