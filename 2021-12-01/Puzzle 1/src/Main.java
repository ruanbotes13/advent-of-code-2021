import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        Integer[] depths = readFile("input.txt");
        int count = countIncreases(depths);
        System.out.println(count);

        int windowCount = countMovingSumIncreases(depths);
        System.out.println(windowCount);
    }

    private static int countMovingSumIncreases(Integer[] depths) {
        List<Integer> windows = new ArrayList<Integer>();
        int sum = 0;

        for (int i = 0; i <= depths.length; i++) {
            if (inRange(i, depths.length)) {
                sum = depths[i] + depths[i-1] + depths[i-2];
                windows.add(sum);
            }
        }

        Integer[] windowArray = windows.toArray(new Integer[]{});

        return countIncreases(windowArray);
    }

    private static boolean inRange(int number, int max) {
        if (number >= 2 && number < max && number-1 < max && number-2 < max) {
            return true;
        }

        return false;
    }

    private static int countIncreases(Integer[] depths) {
        int count = 0;
        for (int i = 1; i < depths.length; i++) {
            if (depths[i] > depths[i-1]) {
                count++;
            }
        }

        return count;
    }

    private static Integer[] readFile(String filePath) throws IOException {
        BufferedReader abc = new BufferedReader(new FileReader(filePath));
        List<Integer> lines = new ArrayList<Integer>();
        String line;

        while((line = abc.readLine()) != null) {
            lines.add(Integer.parseInt(line));
        }
        abc.close();

        return lines.toArray(new Integer[]{});
    }
}
