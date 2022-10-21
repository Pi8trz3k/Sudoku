package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;


class SudokuBoardTest {

    @Test
    void czyUkladLiczbJestPoprawny() {
        SudokuBoard sudoku = new SudokuBoard();
        int[][] board = {{0, 0, 0, 0, 0, 0, 0, 0, 0}
                , {0, 0, 0, 0, 0, 0, 0, 0, 0}
                , {0, 0, 0, 0, 0, 0, 0, 0, 0}
                , {0, 0, 0, 0, 0, 0, 0, 0, 0}
                , {0, 0, 0, 0, 0, 0, 0, 0, 0}
                , {0, 0, 0, 0, 0, 0, 0, 0, 0}
                , {0, 0, 0, 0, 0, 0, 0, 0, 0}
                , {0, 0, 0, 0, 0, 0, 0, 0, 0}
                , {0, 0, 0, 0, 0, 0, 0, 0, 0}};
        sudoku.setBoard(board);
        sudoku.fillBoard();
        board = sudoku.getBoard();
        int powt = 0;
        //verical test
        for (int a = 1; a < 10; a++) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (board[i][j] == a)
                        powt++;
                }
                Assertions.assertEquals(powt, 1);
                powt = 0;
            }
        }
        //horizontal test
        for (int a = 1; a < 10; a++) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (board[j][i] == a)
                        powt++;

                }
                Assertions.assertEquals(powt, 1);
                powt = 0;
            }
        }
        //square test
        for(int a = 1; a<10; a++){
            for(int z=0; z<9; z++){
                for(int i=z%3*3; i<z%3*3+3; i++){
                    for(int j=z/3*3; j<z/3*3+3; j++){
                        if (board[i][j] == a)
                            powt++;
                    }
                }
                Assertions.assertEquals(powt, 1);
                powt=0;
            }
        }
    }

    @Test
    void czyUkladySiePowtarzaja(){
        SudokuBoard sudoku = new SudokuBoard();
        int[][] board = {{0, 0, 0, 0, 0, 0, 0, 0, 0}
                , {0, 0, 0, 0, 0, 0, 0, 0, 0}
                , {0, 0, 0, 0, 0, 0, 0, 0, 0}
                , {0, 0, 0, 0, 0, 0, 0, 0, 0}
                , {0, 0, 0, 0, 0, 0, 0, 0, 0}
                , {0, 0, 0, 0, 0, 0, 0, 0, 0}
                , {0, 0, 0, 0, 0, 0, 0, 0, 0}
                , {0, 0, 0, 0, 0, 0, 0, 0, 0}
                , {0, 0, 0, 0, 0, 0, 0, 0, 0}};

        int[][] board2 = {{0, 0, 0, 0, 0, 0, 0, 0, 0}
                , {0, 0, 0, 0, 0, 0, 0, 0, 0}
                , {0, 0, 0, 0, 0, 0, 0, 0, 0}
                , {0, 0, 0, 0, 0, 0, 0, 0, 0}
                , {0, 0, 0, 0, 0, 0, 0, 0, 0}
                , {0, 0, 0, 0, 0, 0, 0, 0, 0}
                , {0, 0, 0, 0, 0, 0, 0, 0, 0}
                , {0, 0, 0, 0, 0, 0, 0, 0, 0}
                , {0, 0, 0, 0, 0, 0, 0, 0, 0}};
        sudoku.setBoard(board);
        sudoku.fillBoard();
        board = sudoku.getBoard();
        sudoku.setBoard(board2);
        sudoku.fillBoard();
        board2 = sudoku.getBoard();
        Assertions.assertNotSame(board, board2);
    }
}