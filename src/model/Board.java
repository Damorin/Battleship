package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board implements Model {

    private static final int SIZE = 10;

    private List<List<BoardCoord>> enemyShipPositions;

    private List<List<BoardCoord>> friendlyShipPositions;
    private List<List<BoardCoord>> friendlyFirePositions;

    private Map<String, Integer> alphaMappings;

    public Board() {
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
    }

    private void initializeBoards() {
        enemyShipPositions = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            enemyShipPositions.add(new ArrayList<>());
            for (int j = 0; j < SIZE; j++) {
                enemyShipPositions.get(i).add(BoardCoord.EMPTY);
            }
        }

        friendlyShipPositions = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            friendlyShipPositions.add(new ArrayList<>());
            for (int j = 0; j < SIZE; j++) {
                friendlyShipPositions.get(i).add(BoardCoord.EMPTY);
            }
        }

        friendlyFirePositions = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            friendlyFirePositions.add(new ArrayList<>());
            for (int j = 0; j < SIZE; j++) {
                friendlyFirePositions.get(i).add(BoardCoord.EMPTY);
            }
        }
    }

    public List<List<BoardCoord>> getEnemyShipPositions() {
        return enemyShipPositions;
    }

    public List<List<BoardCoord>> getFriendlyFirePositions() {
        return friendlyFirePositions;
    }

    public List<List<BoardCoord>> getFriendlyShipPositions() {
        return friendlyShipPositions;
    }

    @Override
    public void fireAt(String coords) {
        int yPos = Integer.parseInt(coords.substring(1))-1;
        int xPos = alphaMappings.get(coords.substring(0, 1).toUpperCase());
        if (coords.length() == 2) {
            friendlyFirePositions.get(yPos).add(xPos, BoardCoord.HIT);
        } else {
            friendlyFirePositions.get(yPos).add(xPos, BoardCoord.MISS);
        }
    }

    @Override
    public void displayBoards() {
        for(int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                System.out.print(friendlyFirePositions.get(y).get(x));
            }
            System.out.println();
        }
    }


}
