package com.example.gui;

import java.io.IOException;
import java.util.Objects;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class HelloApplication extends Application {
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(
                getClass().getResource("/Difficulty.fxml")));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(FXMLLoader loader) throws IOException {
        scene.setRoot(loader.load());
    }

    public static void main(String[] args) {
        launch();
    }
}