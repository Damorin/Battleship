package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import model.Model;
import ui.View;

public class BoardController implements Controller {

    private Model model;
    private View view;

    public BoardController(Model model, View view) {
        this.model = model;
        this.view = view;

        this.view.addFireListener(new FireListener());
    }

    @Override
    public void runGame() {
        view.runGame();
    }

    class FireListener implements ChangeListener {
        @Override
        public void changed(ObservableValue observable, Object oldValue, Object newValue) {
            model.fireAt(newValue.toString());
            model.displayBoards();
        }
    }
}


