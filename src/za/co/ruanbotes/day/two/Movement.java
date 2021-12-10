package za.co.ruanbotes.day.two;

public class Movement {
    public int distance;
    public Direction direction;

    public Movement(int distance, String direction) {
        this.distance = distance;
        this.direction = Direction.fromString(direction);
    }
}
