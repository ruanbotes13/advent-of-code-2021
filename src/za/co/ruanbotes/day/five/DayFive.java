package za.co.ruanbotes.day.five;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DayFive {
    public void run() throws IOException {
        puzzleOne();
        puzzleTwo();
    }

    private void puzzleOne() throws IOException {
        System.out.println("*********** Puzzle 1 **********");
        String[] lines = readFile("resources/day/five/input.txt");
        List<Line> lineList = readLines(lines);
        long overlaps = calculateOverlaps(lineList);
        System.out.println(overlaps);
        System.out.println("*******************************");
    }

    private List<Line> readLines(String[] lines) {
        List<Line> lineList = new ArrayList<>();

        for (int i = 0; i < lines.length; i++) {
            String[] points = lines[i].split(" -> ");

            int x1 = Integer.parseInt(points[0].split(",")[0]);
            int y1 = Integer.parseInt(points[0].split(",")[1]);
            int x2 = Integer.parseInt(points[1].split(",")[0]);
            int y2 = Integer.parseInt(points[1].split(",")[1]);

            Line line = new Line();

            if (x1 == x2) {
                for (int j = Math.min(y1, y2); j <= Math.max(y1, y2); j++) {
                    line.points.add(
                        new Point(x1, j)
                    );
                }
                lineList.add(line);
            } else if (y1 == y2) {
                for (int j = Math.min(x1, x2); j <= Math.max(x1, x2); j++) {
                    line.points.add(
                        new Point(j, y1)
                    );
                }
                lineList.add(line);
            }


        }

        return lineList;
    }

    private long  calculateOverlaps(List<Line> lines) {
        int multipleOverlaps = 0;

        List<Point> points = new ArrayList<>();

        List<Point> uniquePoints = new ArrayList<>();

        for (int i = 0; i < lines.size(); i++) {
            points.addAll(lines.get(i).points);
        }

        for (int i = 0; i < points.size(); i++) {
            Point point = points.get(i);
            Optional<Point> foundPoint = uniquePoints.stream().filter(point1 -> point1.x == point.x && point1.y == point.y).findFirst();
            if (foundPoint.isPresent()) {
                uniquePoints = uniquePoints.stream().map(point1 -> {
                    if (point1.x == point.x && point1.y == point.y) {
                        point1.overlap++;
                    }
                    return point1;
                }).collect(Collectors.toList());
            } else {
                uniquePoints.add(point);
            }
        }

        return uniquePoints.stream().filter(point -> point.overlap >= 2).count();
    }

    private void puzzleTwo() throws IOException {
        System.out.println("*********** Puzzle 2 **********");
        String[] lines = readFile("resources/day/five/input.txt");
        List<Line> lineList = readLines2(lines);
        long overlaps = calculateOverlaps(lineList);
        System.out.println(overlaps);
        System.out.println("*******************************");
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

    private List<Line> readLines2(String[] lines) {
        List<Line> lineList = new ArrayList<>();

        for (int i = 0; i < lines.length; i++) {
            String[] points = lines[i].split(" -> ");

            int x1 = Integer.parseInt(points[0].split(",")[0]);
            int y1 = Integer.parseInt(points[0].split(",")[1]);
            int x2 = Integer.parseInt(points[1].split(",")[0]);
            int y2 = Integer.parseInt(points[1].split(",")[1]);

            int xdiff = x1-x2;
            int ydiff = y1-y2;

            Line line = new Line();

            // move - on x
            if (xdiff > 0) {
                if (ydiff > 0) { // move - on y
                    int y = y1;
                    for (int j = x1; j >= x2; j--) {
                        line.points.add(new Point(j, y));
                        y = y - 1;
                    }
                }
                else if (ydiff < 0) { //move + on y
                    int y = y1;
                    for (int j = x1; j >= x2; j--) {
                        line.points.add(new Point(j, y));
                        y = y + 1;
                    }
                } else {
                    for (int j = x1; j >= x2; j--) {
                        line.points.add(new Point(j, y1));
                    }
                }
            }
            else if (xdiff < 0) { // move + on x
                if (ydiff > 0) { // move - on y
                    int y = y1;
                    for (int j = x1; j <= x2; j++) {
                        line.points.add(new Point(j, y));
                        y = y - 1;
                    }
                }
                else if (ydiff < 0) { //move + on y
                    int y = y1;
                    for (int j = x1; j <= x2; j++) {
                        line.points.add(new Point(j, y));
                        y = y + 1;
                    }
                }
                else {
                    for (int j = x1; j <= x2; j++) {
                        line.points.add(new Point(j, y1));
                    }
                }
            }
            else {
                if(ydiff > 0) {
                    for (int j = y1; j >= y2; j--) {
                        line.points.add(new Point(x1, j));
                    }
                } else {
                    for (int j = y1; j <= y2; j++) {
                        line.points.add(new Point(x1, j));
                    }
                }
            }

            lineList.add(line);
        }

        return lineList;
    }
}
