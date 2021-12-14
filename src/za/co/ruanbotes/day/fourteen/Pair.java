package za.co.ruanbotes.day.fourteen;

public class Pair {
    String pattern;
    String replacement;

    public Pair(String pattern, String replacement) {
        this.pattern = pattern;
        this.replacement = replacement;
    }

    public boolean pairMatches(String pair) {
        if (pair.equals(pattern)) {
            return true;
        }

        return false;
    }
}
