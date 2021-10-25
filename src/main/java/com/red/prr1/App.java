package com.red.prr1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Board board = new Board();
        Scene scene = new Scene(board,500 , 525);
        stage.setTitle("Simulation");
        stage.setScene(scene);
        stage.show();
        board.out();
    }

    public static void main(String[] args) {
        launch();
    }
}