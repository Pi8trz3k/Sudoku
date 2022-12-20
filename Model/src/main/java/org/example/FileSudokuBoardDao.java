package org.example;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class FileSudokuBoardDao implements Dao<SudokuBoard>, AutoCloseable {

    private final String path;

    public FileSudokuBoardDao(String fileName) {
        this.path = fileName;
    }

    @Override
    public SudokuBoard read() {
        try (ObjectInputStream objectIn = new ObjectInputStream(new FileInputStream(path))) {
            Object obj = objectIn.readObject();

            return (SudokuBoard) obj;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }

    @Override
    public void write(SudokuBoard obj) {
        try (ObjectOutputStream objectOut = new ObjectOutputStream(new FileOutputStream(path))) {
            objectOut.writeObject(obj);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void close() {

    }
}
