package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcSudokuBoardDao implements Dao<SudokuBoard>, AutoCloseable {

    private final String host = "jdbc:mysql://localhost:3306/sudoku";
    private final String userName = "root";
    private final String userPassword = "";
    private String sbName;

    private Connection con = null;
    private Statement statement = null;

    public JdbcSudokuBoardDao(String sbName) {
        this.sbName = sbName;
        try {
            con = DriverManager.getConnection(host, userName, userPassword);
            statement = con.createStatement();
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
    }

    @Override
    public SudokuBoard read() {
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        String querySelectData =
                "SELECT col, row, field_value, editable FROM pole WHERE id_board "
                        + "= (SELECT id FROM plansza WHERE board_name=? ORDER BY id DESC LIMIT 1)";
        ResultSet resultSet;

        try (PreparedStatement preparedStatement = con.prepareStatement(querySelectData)) {
            preparedStatement.setString(1, sbName);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                board.set(
                            resultSet.getInt("row"),
                            resultSet.getInt("col"),
                            resultSet.getInt("field_value")
                );

                if (resultSet.getBoolean("editable")) {
                    board.setEditable(
                            resultSet.getInt("col"),
                            resultSet.getInt("row")
                    );
                }
            }
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }

        return board;
    }

    @Override
    public void write(SudokuBoard obj) {

        String queryInsertBoard = "INSERT INTO plansza(board_name) VALUE(?)";
        String queryInsertField =
                "INSERT INTO pole(id_board, col, row, field_value, editable) "
                + "VALUES ((SELECT id FROM plansza WHERE board_name=? "
                        + "ORDER BY id DESC LIMIT 1),?,?,?,?)";

        try (PreparedStatement preparedStatement = con.prepareStatement(queryInsertBoard)) {
            preparedStatement.setString(1, sbName);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }

        try (PreparedStatement preparedStatement = con.prepareStatement(queryInsertField)) {
            for (int row = 0; row < 9; row++) {
                for (int column = 0; column < 9; column++) {
                    preparedStatement.setString(1, sbName);
                    preparedStatement.setInt(2, column);
                    preparedStatement.setInt(3, row);
                    preparedStatement.setInt(4, obj.get(row, column));
                    preparedStatement.setBoolean(5, obj.isEditable(row, column));
                    preparedStatement.executeUpdate();
                }
            }
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
    }

    @Override
    public void close() {
        try {
            con.close();
            statement.close();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}
