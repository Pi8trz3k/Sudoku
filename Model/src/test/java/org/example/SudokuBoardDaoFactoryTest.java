package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SudokuBoardDaoFactoryTest {

    @Test
    void factoryTest() {
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        SudokuBoard boardDao = null;
        board.solveGame();
        board.set(0,0,9);
        try (Dao<SudokuBoard> dao = SudokuBoardDaoFactory.getFileDao("factoryTest")) {
            dao.write(board);
            boardDao = dao.read();

        } catch(Exception ex) {
            ex.printStackTrace();
        }

        assertEquals(board, boardDao);
    }
}
