package za.co.ruanbotes.day.twelve;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

public class DayTwelve {

    int countPuzzleOne = 0;
    int countPuzzleTwo = 0;

    public void run() {
//        puzzleOne();
        puzzleTwo();
    }

    private void puzzleOne() {
        System.out.println("*********** Puzzle 1 **********");
        String[] lines = readFile("resources/day/twelve/input.txt");
        Graph graph = getGraph(lines);
        lookupPaths(graph);
        System.out.println(this.countPuzzleOne);
        System.out.println("*******************************");
    }

    private Graph getGraph(String[] lines) {
        Graph graph = new Graph();

        for (int i = 0; i < lines.length; i++) {
            String[] parts = lines[i].split("-");
            graph.addTwoWayVertex(parts[0], parts[1]);
        }

        return graph;
    }

    private void lookupPaths(Graph graph) {
        LinkedList<String> visited = new LinkedList();
        visited.add("start");
        depthFirst(graph, visited);
    }

    private void depthFirst(Graph graph, LinkedList<String> visited) {
        LinkedList<String> nodes = graph.adjacentNodes(visited);
        // examine adjacent nodes
        for (String node : nodes) {
            if (node.equals("end")) {
                visited.add(node);
                printPath(visited);
                visited.removeLast();
                break;
            }
        }
        for (String node : nodes) {
            if (node.equals("end")) {
                continue;
            }
            visited.addLast(node);
            depthFirst(graph, visited);
            visited.removeLast();
        }
    }

    private void printPath(LinkedList<String> visited) {
        for (String node : visited) {
            System.out.print(node);
            System.out.print(" ");
        }
        System.out.println();
        this.countPuzzleOne++;
    }

    private void puzzleTwo() {
        System.out.println("*********** Puzzle 2 **********");
        String[] lines = readFile("resources/day/twelve/input.txt");
        Graph graph = getGraph(lines);
        lookupPathsTwo(graph);
        System.out.println(this.countPuzzleTwo);
        System.out.println("*******************************");
    }

    private void lookupPathsTwo(Graph graph) {
        LinkedList<String> visited = new LinkedList();
        visited.add("start");
        depthFirstTwo(graph, visited);
    }

    private void depthFirstTwo(Graph graph, LinkedList<String> visited) {
        LinkedList<String> nodes = graph.adjacentNodesNotFiltered(visited);

        LinkedList<String> adjacentFiltered = nodes
            .stream()
            .filter(node -> {
                if (graph.isStringLowerCase(node) && visited.contains(node) && alreadyContainsDouble(visited)) {
                    return false;
                }

                return true;
            })
            .collect(Collectors.toCollection(LinkedList::new));

        // examine adjacent nodes
        for (String node : adjacentFiltered) {
            if (node.equals("end")) {
                visited.add(node);
                printPathTwo(visited);
                visited.removeLast();
                break;
            }
        }
        for (String node : adjacentFiltered) {
            if (node.equals("end")) {
                continue;
            }
            visited.addLast(node);
            depthFirstTwo(graph, visited);
            visited.removeLast();
        }
    }

    private boolean alreadyContainsDouble(LinkedList<String> visited) {
        HashMap<String, Integer> map = new HashMap<>();

        for (int i = 0; i < visited.size(); i++) {
            if (map.containsKey(visited.get(i))) {
                map.replace(visited.get(i), map.get(visited.get(i)) + 1);
            }
            else {
                map.put(visited.get(i), 1);
            }
        }

        boolean hasMultiple = false;

        for (Map.Entry mapElement : map.entrySet()) {
            String key = (String)mapElement.getKey();
            int value = ((int)mapElement.getValue());

            if (!key.equals("start") && !key.equals("end")) {
                if (isStringLowerCase(key) && value > 1) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean isStringLowerCase(String str){

        //convert String to char array
        char[] charArray = str.toCharArray();

        for(int i=0; i < charArray.length; i++){


            //if any character is in upper case, return false
            if( Character.isUpperCase( charArray[i] ))
                return false;

        }

        return true;

    }

    private void printPathTwo(LinkedList<String> visited) {
        for (String node : visited) {
            System.out.print(node);
            System.out.print(" ");
        }
        System.out.println();
        this.countPuzzleTwo++;
    }

    private String[] readFile(String filePath) {
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
