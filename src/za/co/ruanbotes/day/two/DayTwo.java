package za.co.ruanbotes.day.two;


import za.co.ruanbotes.utils.FileReader;
import za.co.ruanbotes.utils.Printer;

import java.util.ArrayList;
import java.util.List;


public class DayTwo {

    public void run() {
        partOne();
        partTwo();
    }

    private void partOne() {
        String[] lines = FileReader.readFile("resources/day/two/input.txt");
        Movement[] movements = convertToMovements(lines);
        Printer.print(1, calculate(movements));
    }

    private int calculate(Movement[] movements) {
        int horizontal = 0;
        int vertical = 0;

        for(int i = 0; i < movements.length; i++) {
            if (movements[i].direction.equals(Direction.FORWARD)) {
                horizontal = horizontal + movements[i].distance;
            }
            else if (movements[i].direction.equals(Direction.UP)) {
                vertical = vertical - movements[i].distance;
            } else if (movements[i].direction.equals(Direction.DOWN)) {
                vertical = vertical + movements[i].distance;
            }
        }

        return horizontal * vertical;
    }

    private Movement[] convertToMovements(String[] lines) {
        List<Movement> movementList = new ArrayList<>();

        for (int i = 0; i < lines.length; i++) {
            String[] parts = lines[i].split(" ");
            movementList.add(
                new Movement(Integer.parseInt(parts[1]), parts[0])
            );
        }

        return movementList.toArray(new Movement[]{});
    }

    private void partTwo() {
        String[] lines = FileReader.readFile("resources/day/two/input.txt");
        Movement[] movements = convertToMovements(lines);
        Printer.print(2, calculateTwo(movements));
    }

    private int calculateTwo(Movement[] movements) {
        int horizontal = 0;
        int vertical = 0;
        int aim = 0;

        for(int i = 0; i < movements.length; i++) {
            if (movements[i].direction.equals(Direction.FORWARD)) {
                horizontal = horizontal + movements[i].distance;
                vertical = vertical + (aim * movements[i].distance);
            }
            else if (movements[i].direction.equals(Direction.UP)) {
                aim = aim - movements[i].distance;
            } else if (movements[i].direction.equals(Direction.DOWN)) {
                aim = aim + movements[i].distance;
            }
        }

        return horizontal * vertical;
    }
}
