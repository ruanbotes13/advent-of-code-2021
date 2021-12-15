package za.co.ruanbotes.day.fifteen;

public class Vertex implements Comparable<Vertex> {

    public int weight;
    public Edge[] adjacencies;
    public double minDistance = Double.POSITIVE_INFINITY;

    public Vertex(int weight) {
        this.weight = weight;
    }

    public int compareTo(Vertex other) {
        return Double.compare(minDistance, other.minDistance);
    }

}