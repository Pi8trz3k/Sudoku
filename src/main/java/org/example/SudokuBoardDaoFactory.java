package org.example;

public class SudokuBoardDaoFactory implements AutoCloseable {

    public Dao<SudokuBoard> getFileDao(String fileName) {
        return new FileSudokuBoardDao(fileName);
    }

    @Override
    public void close() {

    }
}
