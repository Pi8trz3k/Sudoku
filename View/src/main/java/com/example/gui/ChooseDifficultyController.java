package com.example.gui;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import org.example.Authors;
import org.example.DiffcultEnum;




public class ChooseDifficultyController {

    @FXML
    private RadioButton easy;
    @FXML
    private RadioButton medium;
    @FXML
    private RadioButton hard;

    @FXML
    private Button playButton;

    @FXML
    private Button plButton;

    @FXML
    private Button enButton;

    @FXML
    private Label mainText;

    @FXML
    private Label author1;

    @FXML
    private Label author2;

    private int langFlag = 0;

    Locale lang = new Locale("pl");

    ResourceBundle langText;


    private int difficulty = 0;

    @FXML
    public void initialize() {
        langText = ResourceBundle.getBundle("MenuText", lang);
        easy.setText(langText.getString("easy"));
        medium.setText(langText.getString("medium"));
        hard.setText(langText.getString("hard"));
        playButton.setText(langText.getString("play"));
        mainText.setText(langText.getString("main"));
        Authors autorzy = new Authors();
        author1.setText(autorzy.getString("author1"));
        author2.setText(autorzy.getString("author2"));

    }

    @FXML
    private void play() throws IOException {

        DiffcultEnum difficultyLevel = null;

        if (difficulty == 0) {
            difficultyLevel = DiffcultEnum.easy;
        }
        if (difficulty == 1) {
            difficultyLevel = DiffcultEnum.medium;
        }
        if (difficulty == 2) {
            difficultyLevel = DiffcultEnum.hard;
        }


        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Board.fxml"));
        HelloApplication.setRoot(loader);
        BoardDrawController bdc = loader.getController();
        bdc.init(difficultyLevel, lang);
    }

    @FXML
    public void getDifficulty(javafx.event.ActionEvent actionEvent) {
        if (easy.isSelected()) {
            difficulty = 0;
        } else if (medium.isSelected()) {
            difficulty = 1;
        } else if (hard.isSelected()) {
            difficulty = 2;
        }
    }

    @FXML
    public void plButtonOn() {
        langFlag = 0;
        lang = new Locale("pl");
        initialize();
    }

    @FXML
    public void enButtonOn() {
        langFlag = 1;
        lang = new Locale("en");
        initialize();
    }
}
