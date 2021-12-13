package za.co.ruanbotes.day.thirteen;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class DayThirteen {
    public void run() {
        puzzleOne();
        puzzleTwo();
    }

    private void puzzleOne() {
        System.out.println("*********** Puzzle 1 **********");
        String[] lines = readFile("resources/day/thirteen/input.txt");
        List<Coordinate> matrix = getDotCoordinates(lines);
        List<Instruction> instructions = getInstructions(lines);
        String[][] board = getBoard(matrix);
        board = applyInstructions(board, instructions, instructions.size());
        System.out.println(countDots(board));

        System.out.println("*******************************");
    }

    private List<Coordinate> getDotCoordinates(String[] lines) {
        List<Coordinate> coordinates = new ArrayList<>();
        int lineCounter = 0;
        String line = lines[lineCounter];
        while(line.length() != 0) {
            coordinates.add(new Coordinate(Integer.parseInt(line.split(",")[0]), Integer.parseInt(line.split(",")[1])));
            lineCounter++;
            line = lines[lineCounter];
        }

        return coordinates;
    }

    private List<Instruction> getInstructions(String[] lines) {
        List<Instruction> instructions = new ArrayList<>();

        int lineCounter = 0;
        String line = lines[lineCounter];
        while(line.length() != 0) {
            lineCounter++;
            line = lines[lineCounter];
        }

        lineCounter++;

        while (lineCounter < lines.length) {
            String[] parts = lines[lineCounter].split(" ");
            String[] instructionParts = parts[2].split("=");
            instructions.add(
                new Instruction(instructionParts[0], Integer.parseInt(instructionParts[1]))
            );
            lineCounter++;
        }

        return instructions;
    }

    private String[][] getBoard(List<Coordinate> coordinates) {
        int maxX = findMaxX(coordinates);
        int maxY = findMaxY(coordinates);

        String[][] board = new String[maxX + 1][maxY + 1];

        for (String[] row: board) {
            Arrays.fill(row, ".");
        }

        for (Coordinate coordinate : coordinates) {
            board[coordinate.x][coordinate.y] = "#";
        }

        return board;
    }

    private int findMaxX(List<Coordinate> coordinates) {
        int max = 0;

        for (int i = 0; i < coordinates.size(); i++) {
            if (coordinates.get(i).x > max) {
                max = coordinates.get(i).x;
            }
        }

        return max;
    }

    private int findMaxY(List<Coordinate> coordinates) {
        int max = 0;

        for (int i = 0; i < coordinates.size(); i++) {
            if (coordinates.get(i).y > max) {
                max = coordinates.get(i).y;
            }
        }

        return max;
    }

    private String[][] applyInstructions(String[][] board, List<Instruction> instructions, int numberOfInstructions) {
        String[][] alteredBoard = board;
        for (int i = 0; i < numberOfInstructions; i++) {
            alteredBoard = applyInstruction(alteredBoard, instructions.get(i));
        }

        return alteredBoard;
    }

    private String[][] applyInstruction(String[][] board, Instruction instruction) {
        String[][] alteredBoard;
        if (instruction.axis.equals("x")) {
            alteredBoard = new String[instruction.coordinate][board[0].length];

            for (int x = 0; x < instruction.coordinate; x++) {
                for (int y = 0; y < board[x].length; y++) {
                    alteredBoard[x][y] = board[x][y];
                }
            }

            int xCounter = instruction.coordinate - 1;

            for (int x = instruction.coordinate + 1; x < board.length; x++) {
                for (int y = 0; y < board[x].length; y++) {
                    if (alteredBoard[xCounter][y].equals(".")) {
                        if (board[x][y].equals("#")) {
                            alteredBoard[xCounter][y] = "#";
                        }
                    }
                }
                xCounter--;
            }
        } else {
            alteredBoard = new String[board.length][instruction.coordinate];

            for (int x = 0; x < board.length; x++) {
                for (int y = 0; y < instruction.coordinate; y++) {
                    alteredBoard[x][y] = board[x][y];
                }
            }

            for (int x = 0; x < board.length; x++) {
                int yCounter = instruction.coordinate - 1;
                for (int y = instruction.coordinate + 1; y < board[x].length; y++) {
                    if (alteredBoard[x][yCounter].equals(".")) {
                        if (board[x][y].equals("#")) {
                            alteredBoard[x][yCounter] = "#";
                        }
                    }
                    yCounter--;
                }
            }
        }

        return alteredBoard;
    }

    private int countDots(String[][] board) {
        int dots = 0;

        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[x].length; y++) {
                if (board[x][y].equals("#")) {
                    dots++;
                }
            }
        }

        return dots;
    }

    private void puzzleTwo() {
        System.out.println("*********** Puzzle 2 **********");
        String[] lines = readFile("resources/day/thirteen/input.txt");

        List<Coordinate> matrix = getDotCoordinates(lines);
        List<Instruction> instructions = getInstructions(lines);
        String[][] board = getBoard(matrix);
        board = applyInstructions(board, instructions, instructions.size());
        System.out.println(countDots(board));

        for (int y = 0; y < board[0].length; y++) {
            for (int x = 0; x < board.length; x++) {
                System.out.print(board[x][y]);
            }
            System.out.print("\n");
        }
        System.out.println("*******************************");
    }

    private String[] readFile(String filePath) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader abc = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = abc.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.println("File not read");
        }

        return lines.toArray(new String[]{});
    }
}
