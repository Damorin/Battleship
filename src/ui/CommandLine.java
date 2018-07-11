package ui;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CommandLine implements View {

    private SimpleStringProperty firePosition = new SimpleStringProperty();

    public void runGame() {


        Scanner scanner = new Scanner(System.in);
        String input = "";

        List<String> tries = new ArrayList<>();

        while (!input.equals("quit")) {
            System.out.println("Enter coordinates: ");
            input = scanner.next().toLowerCase();

            if (input.equals("quit")) {
                System.out.println("Thanks for playing!");
            } else if (tries.contains(input)) {
                System.out.println("You already fired there, try again!");
            } else {
                tries.add(input);
                System.out.println("Firing at " + input.toUpperCase());
                firePosition.setValue(input);
            }

        }

        scanner.close();

    }

    @Override
    public void addFireListener(ChangeListener fireListener) {
        firePosition.addListener(fireListener);
    }


    @Override
    public void displayErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }
}
