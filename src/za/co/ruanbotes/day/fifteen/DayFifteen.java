package za.co.ruanbotes.day.fifteen;

import za.co.ruanbotes.utils.FileReader;

public class DayFifteen {

    public void run() {
        puzzleOne();
        puzzleTwo();
    }

    private void puzzleOne() {
        System.out.println("*********** Puzzle 1 **********");
        String[] lines = FileReader.readFile("resources/day/fifteen/input.txt");
        Graph graph = new Graph();
        Vertex[][] matrix = graph.getMatrix(lines);
        matrix = graph.addEdges(matrix);
        graph.computePaths(matrix[0][0]);
        System.out.println(matrix[lines.length-1][lines[0].length()-1].minDistance);
        System.out.println("*******************************");
    }

    private void puzzleTwo() {
        System.out.println("*********** Puzzle 2 **********");
        String[] lines = FileReader.readFile("resources/day/fifteen/input.txt");
        Graph graph = new Graph();
        Vertex[][] matrix = graph.getMatrixMultiplied(lines);
        matrix = graph.addEdges(matrix);
        graph.computePaths(matrix[0][0]);
        System.out.println(matrix[matrix.length-1][matrix[0].length-1].minDistance);
        System.out.println("*******************************");
    }


}
