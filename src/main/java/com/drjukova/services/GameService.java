package com.drjukova.services;

import com.drjukova.model.CellState;
import com.drjukova.model.DeckCount;
import com.drjukova.model.Game;
import com.drjukova.model.Orientation;
import com.drjukova.model.Cell;
import com.drjukova.model.Point;
import com.drjukova.model.Ship;
import com.drjukova.model.BattleField;
import com.drjukova.model.BasePlayer;


import java.util.*;

public class GameService {

    public void fill(BattleField field, List<Ship> ships) {
        setBattlefield(field);
        Random rand = new Random();
        Map<DeckCount, Integer> shipCounter = shipCounter();

        for (int i = 1; i < DeckCount.values().length; i++) {
            DeckCount dc = DeckCount.valueOf(i);

            for (int j = 0; j < shipCounter.get(dc); j++) {
                Orientation orient = rand.nextInt(2) > 0 ? Orientation.Horizontal
                        : Orientation.Vertical;

                for (int k = 0; k < 10; k++) {
                    int x = rand.nextInt(10);
                    int y = rand.nextInt(10);

                    Point coord = new Point(x, y);
                    if (isPosiblePlace(field, orient, dc, coord)) {
                        addShip(field, orient, dc, coord, ships, shipCounter);
                        break;
                    }
                }
            }
        }
    }

    private Map<DeckCount, Integer> shipCounter() {
        Map<DeckCount, Integer> shipCounter = new HashMap<DeckCount, Integer>();
        for (int i = 1; i < DeckCount.values().length; i++) {
            int shipCount = 5 - i;
            shipCounter.put(DeckCount.valueOf(i), shipCount);
        }
        return shipCounter;
    }

    private void addShip(BattleField field, Orientation orient, DeckCount dc, Point startCoord, List<Ship> ships, Map<DeckCount, Integer> shipCounter) {

        int free_places = shipCounter.get(dc);

        if (free_places > 0) {
            Point[] coords = getCoordsForShip(field, orient, dc, startCoord);
            if (coords == null) {
                return;
            }
            List<Cell> cells = new ArrayList<Cell>();

            for (Point point : coords) {
                cells.add(field.getCell(point));
            }
            Ship ship = new Ship(orient, dc, cells);
            ships.add(ship);

            free_places = free_places - 1;
            shipCounter.replace(dc, free_places);

        }
    }

