package org.example;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.junit.jupiter.api.Assertions.*;

public class SudokuBoardDaoFactoryTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(SudokuBoardTest.class);
    @Test
    void factoryTest() {
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        SudokuBoard boardDao = null;
        board.solveGame();
        board.set(0,0,9);
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        try (Dao<SudokuBoard> dao = factory.getFileDao("factoryTest")) {
            dao.write(board);
            boardDao = dao.read();

        } catch(Exception ex) {
            LOGGER.error(ex.getMessage());
        }

        assertEquals(board, boardDao);
    }
}
