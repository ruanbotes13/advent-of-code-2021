package za.co.ruanbotes.day.nine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DayNine {
    public void run() throws IOException {
        puzzleOne();
        puzzleTwo();
    }

    private void puzzleOne() throws IOException {
        System.out.println("*********** Puzzle 1 **********");
        String[] lines = readFile("resources/day/nine/input.txt");
        Integer[][] grid = getGrid(lines);
        List<Integer> lowPoints = getLowPoints(grid);
        List<Integer> riskLevels = riskLevel(lowPoints);
        System.out.println(calculateSum(riskLevels));
        System.out.println("*******************************");
    }

    private Integer[][] getGrid(String[] lines) {
        int rows = lines.length;
        int columns = lines[0].length();

        Integer[][] grid = new Integer[rows][columns];

        for (int i = 0; i < rows; i++) {
            String[] row = lines[i].split("(?!^)");

            for (int j = 0; j < columns; j++) {
                grid[i][j] = Integer.parseInt(row[j]);
            }
        }

        return grid;
    }

    private List<Integer> getLowPoints(Integer[][] grid) {
        List<Integer> lowPoints = new ArrayList<>();
        int rows = grid.length;
        int columns = grid[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Integer current = grid[i][j];
                Integer top = i - 1 < 0 ? Integer.MAX_VALUE : grid[i - 1][j];
                Integer left = j - 1 < 0 ? Integer.MAX_VALUE : grid[i][j - 1];
                Integer right = j + 1 >= columns ? Integer.MAX_VALUE : grid[i][j + 1];
                Integer bottom = i + 1 >= rows ? Integer.MAX_VALUE : grid[i + 1][j];

                if (current < top && current < bottom && current < right && current < left) {
                    lowPoints.add(grid[i][j]);
                }
            }
        }

        return lowPoints;
    }

    private List<Integer> riskLevel(List<Integer> lowPoints) {
        List<Integer> riskLevels = new ArrayList<>();

        lowPoints.forEach(lowPoint -> riskLevels.add(1 + lowPoint));

        return riskLevels;
    }

    private long calculateSum(List<Integer> riskLevels) {
        long sum = 0;

        for (Integer riskLevel : riskLevels) {
            sum += riskLevel;
        }

        return sum;
    }

    private void puzzleTwo() throws IOException {
        System.out.println("*********** Puzzle 2 **********");
        String[] lines = readFile("resources/day/nine/input.txt");
        Grid grid = getGridObject(lines);
        grid = getLowPointsObject(grid);
        List<Basin> basins = getBasins(grid);
        Collections.sort(basins);
        System.out.println(basins.get(0).gridBlocks.size() * basins.get(1).gridBlocks.size() * basins.get(2).gridBlocks.size());
        System.out.println("*******************************");
    }

    private Grid getGridObject(String[] lines) {
        int rows = lines.length;
        int columns = lines[0].length();

        GridBlock[][] grid = new GridBlock[rows][columns];

        for (int i = 0; i < rows; i++) {
            String[] row = lines[i].split("(?!^)");

            for (int j = 0; j < columns; j++) {
                grid[i][j] = new GridBlock(i, j, Integer.parseInt(row[j]));
            }
        }

        return new Grid(grid);
    }

    private Grid getLowPointsObject(Grid grid) {
        List<GridBlock> lowPoints = new ArrayList<>();
        int rows = grid.grid.length;
        int columns = grid.grid[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Integer current = grid.grid[i][j].value;
                Integer top = i - 1 < 0 ? Integer.MAX_VALUE : grid.grid[i - 1][j].value;
                Integer left = j - 1 < 0 ? Integer.MAX_VALUE : grid.grid[i][j - 1].value;
                Integer right = j + 1 >= columns ? Integer.MAX_VALUE : grid.grid[i][j + 1].value;
                Integer bottom = i + 1 >= rows ? Integer.MAX_VALUE : grid.grid[i + 1][j].value;

                if (current < top && current < bottom && current < right && current < left) {
                    lowPoints.add(grid.grid[i][j]);
                }
            }
        }

        grid.lowPoints = lowPoints;

        return grid;
    }

    private List<Basin> getBasins(Grid grid) {
        List<Basin> basins = new ArrayList<>();

        for (GridBlock gridBlock : grid.lowPoints) {
            Basin basin = new Basin();
            basin.gridBlocks = getBasin(gridBlock, grid);
            basins.add(basin);
        }

        return basins;
    }

    private List<GridBlock> getBasin(GridBlock lowPoint, Grid grid) {
        List<GridBlock> basin = new ArrayList<>();
        grid.setVisited(lowPoint.row, lowPoint.column);
        lowPoint.visited = true;
        basin.add(lowPoint);

        GridBlock top = lowPoint.row - 1 < 0 ? null : grid.grid[lowPoint.row - 1][lowPoint.column];
        GridBlock left = lowPoint.column - 1 < 0 ? null : grid.grid[lowPoint.row][lowPoint.column - 1];
        GridBlock right = lowPoint.column + 1 >= grid.grid[0].length ? null : grid.grid[lowPoint.row][lowPoint.column + 1];
        GridBlock bottom = lowPoint.row + 1 >= grid.grid.length ? null : grid.grid[lowPoint.row + 1][lowPoint.column];

        if (top != null && !top.visited && top.value != 9) {
            basin.addAll(getBasin(top, grid));
        }

        if (left != null && !left.visited && left.value != 9) {
            basin.addAll(getBasin(left, grid));
        }

        if (right != null && !right.visited && right.value != 9) {
            basin.addAll(getBasin(right, grid));
        }

        if (bottom != null && !bottom.visited && bottom.value != 9) {
            basin.addAll(getBasin(bottom, grid));
        }

        return basin;
    }

    private String[] readFile(String filePath) throws IOException {
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
