package com.example.gui;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.RadioButton;
import org.example.BacktrackingSudokuSolver;
import org.example.DiffcultEnum;
import org.example.SudokuBoard;
import org.example.SudokuSolver;


public class ChooseDifficultyController {

    @FXML
    private RadioButton easy;
    @FXML
    private RadioButton medium;
    @FXML
    private RadioButton hard;

    private int difficulty = 0;

    @FXML
    private void play() throws IOException {

        DiffcultEnum difficultyLevel = null;

        switch (difficulty) {
            case (0) -> difficultyLevel = DiffcultEnum.easy;
            case (1) -> difficultyLevel = DiffcultEnum.medium;
            case (2) -> difficultyLevel = DiffcultEnum.hard;
            default -> {
            }
        }

        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard sudoku = new SudokuBoard(solver);
        sudoku.solveGame();

        difficultyLevel.deleteFields(sudoku);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Board.fxml"));
        HelloApplication.setRoot(loader);
        BoardDrawController bdc = loader.getController();
        bdc.draw(sudoku);
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
}