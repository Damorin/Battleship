package ui;

import model.Board;
import model.State;

public class CommandLine implements View {
    private State game;

    public CommandLine() {
        game = new Board(5);
    }
}