    public boolean noNeighbours(Point position, Point lastPosition, BattleField field) {
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (!(i == 0 && j == 0)) {
                    Point point = new Point(position.getX() + i, position.getY() + j);
                    if (isValidCoord(point, field) && field.getCell(point).getState() == CellState.alive) {
                        if (!(point.getX() == lastPosition.getX() && point.getY() == lastPosition.getY())) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    private boolean isPosiblePlace(BattleField field, Orientation orient, DeckCount dc, Point startCoord) {
        Point step = orient.getDirection();
        boolean isPosiblePlace = true;
        Point position = new Point(startCoord.getX(), startCoord.getY());
        Point lastPosition = new Point(-1, -1);
        for (int i = 0; i < dc.getValue(); i++) {
            isPosiblePlace = isValidCoord(position, field) &&
                    noNeighbours(position, lastPosition, field);

            if (!isPosiblePlace) {
                break;
            }
            lastPosition.setX(position.getX());
            lastPosition.setY(position.getY());
            position.setX(position.getX() + step.getX());
            position.setY(position.getY() + step.getY());
        }

        return isPosiblePlace;
    }

    private Point[] getCoordsForShip(BattleField map, Orientation orientation, DeckCount deckCount, Point startCoord) {
        Point step;
        if (orientation == Orientation.Horizontal) {
            step = new Point(1, 0);
        } else if (orientation == Orientation.Vertical) {
            step = new Point(0, 1);
        } else {
            return null;
        }
        boolean isPosiblePlace = true;
        Point[] coord = new Point[deckCount.getValue()];
        Point position = new Point(startCoord.getX(), startCoord.getY());
        Point lastPosition = new Point(-1, -1);
        for (int i = 0; i < deckCount.getValue(); i++) {
            isPosiblePlace = isValidCoord(position, map) && noNeighbours(position, lastPosition, map);
            if (!isPosiblePlace) {
                break;
            }
            lastPosition.setX(position.getX());
            lastPosition.setY(position.getY());
            coord[i] = new Point(position.getX(), position.getY());
            position.setX(position.getX() + step.getX());
            position.setY(position.getY() + step.getY());
        }
        if (isPosiblePlace) {
            return coord;
        }
        return null;
    }

    private boolean isValidCoord(Point point, BattleField field) {
        return point.getX() >= 0 && point.getX() < field.getWidth() && point.getY() >= 0 && point.getY() < field.getHeight();
    }

    public void setBattlefield(BattleField field) {
        for (int y = 0; y < field.getHeight(); y++) {
            for (int x = 0; x < field.getWidth(); x++) {
                Point point = new Point(x, y);
                field.cells[y][x] = new Cell(point);
                field.getShotPoints().add(field.cells[y][x]);

            }
        }
    }

    public void setGame(Game game) {
        for (int i = 0; i < game.getN(); i++) {
            game.getPlayerList().add(new BasePlayer());
            fill(game.getPlayerList().get(i).field, game.getPlayerList().get(i).ships);
        }
    }

    private void step(int n, Game game) {
        n++;
        if (n >= game.getPlayerList().size()) {
            game.setCurrentPlayerIndex(0);
        } else {
            game.setCurrentPlayerIndex(1);
        }
    }


    public void shotStep(Game game) {
        if (isAlivePlayer(game, 0) && isAlivePlayer(game, 1)) {//пока живы оба игрока
            if (!doShot(game.getPlayerList().get(game.getCurrentPlayerIndex()))) {
                step(game.getCurrentPlayerIndex(), game);
            }
        }
        if (!(isAlivePlayer(game, 0)) || !(isAlivePlayer(game, 1))) {
            if (whoWin(game) == -1) {
                System.out.println("Ничья");
            } else {
                System.out.println("Выйграл : " + whoWin(game));
            }
        }
    }

    public Integer whoWin(Game game) {
        int flag = -1;
        for (int i = 0; i < game.getPlayerList().size(); i++) {
            if (isAlive(game.getPlayerList().get(i))) {
                if (flag != -1) {
                    return null;
                }
                flag = i;
            }
        }
        return flag + 1;
    }

    public boolean isAlivePlayer(Game game, int i) {
        if (isAlive(game.getPlayerList().get(i))) {
            return true;
        }
        return false;
    }

    public boolean isAlive(BasePlayer player) {
        for (Ship ship : player.getShips()) {
            if (isAliveShip(ship)) {
                return true;
            }
        }
        return false;
    }

    public boolean isAliveShip(Ship ship) {
        for (Cell deck : ship.getDecks()) {
            if (deck.getState() == CellState.alive) {
                return true;
            }
        }
        return false;
    }

    private boolean doShot(BasePlayer player) {
        int size = player.getBattleField().getShotPoints().size();
        Cell cell = player.getBattleField().getShotPoints().get((int) (Math.random() * (size)));//получаем рандомную клетку для обстрела
        if (cell.getState() == CellState.alive) { //если является кораблем, меняем на состояние ранен
            cell.setState(CellState.injured);
            for (Ship ship : player.getShips()) {
                if (ship.getDecks().contains(cell)) {
                    if (!isAliveShip(ship)) {
                        board(player.getBattleField(), ship);
                    }
                }
            }
            return true;
        } else if (cell.getState() == CellState.empty) {
            cell.setState(CellState.missed);
        }
        player.getBattleField().getShotPoints().remove(cell);//удаляем точку, по которой стреляли

        return false;
    }

    private void board(BattleField field, Ship ship) {//выделения борта мертвого корабля статусом мимо
        for (Cell c : ship.getDecks()) {
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    if (!(i == 0 && j == 0)) {
                        Point point = new Point(c.getPosition().getY() + j, c.getPosition().getX() + i);
                        if (isValidCoord(point, field) && field.getCell(point).getState() == CellState.empty) {
                            field.getShotPoints().remove(field.getCell(point));//если координата борта валидна и пустая, то удаляем из списка обстрела
                            field.getCell(point).setState(CellState.missed);//меняем на статус мимо
                        }
                    }
                }
            }
        }
    }
}

