package org.example;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class FileSudokuBoardDao implements Dao<SudokuBoard>, AutoCloseable {

    private final String fileName;
    private final FileInputStream fileIn;
    private final FileOutputStream fileOut;

    public FileSudokuBoardDao(String fileName) throws FileNotFoundException {
        this.fileName = fileName;
        fileIn = new FileInputStream(fileName);
        fileOut = new FileOutputStream(fileName);
    }

    @Override
    public SudokuBoard read() {
        try (fileIn) {
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            Object obj = objectIn.readObject();

            return (SudokuBoard) obj;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }

    @Override
    public void write(SudokuBoard obj) {
        try (fileOut) {
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(obj);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void close() throws IOException {
        fileIn.close();
        fileOut.close();
    }
}
