package com.example.gui;

import java.util.Locale;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import org.example.BacktrackingSudokuSolver;
import org.example.FileSudokuBoardDao;
import org.example.SudokuBoard;



public class BoardDrawController {

    FileChooser fc = new FileChooser();
    Locale lang = new Locale("pl");
    ResourceBundle langText;
    private boolean isLoadedFromFile = false;
    private SudokuBoard sudokuBoardCopy = new SudokuBoard(new BacktrackingSudokuSolver());
    boolean isValid = true;

    @FXML
    private GridPane gridPane;

    @FXML
    private Button loadButton;

    @FXML
    private Button saveButton;

    @FXML
    private Button checkButton;

    @FXML
    private Label winLose;


    public void changeLang(Locale newLang) {
        lang = newLang;
        langText = ResourceBundle.getBundle("BoardText", lang);
        saveButton.setText(langText.getString("saveButton"));
        loadButton.setText(langText.getString("loadButton"));
        checkButton.setText(langText.getString("checkButton"));
    }

    public void draw(SudokuBoard sudoku, Locale newLang) {
        changeLang(newLang);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                TextField textField = new TextField();
                textField.setMaxSize(30, 30);
                textField.setFont(Font.font(15));
                textField.setAlignment(Pos.CENTER);
                if (sudoku.get(i, j) == 0) {
                    textField.setText(Integer.toString(sudoku.get(i, j)));
                }
                if (sudoku.get(i, j) != 0) {
                    textField.setEditable(false);
                    textField.setText(Integer.toString(sudoku.get(i, j)));
                }

                gridPane.add(textField, i, j);

            }
        }
    }

    @FXML
    public void checkButtonOn() {
        if (isSolved() && isValid()) {
            winLose.setText(langText.getString("win"));
        } else {
            winLose.setText(langText.getString("lose"));
        }
    }

    @FXML
    public void loadButtonOn() throws CloneNotSupportedException {
        isLoadedFromFile = true;
        sudokuBoardCopy = sudokuLoad().clone();
    }

    @FXML
    public void saveButtonOn() {
        sudokuSave();
    }

    public void sudokuSave() {
        try (FileSudokuBoardDao dao = new FileSudokuBoardDao("sudokuBoardSave.txt")) {
            dao.write(sudokuBoardCopy);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public SudokuBoard sudokuLoad() {
        try (FileSudokuBoardDao dao = new FileSudokuBoardDao("sudokuBoardSave.txt")) {
            sudokuBoardCopy = dao.read();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return sudokuBoardCopy;
    }

    public boolean isSolved() {
        boolean win = true;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                String fieldNumber = ((TextField) gridPane
                        .getChildren().get(i * 9 + j)).getText();
                if (!String.valueOf(sudokuBoardCopy.get(i, j)).equals(fieldNumber)) {
                    win = false;
                }
            }
        }
        return win;
    }

    public boolean isValid() {
        boolean valid = true;
        for (int i = 0; i < 9; i++) {
            String fieldNumber = ((TextField) gridPane.getChildren().get(i)).getText();
            if (!(fieldNumber.matches("[1 9]")) || (fieldNumber.equals(""))) {
                valid = false;
            }
        }
        return valid;
    }


}