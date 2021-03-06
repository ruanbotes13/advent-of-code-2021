package za.co.ruanbotes.day.four;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DayFour {
    public void run() throws IOException {
        puzzleOne();
        puzzleTwo();
    }

    private void puzzleOne() throws IOException {
        System.out.println("*********** Puzzle 1 **********");
        Bingo bingo = readFile("resources/day/four/input.txt");
        bingo.play();
        System.out.println("test");
        System.out.println("*******************************");
    }

    private void puzzleTwo() throws IOException {
        System.out.println("*********** Puzzle 2 **********");
        Bingo bingo = readFile("resources/day/four/input.txt");
        bingo.playLast();
        System.out.println("*******************************");
    }

    private Bingo readFile(String filePath) throws IOException {
        BufferedReader abc = new BufferedReader(new FileReader(filePath));
        List<String> lines = new ArrayList<String>();
        String line;
        Bingo bingo = new Bingo();

        line = abc.readLine();

        bingo.setNumbers(line.split(","));
        abc.readLine();
        List<String> boardRows = new ArrayList<>();

        while((line = abc.readLine()) != null) {
            if(line.length() != 0) {
                boardRows.add(line);
            } else if (line != null) {
                bingo.setBoard(boardRows);
                boardRows = new ArrayList<>();
            }
        }
        abc.close();

        bingo.setBoard(boardRows);
        boardRows = new ArrayList<>();

        return bingo;
    }
}
