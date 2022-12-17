package org.example;

import java.util.Random;

public enum DiffcultEnum {
    easy(10),
    medium(20),
    hard(30);


    private final int fieldsToErase;

    DiffcultEnum(int numOfFieldsToErase) {
        fieldsToErase = numOfFieldsToErase;
    }

    public int getFieldsToErase() {
        return fieldsToErase;
    }

    public void deleteFields(SudokuBoard board) {
        Random rand = new Random();
        int x = 0;
        int y = 0;
        for (int i = 0; i < fieldsToErase; i++) {
            do {
                x = rand.nextInt(9);
                y = rand.nextInt(9);
            } while (board.get(x, y) == 0);
            board.set(x, y, 0);
        }
    }
}
