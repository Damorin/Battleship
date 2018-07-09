package model;

import java.util.ArrayList;
import java.util.List;

public class Board implements State {

    private List<List<BoardCoord>> grid;
    private int size;


    public Board(int size) {
        grid = new ArrayList<>();
        this.size = size;
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                grid.get(i).add(BoardCoord.EMPTY);
            }
        }
    }

    public void fireAt(int x, int y) {

    }
}
