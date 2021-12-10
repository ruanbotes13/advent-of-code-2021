package za.co.ruanbotes.day.four;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Board {
//    List<List<BoardNumber>> listOfLists = new ArrayList<List<BoardNumber>>();
    BoardNumber[][] board;

    public Board(int columns, int rows) {
        board = new BoardNumber[rows][columns];
    }

    public void addNumber(int column, int row, int number) {
        BoardNumber boardNumber = new BoardNumber(number);
        board[row][column] = boardNumber;
    }

    public void markNumber(Integer number) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                board[i][j].markNumber(number);
            }
        }
    }

    public boolean hasBingo() {
        return checkRows() || checkColumns();
    }

    public boolean checkRows() {
        for (int i = 0; i < board.length; i++) {
            int numberMarked = 0;
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j].marked == true) {
                    numberMarked++;
                }
            }

            if (numberMarked == board.length) {
                return true;
            }

            numberMarked = 0;
        }

        return false;
    }

    public boolean checkColumns() {
        for (int i = 0; i < board.length; i++) {
            int numberMarked = 0;
            for (int j = 0; j < board[0].length; j++) {
                if (board[j][i].marked == true) {
                    numberMarked++;
                }
            }

            if (numberMarked == board.length) {
                return true;
            }

            numberMarked = 0;
        }

        return false;
    }

    public Integer sumUnmarked() {
        Integer sum = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j].marked == false) {
                    sum = sum + board[i][j].number;
                }
            }
        }

        return sum;
    }

}
