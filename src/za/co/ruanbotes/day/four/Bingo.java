package za.co.ruanbotes.day.four;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bingo {
    public List<Integer> numbers = new ArrayList<>();
    public List<Board> boards = new ArrayList<>();
    public List<Integer> winnings = new ArrayList<>();

    public void setNumbers(String[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            this.numbers.add(Integer.parseInt(numbers[i]));
        }
    }

    public void setBoard(List<String> boardRows) {
        Board board = new Board(5,5);
        for (int i = 0; i < 5; i++) {
            String row = boardRows.get(i).trim();
            String[] parts = row.split("\\W+");
            for (int j = 0; j < 5; j++) {
                Integer number = Integer.parseInt(parts[j]);
                board.addNumber(j, i, number);
            }
//            int column = 0;
//            for (int j = 0; j < 14; j = j + 3) {
//                String substring = row.substring(j, j + 2).trim();
//                Integer number = Integer.parseInt(substring);
//                if (number == null) {
//                    System.out.println("");
//                }
//                board.addNumber(column, i, number);
//                column++;
//            }
        }

        this.boards.add(board);
    }

    public void play() {
        for (int i = 0; i < numbers.size(); i++) {
            Integer number = numbers.get(i);

            for (int j = 0; j < boards.size(); j++) {
                boards.get(j).markNumber(number);
                if (boards.get(j).hasBingo()) {
                    System.out.println("Board " + (j+1) + " has bingo!");
                    System.out.println(boards.get(j).sumUnmarked() * number);
                    return;
                }
            }
        }
    }

    public void playLast() {
        int winningBoard = 0;
        int winningNumber = 0;

        for (int i = 0; i < numbers.size(); i++) {
            Integer number = numbers.get(i);

            for (int j = 0; j < boards.size(); j++) {
                boards.get(j).markNumber(number);
                if (boards.get(j).hasBingo()) {
                    winningBoard = j;
                    winningNumber = number;

                    if (isLast(winningBoard)) {
                        System.out.println("Board " + (winningBoard+1) + " has bingo!");
                        System.out.println(boards.get(winningBoard).sumUnmarked() + " " + winningNumber);
                        System.out.println(boards.get(winningBoard).sumUnmarked() * winningNumber);
                        return;
                    }
                }
            }
        }


    }

    public boolean isLast(int boardNumber) {
        if (!winnings.contains(boardNumber)) {
            winnings.add(boardNumber);
        }

        List<Integer> notWinned = new ArrayList<>();

        for (int i = 0; i < boards.size(); i++) {
            if (!winnings.contains(i)) {
                notWinned.add(i);
            }
        }

        return notWinned.size() == 0;
    }
}
