package za.co.ruanbotes.day.eleven;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class DayEleven {
    public void run() {
//        puzzleOne();
        puzzleTwo();
    }

    private static void puzzleOne() {
        System.out.println("*********** Puzzle 1 **********");
        String[] lines = readFile("resources/day/eleven/input.txt");
        Octopus[][] octopi = getOctopusGrid(lines);
        OctopiGrid octopiGrid = new OctopiGrid(octopi);
        octopiGrid.simulateFlashes(100);
        System.out.println(octopiGrid.flashes);
        System.out.println("*******************************");
    }

    private static Octopus[][] getOctopusGrid(String[] lines) {
        Octopus[][] octopi = new Octopus[10][10];

        for (int i = 0; i < lines.length; i++) {
            String[] parts = lines[i].split("(?!^)");

            for (int j = 0; j < parts.length; j++) {
                octopi[i][j] = new Octopus(Integer.parseInt(parts[j]));
            }
        }

        return octopi;
    }

    private static void puzzleTwo() {
        System.out.println("*********** Puzzle 2 **********");
        String[] lines = readFile("resources/day/eleven/input.txt");
        Octopus[][] octopi = getOctopusGrid(lines);
        OctopiGrid octopiGrid = new OctopiGrid(octopi);
        octopiGrid.findSimultanious();
        System.out.println(octopiGrid.stepAllFlashed);
        System.out.println("*******************************");
    }

    private static String[] readFile(String filePath) {
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
