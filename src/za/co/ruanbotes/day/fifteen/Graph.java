package za.co.ruanbotes.day.fifteen;

import java.util.*;
import java.util.stream.Collectors;

public class Graph {

    protected Vertex[][] getMatrix(String[] lines) {
        Vertex[][] matrix = new Vertex[lines.length][lines[0].length()];

        for (int i = 0; i < lines.length; i++) {
            String[] line = lines[i].split("(?!^)");
            for (int j = 0; j < line.length; j++) {
                matrix[i][j] = new Vertex(Integer.parseInt(line[j]));
            }
        }

        return matrix;
    }

    protected Vertex[][] getMatrixMultiplied(String[] lines) {
        Vertex[][] matrix = new Vertex[lines.length * 5][lines[0].length() * 5];

        for (int i = 0; i < lines.length; i++) {
            String[] line = lines[i].split("(?!^)");
            for (int j = 0; j < line.length; j++) {
                matrix[i][j] = new Vertex(Integer.parseInt(line[j]));
            }
        }

        for (int i = lines.length; i < lines.length * 5; i++) {
            for (int j = 0; j < lines[0].length(); j++) {
                Integer newValue = (matrix[i - lines.length][j].weight) + 1;
                if (newValue > 9) {
                    newValue = 1;
                }
                matrix[i][j] = new Vertex(newValue);
            }
        }

        for (int i = 0; i < lines.length * 5; i++) {
            for (int j = lines.length; j < lines[0].length() * 5; j++) {
                Integer newValue = (matrix[i][j - lines.length].weight) + 1;
                if (newValue > 9) {
                    newValue = 1;
                }
                matrix[i][j] = new Vertex(newValue);
            }
        }

        return matrix;
    }

    protected Vertex[][] addEdges(Vertex[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                List<Edge> adjacencies = new ArrayList<>();
                if (i - 1 >= 0) {
                    adjacencies.add(
                        new Edge(matrix[i-1][j], matrix[i-1][j].weight)
                    );
                }
                if (i + 1 < matrix.length) {
                    adjacencies.add(
                        new Edge(matrix[i+1][j], matrix[i+1][j].weight)
                    );
                }
                if (j - 1 >= 0) {
                    adjacencies.add(
                        new Edge(matrix[i][j-1], matrix[i][j-1].weight)
                    );
                }
                if (j + 1 < matrix[0].length) {
                    adjacencies.add(
                        new Edge(matrix[i][j+1], matrix[i][j+1].weight)
                    );
                }

                matrix[i][j].adjacencies = adjacencies.toArray(Edge[]::new);
            }
        }

        return matrix;
    }

    public void computePaths(Vertex source) {

        source.minDistance = 0.;
        Deque<Vertex> vertexQueue = new ArrayDeque<>();
        vertexQueue.add(source);

        while (!vertexQueue.isEmpty()) {
            Vertex current = vertexQueue.poll();

            // Visit each edge exiting current
            for (Edge edge : current.adjacencies)
            {
                Vertex target = edge.target;
                double weight = edge.weight;
                double distanceThroughCurrent = current.minDistance + weight;
                if (distanceThroughCurrent < target.minDistance) {
                    target.minDistance = distanceThroughCurrent ;
                    vertexQueue.add(target);
                }
            }
        }
    }
}

