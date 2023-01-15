package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JdbcSudokuBoardDaoTest {

    @Test
    public void test1() {
        String name = "nazwaTestowa";
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard sb = new SudokuBoard(solver);
        sb.solveGame();
        SudokuBoard newB;

        try (JdbcSudokuBoardDao jdbcTest = new JdbcSudokuBoardDao(name)) {
            jdbcTest.write(sb);

            newB = jdbcTest.read();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        assertEquals(sb, newB);
        assertNotSame(sb, newB);
    }
}
