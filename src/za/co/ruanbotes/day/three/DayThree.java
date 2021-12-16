package za.co.ruanbotes.day.three;

import za.co.ruanbotes.utils.FileReader;
import za.co.ruanbotes.utils.Printer;

import java.util.ArrayList;
import java.util.List;

public class DayThree {
    public void run() {
        puzzleOne();
        puzzleTwo();
    }

    private void puzzleOne() {
        String[] lines = FileReader.readFile("resources/day/three/input.txt");
        int ones[] = calculateOnes(lines);
        int zeros[] = calculateZeros(lines);

        Printer.print(1, calculatePowerConsumption(ones, zeros));
    }

    private int[] calculateOnes(String[] lines) {
        int ones[] = new int[lines[0].length()];

        for (int i = 0; i < lines.length; i++) {
            for (int j = 0; j < lines[i].length(); j++) {
                if (lines[i].charAt(j) == '1') {
                    ones[j] += 1;
                }
            }
        }

        return ones;
    }

    private int[] calculateZeros(String[] lines) {
        int zeros[] = new int[lines[0].length()];

        for (int i = 0; i < lines.length; i++) {
            for (int j = 0; j < lines[i].length(); j++) {
                if (lines[i].charAt(j) == '0') {
                    zeros[j] += 1;
                }
            }
        }

        return zeros;
    }

    private int calculatePowerConsumption(int[] ones, int[] zeros) {
        String gamma = "";
        String epsilon = "";

        for (int i = 0; i < ones.length; i++) {
            if (ones[i] > zeros[i]) {
                gamma += "1";
                epsilon += "0";
            }
            else {
                epsilon += "1";
                gamma += "0";
            }
        }

        return Integer.parseInt(gamma, 2) * Integer.parseInt(epsilon, 2);
    }

    private void puzzleTwo() {
        String[] lines = FileReader.readFile("resources/day/three/input.txt");
        String oxygen = oxygenGenerator(lines, 0)[0];
        String carbonDioxide = carbonDioxide(lines, 0)[0];
        Printer.print(2, Integer.parseInt(oxygen, 2) * Integer.parseInt(carbonDioxide, 2));
    }

    private String[] oxygenGenerator(String[] binaries, int position) {
        if (binaries.length == 1) {
            return binaries;
        }
        else {
            int ones[] = calculateOnes(binaries);
            int zeros[] = calculateZeros(binaries);
            List<String> oxygenRatings = getOxygenRatings(binaries, ones, zeros, position);

            return oxygenGenerator(
                oxygenRatings.toArray(new String[]{}),
                position + 1
            );
        }
    }

    private List<String> getOxygenRatings(String[] binaries, int[] ones, int[] zeros, int position) {
        List<String> list = new ArrayList<>();

        for (int i = 0; i < binaries.length; i++) {
            if (ones[position] > zeros[position]) {
                if (binaries[i].charAt(position) == '1') {
                    list.add(binaries[i]);
                }
            }
            else if (ones[position] < zeros[position]) {
                if (binaries[i].charAt(position) == '0') {
                    list.add(binaries[i]);
                }
            } else {
                if (binaries[i].charAt(position) == '1') {
                    list.add(binaries[i]);
                }
            }
        }

        return list;
    }

    private String[] carbonDioxide(String[] binaries, int position) {
        if (binaries.length == 1) {
            return binaries;
        }
        else {
            int ones[] = calculateOnes(binaries);
            int zeros[] = calculateZeros(binaries);
            List<String> carbonDioxideRatings = getCarbonDioxideRatings(binaries, ones, zeros, position);

            return carbonDioxide(
                carbonDioxideRatings.toArray(new String[]{}),
                position + 1
            );
        }
    }

    private List<String> getCarbonDioxideRatings(String[] binaries, int[] ones, int[] zeros, int position) {
        List<String> list = new ArrayList<>();

        for (int i = 0; i < binaries.length; i++) {
            if (ones[position] > zeros[position]) {
                if (binaries[i].charAt(position) == '0') {
                    list.add(binaries[i]);
                }
            }
            else if (ones[position] < zeros[position]) {
                if (binaries[i].charAt(position) == '1') {
                    list.add(binaries[i]);
                }
            } else {
                if (binaries[i].charAt(position) == '0') {
                    list.add(binaries[i]);
                }
            }
        }

        return list;
    }
}
