package com.example.gui;


import java.io.File;
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
import javafx.stage.Stage;
import org.example.BacktrackingSudokuSolver;
import org.example.DiffcultEnum;
import org.example.FileSudokuBoardDao;
import org.example.SudokuBoard;




public class BoardDrawController {

    private DiffcultEnum diffcultEnum;
    private Stage stage;
    private FileChooser fc = new FileChooser();
    private Locale lang = new Locale("pl");
    private ResourceBundle langText;
    private boolean isLoadedFromFile = false;
    private SudokuBoard sudoku;
    private SudokuBoard sudokuCopy = new SudokuBoard(new BacktrackingSudokuSolver());
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

    public void draw(DiffcultEnum df, Locale newLang) {
        diffcultEnum = df;
        sudoku = new SudokuBoard(new BacktrackingSudokuSolver());
        sudoku.solveGame();
        try {
            sudokuCopy = sudoku.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        diffcultEnum.deleteFields(sudoku);
        changeLang(newLang);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                TextField textField = new TextField();
                textField.setMaxSize(30, 30);
                textField.setFont(Font.font(15));
                textField.setAlignment(Pos.CENTER);
                if (sudoku.get(i, j) == 0) {
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
    public void loadButtonOn() throws CloneNotSupportedException {
        isLoadedFromFile = true;
        sudoku = sudokuLoad().clone();
    }

    @FXML
    public void saveButtonOn() {
        try {
            File file = fc.showSaveDialog(stage);
            FileSudokuBoardDao dao = new FileSudokuBoardDao(file.getName());
            dao.write(sudoku);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public SudokuBoard sudokuLoad() {
        File file = fc.showOpenDialog(stage);
        try {
            FileSudokuBoardDao dao = new FileSudokuBoardDao(file.getName());
            sudoku = dao.read();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return sudoku;
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