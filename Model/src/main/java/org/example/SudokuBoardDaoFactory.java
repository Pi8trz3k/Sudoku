package org.example;

import org.example.exceptions.DaoException;

public class SudokuBoardDaoFactory {

    public Dao<SudokuBoard> getFileDao(String fileName) throws DaoException {
        try {
            return new FileSudokuBoardDao(fileName);
        } catch (Exception ex) {
            throw new DaoException(ex);
        }
    }

    public Dao<SudokuBoard> getDataBaseDao(String sudokuBoardName) {
        return new JdbcSudokuBoardDao(sudokuBoardName);
    }
}
