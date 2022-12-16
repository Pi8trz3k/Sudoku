package com.example.gui;

import org.example.SudokuBoard;

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
        //tutaj to trzeba jakieś gówno, nie mam głowy, do pizdy z tym
    }
}
