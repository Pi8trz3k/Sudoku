package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FileSudokuBoardDaoTest {

    @Test
    void daoTest() {
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        board.solveGame();
        SudokuBoard boardFromDao = null;
        board.set(0,0,5);

        try (FileSudokuBoardDao dao = new FileSudokuBoardDao("daoTest")) {
            dao.write(board);
            boardFromDao = dao.read();   
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        assertEquals(board, boardFromDao);
    }
}
