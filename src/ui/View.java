package ui;


import javafx.beans.value.ChangeListener;

public interface View {

    void displayErrorMessage(String errorMessage);

    void runGame();

    void addFireListener(ChangeListener fireListener);

}
