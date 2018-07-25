package model;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.util.*;

public class Board implements Model {

    private static final int SIZE = 10;

    private Random rng;

    private ObservableList<ObservableList<BoardCoord>> shipPositions;
    private ObservableList<ObservableList<BoardCoord>> firePositions;

    private Map<String, Integer> alphaMappings;

    private Map<Ship, Integer> shipSizes;

    public Board() {

        rng = new Random();

        alphaMappings = new HashMap<>();
        alphaMappings.put("A", 0);
        alphaMappings.put("B", 1);
        alphaMappings.put("C", 2);
        alphaMappings.put("D", 3);
        alphaMappings.put("E", 4);
        alphaMappings.put("F", 5);
        alphaMappings.put("G", 6);
        alphaMappings.put("H", 7);
        alphaMappings.put("I", 8);
        alphaMappings.put("J", 9);

        initializeBoards();

        initializeShipTypes();
        addShip(Ship.CARRIER);
        addShip(Ship.BATTLESHIP);
        addShip(Ship.CRUISER);
        addShip(Ship.SUBMARINE);
        addShip(Ship.SUBMARINE);
        addShip(Ship.DESTROYER);
    }

    private void initializeBoards() {
        shipPositions = FXCollections.observableArrayList();
        for (int i = 0; i < SIZE; i++) {
            shipPositions.add(FXCollections.observableArrayList());
            for (int j = 0; j < SIZE; j++) {
                shipPositions.get(i).add(BoardCoord.EMPTY);
            }
        }

        firePositions = FXCollections.observableArrayList();
        for (int i = 0; i < SIZE; i++) {
            firePositions.add(FXCollections.observableArrayList());
            for (int j = 0; j < SIZE; j++) {
                firePositions.get(i).add(BoardCoord.EMPTY);
            }
        }
    }

    @Override
    public ObservableList<ObservableList<BoardCoord>> getFirePositions() {
        return firePositions;
    }

    @Override
    public ObservableList<ObservableList<BoardCoord>> getShipPositions() {
        return shipPositions;
    }

    @Override
    public void fireAt(String coords) {
        int x = alphaMappings.get(coords.substring(0, 1).toUpperCase());
        int y = Integer.parseInt(coords.substring(1)) - 1;
        if (coords.length() == 2) {
            if (shipPositions.get(y).get(x) == BoardCoord.SHIP) {
                firePositions.get(y).set(x, BoardCoord.HIT);
                System.out.println(firePositions.get(y).get(x));
            } else {
                firePositions.get(y).set(x, BoardCoord.MISS);
                System.out.println(firePositions.get(y).get(x));
            }
        }
    }

    @Override
    public void addBoardListener(ListChangeListener boardListener) {
        for (ObservableList<BoardCoord> row : firePositions) {
            row.addListener(boardListener);
        }
    }

    @Override
    public int getBoardSize() {
        return this.SIZE;
    }

    private void addShip(Ship type) {

        int x, y;

        int shipSize = shipSizes.get(type);

        boolean isHorizontal = rng.nextBoolean();
        boolean isShipPlaceable = false;

        while (!isShipPlaceable) {
            x = rng.nextInt(SIZE - shipSize);
            y = rng.nextInt(SIZE - shipSize);


            if (shipPositions.get(y).get(x) == BoardCoord.EMPTY) {
                isShipPlaceable = true;
                if (isHorizontal) {
                    for (int j = 0; j < shipSize; j++) {
                        if (shipPositions.get(y).get(x + j) == BoardCoord.SHIP) {
                            isShipPlaceable = false;
                        }
                    }
                    if (isShipPlaceable) {
                        for (int k = 0; k < shipSize; k++) {
                            shipPositions.get(y).add(x + k, BoardCoord.SHIP);
                        }
                    }
                } else {
                    for (int j = 0; j < shipSize; j++) {
                        if (shipPositions.get(y + j).get(x) == BoardCoord.SHIP) {
                            isShipPlaceable = false;
                        }
                    }
                    if (isShipPlaceable) {
                        for (int k = 0; k < shipSize; k++) {
                            shipPositions.get(y + k).add(x, BoardCoord.SHIP);
                        }
                    }
                }
            }
        }

        System.out.println(type.toString() + " placed");

    }

    private void initializeShipTypes() {
        shipSizes = new HashMap<>();
        shipSizes.put(Ship.CARRIER, 5);
        shipSizes.put(Ship.BATTLESHIP, 4);
        shipSizes.put(Ship.CRUISER, 3);
        shipSizes.put(Ship.SUBMARINE, 3);
        shipSizes.put(Ship.DESTROYER, 2);
    }
}
