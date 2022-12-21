package org.example;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.example.exceptions.FileDaoException;


public class FileSudokuBoardDao implements Dao<SudokuBoard>, AutoCloseable {

    private final String path;

    public FileSudokuBoardDao(String fileName) {
        this.path = fileName;
    }

    @Override
    public SudokuBoard read() throws FileDaoException {
        try (ObjectInputStream objectIn = new ObjectInputStream(new FileInputStream(path))) {
            Object obj = objectIn.readObject();

            return (SudokuBoard) obj;
        } catch (IOException | ClassNotFoundException ex) {
            throw new FileDaoException(ex);
        }

    }

    @Override
    public void write(SudokuBoard obj) throws FileDaoException {
        try (ObjectOutputStream objectOut = new ObjectOutputStream(new FileOutputStream(path))) {
            objectOut.writeObject(obj);

        } catch (IOException ex) {
            throw new FileDaoException(ex);
        }
    }

    @Override
    public void close() {

    }
}
