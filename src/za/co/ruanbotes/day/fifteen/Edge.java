package za.co.ruanbotes.day.fifteen;

public class Edge {

    public Vertex target;
    public double weight;

    public Edge(Vertex argTarget, double argWeight) {
        target = argTarget; weight = argWeight;
    }
}
