package za.co.ruanbotes.day.one;

import za.co.ruanbotes.utils.Converter;
import za.co.ruanbotes.utils.FileReader;
import za.co.ruanbotes.utils.Printer;

import java.util.ArrayList;
import java.util.List;

public class DayOne {

    public void run() {
        partOne();
        partTwo();
    }

    private void partOne() {
        String[] lines = FileReader.readFile("resources/day/one/input.txt");
        Integer[] depths = Converter.convertToIntArray(lines);
        int count = countIncreases(depths);
        Printer.print(1, count);
    }

    private int countIncreases(Integer[] depths) {
        int count = 0;
        for (int i = 1; i < depths.length; i++) {
            if (depths[i] > depths[i-1]) {
                count++;
            }
        }

        return count;
    }

    private void partTwo() {
        String[] lines = FileReader.readFile("resources/day/one/input.txt");
        Integer[] depths = Converter.convertToIntArray(lines);
        int windowCount = countMovingSumIncreases(depths);
        Printer.print(2, windowCount);
    }

    private int countMovingSumIncreases(Integer[] depths) {
        List<Integer> windows = new ArrayList<Integer>();
        int sum = 0;

        for (int i = 0; i <= depths.length; i++) {
            if (inRange(i, depths.length)) {
                sum = depths[i] + depths[i-1] + depths[i-2];
                windows.add(sum);
            }
        }

        Integer[] windowArray = windows.toArray(new Integer[]{});

        return countIncreases(windowArray);
    }

    private boolean inRange(int number, int max) {
        if (number >= 2 && number < max && number-1 < max && number-2 < max) {
            return true;
        }

        return false;
    }
}
