package org.example;

import org.example.exceptions.DaoException;

public class SudokuBoardDaoFactory {

    public static Dao<SudokuBoard> getFileDao(String fileName) throws DaoException {
        try {
            return new FileSudokuBoardDao(fileName);
        } catch (Exception ex) {
            throw new DaoException(ex);
        }
    }
}
