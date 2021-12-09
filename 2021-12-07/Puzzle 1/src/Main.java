import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
//        puzzleOne();
        puzzleTwo();
    }

    private static void puzzleOne() throws IOException {
        System.out.println("*********** Puzzle 1 **********");
        String[] crabPositions = readFile("input.txt");
        Integer[] crabPositionList = getCrabPositions(crabPositions[0]);
        int optimalFuel = getOptimalPosition(crabPositionList);
        System.out.println(optimalFuel);
        System.out.println("*******************************");
    }

    private static Integer[] getCrabPositions(String crabPositions) {
        String[] parts = crabPositions.split(",");

        List<Integer> crabPositionList = new ArrayList<>();

        for (int i = 0; i < parts.length; i++) {
            crabPositionList.add(Integer.parseInt(parts[i]));
        }

        return crabPositionList.toArray(new Integer[]{});
    }

    private static int getOptimalPosition(Integer[] crabPositions) {
        int optimalPosition = 0;
        int optimalFuel = -1;

        for (int i = 0; i < crabPositions.length; i++) {
            int fuelSum = 0;

            for (int j = 0; j < crabPositions.length; j++) {
                fuelSum = fuelSum + Math.abs(crabPositions[i] - crabPositions[j]);
            }

            if (optimalFuel == -1 || fuelSum < optimalFuel) {
                optimalFuel = fuelSum;
                optimalPosition = i;
            }
        }

        return optimalFuel;
    }

    private static void puzzleTwo() throws IOException {
        System.out.println("*********** Puzzle 2 **********");
        String[] crabPositions = readFile("input.txt");
        Integer[] crabPositionList = getCrabPositions(crabPositions[0]);
        int optimalFuel = getNewOptimalPosition(crabPositionList);
        System.out.println(optimalFuel);
        System.out.println("*******************************");
    }

    private static int getNewOptimalPosition(Integer[] crabPositions) {
        int max = 0;

        for (int i = 0; i < crabPositions.length; i++) {
            if (max < crabPositions[i]) {
                max = crabPositions[i];
            }
        }

        int optimalFuel = -1;
        int optimalPosition = 0;

        for (int i = 0; i <= max; i++) {
            int fuel = 0;
            for (int j = 0; j < crabPositions.length; j++) {
                int diff = Math.abs(i - crabPositions[j]);
                for (int k = 1; k <= diff; k++) {
                    fuel = fuel + k;
                }
            }

            if (optimalFuel == -1 || fuel < optimalFuel) {
                optimalFuel = fuel;
            }
        }

        return optimalFuel;
    }

    private static String[] readFile(String filePath) throws IOException {
        BufferedReader abc = new BufferedReader(new FileReader(filePath));
        List<String> lines = new ArrayList<String>();
        String line;

        while((line = abc.readLine()) != null) {
            lines.add(line);
        }
        abc.close();

        return lines.toArray(new String[]{});
    }
}
