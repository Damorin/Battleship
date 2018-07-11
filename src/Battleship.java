import controller.BoardController;
import controller.Controller;
import model.Board;
import model.Model;
import ui.CommandLine;
import ui.View;

public class Battleship {

    public static void main(String[] args) {
        Model model = new Board();
        View view = new CommandLine();
        Controller controller = new BoardController(model, view);
        controller.runGame();
    }
}
