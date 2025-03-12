import java.util.Arrays;

import javax.swing.JTextField;

public class Sudoku {
    private char[][] board = new char[9][9];
    private final char[][] solution = new char[9][9];
    private final String difficulty;


    public Sudoku(String board, String solution, String difficulty) {
        int count = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                this.solution[i][j] = solution.charAt(count);
                if (board.charAt(count) != '.') {
                    this.board[i][j] = board.charAt(count);
                } else {
                    this.board[i][j] = '\0';
                }
                count++;
            }
        }
        this.difficulty = difficulty;
    }

    public char getCell(int row, int col) {
        return board[row][col];
    }

    public boolean checkSolution(JTextField[][] fields) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (!fields[i][j].getText().equals(solution[i][j]+"")) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "Sudoku{" +
                "board=" + Arrays.deepToString(board) +
                ", solution=" + Arrays.deepToString(solution) +
                ", difficulty='" + difficulty + '\'' +
                '}';
    }
}
