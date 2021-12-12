package za.co.ruanbotes.day.twelve;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Graph {
    private Map<String, LinkedHashSet<String>> map = new HashMap();
    public boolean smallCaveVisited = false;

    public void addEdge(String node1, String node2) {
        LinkedHashSet<String> adjacent = map.get(node1);
        if(adjacent==null) {
            adjacent = new LinkedHashSet();
            map.put(node1, adjacent);
        }
        adjacent.add(node2);
    }

    public void addTwoWayVertex(String node1, String node2) {
        addEdge(node1, node2);
        addEdge(node2, node1);
    }

    public boolean isConnected(String node1, String node2) {
        Set adjacent = map.get(node1);
        if(adjacent==null) {
            return false;
        }
        return adjacent.contains(node2);
    }

    public LinkedList<String> adjacentNodes(LinkedList<String> visited) {
        LinkedHashSet<String> adjacent = map.get(visited.getLast());

        if(adjacent==null) {
            return new LinkedList();
        }

        LinkedHashSet<String> adjacentFiltered = adjacent
            .stream()
            .filter(node -> {
                if (node.equals("start")) {
                    return false;
                }
                else if (isStringLowerCase(node) && visited.contains(node)) {
                    return false;
                }

                return true;
            })
            .collect(Collectors.toCollection(LinkedHashSet::new));

        return new LinkedList<String>(adjacentFiltered);
    }

    public LinkedList<String> adjacentNodesNotFiltered(LinkedList<String> visited) {
        LinkedHashSet<String> adjacent = map.get(visited.getLast());

        if(adjacent==null) {
            return new LinkedList();
        }

        LinkedHashSet<String> adjacentFiltered = adjacent
            .stream()
            .filter(node -> {
                if (node.equals("start")) {
                    return false;
                }

                return true;
            })
            .collect(Collectors.toCollection(LinkedHashSet::new));

        return new LinkedList<String>(adjacentFiltered);
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
}

