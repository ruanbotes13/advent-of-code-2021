import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
	    Movement[] movements = readFile("input.txt");
	    int horizontal = 0;
	    int vertical = 0;

	    for(int i = 0; i < movements.length; i++) {
            if (movements[i].direction.equals(Direction.FORWARD)) {
                horizontal = horizontal + movements[i].distance;
            }
            else if (movements[i].direction.equals(Direction.UP)) {
                vertical = vertical - movements[i].distance;
            } else if (movements[i].direction.equals(Direction.DOWN)) {
                vertical = vertical + movements[i].distance;
            }
        }

        System.out.println(horizontal * vertical);

        horizontal = 0;
        vertical = 0;
        int aim = 0;

        for(int i = 0; i < movements.length; i++) {
            if (movements[i].direction.equals(Direction.FORWARD)) {
                horizontal = horizontal + movements[i].distance;
                vertical = vertical + (aim * movements[i].distance);
            }
            else if (movements[i].direction.equals(Direction.UP)) {
                aim = aim - movements[i].distance;
            } else if (movements[i].direction.equals(Direction.DOWN)) {
                aim = aim + movements[i].distance;
            }
        }

        System.out.println(horizontal * vertical);

    }

    private static Movement[] readFile(String filePath) throws IOException {
        BufferedReader abc = new BufferedReader(new FileReader(filePath));
        List<Movement> lines = new ArrayList<Movement>();
        String line;

        while((line = abc.readLine()) != null) {
            String[] parts = line.split(" ");
            lines.add(new Movement(Integer.parseInt(parts[1]), parts[0]));
        }
        abc.close();

        return lines.toArray(new Movement[]{});
    }
}
