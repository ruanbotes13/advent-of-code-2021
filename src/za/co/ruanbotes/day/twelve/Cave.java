package za.co.ruanbotes.day.twelve;

import java.util.ArrayList;
import java.util.List;

public class Cave {
    public String name;
    public boolean bigCave;
    public boolean startCave;
    public boolean endCave;
    public boolean visited;
    public List<Cave> connections;

    public Cave(String name) {
        this.name = name;
        this.connections = new ArrayList<>();
        this.visited = false;

        if (name.equals("start")) {
            this.startCave = true;
        }
        else if (name.equals("end")) {
            this.endCave = true;
        }
        else if (name.equals(name.toUpperCase())) {
            bigCave = true;
        }
        else {
            bigCave = false;
        }
    }

    public void addNeighbour(Cave cave) {
        this.connections.add(cave);
    }
}
