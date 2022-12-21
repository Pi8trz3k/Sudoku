package org.example;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

public class FileSudokuBoardDaoTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(SudokuBoardTest.class);
    @Test
    void daoTest() {
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        board.solveGame();
        SudokuBoard boardFromDao = null;
        board.set(0,0,5);
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();

        try (Dao<SudokuBoard> dao = factory.getFileDao("daoTest")) {
            dao.write(board);
            boardFromDao = dao.read();   
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }

        assertEquals(board, boardFromDao);
    }
}
