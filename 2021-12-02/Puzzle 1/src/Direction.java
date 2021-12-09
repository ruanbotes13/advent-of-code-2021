public enum Direction {
    FORWARD,
    DOWN,
    UP;

    public static Direction fromString(String direction) {
        if (direction.equals("forward")) {
            return FORWARD;
        }
        else if (direction.equals("down")) {
            return DOWN;
        }
        else if (direction.equals("up")) {
            return UP;
        }
        else {
            return null;
        }
    }
}
