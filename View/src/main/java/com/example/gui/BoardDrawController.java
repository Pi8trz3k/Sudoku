package com.example.gui;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import org.example.SudokuBoard;

public class BoardDrawController {

    @FXML
    private GridPane gridPane;

    public void draw(SudokuBoard sudoku) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                String text = Integer.toString(sudoku.get(i, j));
                gridPane.add(new Text(text), i, j);
            }
        }
    }
}