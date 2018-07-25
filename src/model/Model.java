package model;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

public interface Model {

    void fireAt(String coords);

    ObservableList<ObservableList<BoardCoord>> getFirePositions();

    ObservableList<ObservableList<BoardCoord>> getShipPositions();

    void addBoardListener(ListChangeListener boardListener);

    int getBoardSize();
}
