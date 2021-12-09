import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
	    puzzleOne();
	    puzzleTwo();
    }

    private static void puzzleOne() throws IOException {
        String line = readFile("input.txt")[0];
        List<Long> lanternFish = getLanternFish(line.split(","));
        long numberLanternFish = simulateGrowth(lanternFish, 256);
        System.out.println(numberLanternFish);

    }

    private static  List<Long> getLanternFish(String[] internalClockValues) {
        List<Long> listOfLists = new ArrayList<Long>();
        for (int i = 0; i <= 8; i++) {
            listOfLists.add(
                count(internalClockValues, i)
            );
        }

        return listOfLists;
    }

    private static long count(String[] fish, int days) {
        long count = 0;

        for (int i = 0; i < fish.length; i++) {
            if (Integer.parseInt(fish[i]) == days) {
                count++;
            }
        }

        return count;
    }

    private static long simulateGrowth(List<Long> lanternFish, int days) {

        for (int i = 0; i < days; i++) {
            long zero = lanternFish.get(0);
            long one = lanternFish.get(1);
            long two = lanternFish.get(2);
            long three = lanternFish.get(3);
            long four = lanternFish.get(4);
            long five = lanternFish.get(5);
            long six = lanternFish.get(6);
            long seven = lanternFish.get(7);
            long eight = lanternFish.get(8);

            lanternFish.set(0, one);
            lanternFish.set(1, two);
            lanternFish.set(2, three);
            lanternFish.set(3, four);
            lanternFish.set(4, five);
            lanternFish.set(5, six);
            lanternFish.set(6, seven + zero);
            lanternFish.set(7, eight);
            lanternFish.set(8, zero);
        }

        return calculate(lanternFish);
    }

    private static long calculate(List<Long> lanternFish) {
        long sum = 0l;

        for (int i = 0; i < lanternFish.size(); i++) {
            sum = sum + lanternFish.get(i);
        }

        return sum;
    }

    private static void puzzleTwo() {

    }

    private static String[] readFile(String filePath) throws IOException {
        BufferedReader abc = new BufferedReader(new FileReader(filePath));
        List<String> lines = new ArrayList<String>();
        String line;

        while((line = abc.readLine()) != null) {
            lines.add(line);
        }
        abc.close();

        return lines.toArray(new String[]{});
    }
}
