package com.drjukova.player;

import com.drjukova.ship.Ship;
import com.drjukova.battlefield.BattleField;

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
