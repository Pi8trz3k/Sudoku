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
import org.example.*;


public class BoardDrawController {

    private ResourceBundle langText;
    private SudokuBoard sudoku;
    private SudokuBoard sudokuCopy = new SudokuBoard(new BacktrackingSudokuSolver());
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
        langText = ResourceBundle.getBundle("BoardText", newLang);
        saveButton.setText(langText.getString("saveButton"));
        loadButton.setText(langText.getString("loadButton"));
        checkButton.setText(langText.getString("checkButton"));
    }

    public void init(DiffcultEnum df, Locale lang) {
        sudoku = new SudokuBoard(new BacktrackingSudokuSolver());
        sudoku.solveGame();
        try {
            sudokuCopy = sudoku.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        df.deleteFields(sudoku);
        changeLang(lang);
        draw(sudoku);
    }

    public void draw(SudokuBoard sudoku) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                TextField textField = new TextField();
                textField.setMaxSize(30, 30);
                textField.setFont(Font.font(15));
                textField.setAlignment(Pos.CENTER);
                if (sudoku.get(i, j) == 0) {
                    textField.setText(Integer.toString(sudoku.get(i, j)));
                    textField.setStyle("-fx-control-inner-background: grey");
                } else if (sudoku.isEditable(i,j)) {
                    textField.setText(Integer.toString(sudoku.get(i, j)));
                    textField.setStyle("-fx-control-inner-background: grey");
                } else {
                    textField.setEditable(false);
                    textField.setText(Integer.toString(sudoku.get(i, j)));
                }

                gridPane.add(textField, i, j);

            }
        }
    }


    @FXML
    public void checkButtonOn() {
        if (isSudokuSolved() && isInputValid()) {
            winLose.setText(langText.getString("win"));
        } else {
            winLose.setText(langText.getString("lose"));
        }
    }

    @FXML
    public void loadButtonOn() {
        try (Dao<SudokuBoard> dao = SudokuBoardDaoFactory.getFileDao("SudokuSaveFile")) {
            sudoku = dao.read();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try (Dao<SudokuBoard> dao = SudokuBoardDaoFactory.getFileDao("SudokuCopySaveFile")) {
            sudokuCopy = dao.read();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        draw(sudoku);
    }

    @FXML
    public void saveButtonOn() {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (sudoku.get(i, j) == 0) {
                        String fieldNumber = ((TextField)
                                gridPane.getChildren().get(i * 9 + j)).getText();
                        if (!(Integer.parseInt(fieldNumber) == 0)) {
                            sudoku.set(i, j, Integer.parseInt(fieldNumber));
                            sudoku.setEditable(i,j);
                        } else {
                            sudoku.set(i, j, 0);
                        }
                    }
                }
            }
            try (Dao<SudokuBoard> dao = SudokuBoardDaoFactory.getFileDao("SudokuSaveFile")) {
                dao.write(sudoku);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        try (Dao<SudokuBoard> dao = SudokuBoardDaoFactory.getFileDao("SudokuCopySaveFile")) {
            dao.write(sudokuCopy);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        }

    public boolean isSudokuSolved() {
        boolean isWon = true;

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                String fieldNumber = ((TextField)
                        gridPane.getChildren().get(i * 9 + j)).getText();
                if (!String.valueOf(sudokuCopy.get(i, j)).equals(fieldNumber)) {
                    isWon = false;
                }
            }
        }
        return isWon;
    }

    public boolean isInputValid() {
        boolean valid = true;
        for (int i = 0; i < 81; i++) {
            String fieldNumber = ((TextField) gridPane.getChildren().get(i)).getText();
            if (!((fieldNumber.matches("[1-9]")) || (fieldNumber.equals("")))) {
                valid = false;
            }
        }
        return valid;
    }


}