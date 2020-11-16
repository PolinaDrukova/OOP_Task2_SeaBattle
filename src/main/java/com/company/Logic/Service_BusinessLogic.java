package com.company.Logic;

import com.company.GameMap.BattleField;
import com.company.GameMap.Game;
import com.company.Player.BasePlayer;
import com.company.enum_state.CellState;
import com.company.enum_state.DeckCount;
import com.company.enum_state.Orientation;
import com.company.objects.Cell;
import com.company.objects.Point;
import com.company.objects.Ship;

import java.util.*;

public class Service_BusinessLogic {
    public void fill(BattleField field, List<Ship> ships) {
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
                    if (field.isValidCoord(point) && field.getCell(point).getState() == CellState.alive) {
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
            isPosiblePlace = field.isValidCoord(position) &&
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
            isPosiblePlace = map.isValidCoord(position) && noNeighbours(position, lastPosition, map);
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

    public void newGame(Game game) {
        while (isAlivePlayer(game, 0) && isAlivePlayer(game, 1)) {
            if (!shot(game.getPlayers(game.getCurrentIndex()))) {
                game.step();
            }
        }
        if (whoWin(game) == -1) {
            System.out.println("Ничья");

        } else {
            System.out.println("Выйграл : " + whoWin(game));
        }
    }


    public void gameStep(Game game) {
        if (isAlivePlayer(game, 0) && isAlivePlayer(game, 1)) {
            if (!shot(game.getPlayers(game.getCurrentIndex()))) {
                game.step();
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
        for (int i = 0; i < game.getPlayers().length; i++) {
            if (game.getPlayers(i).isAlive()) {
                if (flag != -1) {
                    return null;
                }
                flag = i;
            }
        }
        return flag + 1;
    }

    public boolean isAlivePlayer(Game game, int i) {
        if (game.getPlayers(i).isAlive()) {
            return true;
        }
        return false;
    }

    private boolean shot(BasePlayer player) {
        int size = player.getBattleField().getShotPoints().size(); //получаем точку из списка точек доступных для стрельбы и удаляем
        Cell cell = player.getBattleField().getShotPoints().get(random(0, size));
        player.getBattleField().getShotPoints().remove(cell);//удаляем точку, по которой стреляли

        if (cell.state == CellState.alive) { //если является кораблем, меняем на состояние ранен
            cell.setState(CellState.injured);

            for (Ship ship : player.getShips()) {
                if (ship.getDecks().contains(cell)) {
                    if (!ship.isAliveShip()) {
                        boards(player.getBattleField(), ship);
                    }
                }

            }
            return true;
        } else if (cell.state == CellState.empty) {
            cell.setState(CellState.missed);
        }
        return false;
    }


    private void boards(BattleField field, Ship ship) {
        for (Cell c : ship.getDecks()) {
            int x = c.getPosition().getX();
            int y = c.getPosition().getY();

            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    Point point = new Point( y + j, x + i);
                    if (field.isValidCoord(point)) {
                        Cell cell = field.getCell(point);
                        field.getShotPoints().remove(cell);
                        if (cell.getState() == CellState.empty) {
                            cell.setState(CellState.missed);
                        }
                    }
                }
            }
        }

    }

    private int random(int x, int y) {
        return (int) (Math.random() * (y - x)) + x;
    }

}

