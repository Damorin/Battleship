package ui;


import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import model.BoardCoord;

public interface View {

    void displayErrorMessage(String errorMessage);

    void runGame();

    void addFireListener(ChangeListener fireListener);

    void displayBoards(ObservableList<ObservableList<BoardCoord>> firePositions, int SIZE);

}
