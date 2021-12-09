import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws IOException {
//	    puzzleOne();
        puzzleTwo();
    }

    private static void puzzleTwo() throws IOException {
        String[] lines = readFile("input.txt");
//        int ones[] = new int[lines[0].length()];
////        Arrays.fill(ones, 0);
//        int zeros[] = new int[lines[0].length()];
////        Arrays.fill(zeros, 0);
//
//        for (int i = 0; i < lines.length; i++) {
//            for (int j = 0; j < lines[i].length(); j++) {
//                if (lines[i].charAt(j) == '0') {
//                    zeros[j] += 1;
//                }
//                else {
//                    ones[j] += 1;
//                }
//            }
//        }

        String oxygen = oxygenGenerator(lines, 0)[0];
        String cotwo = cotwo(lines, 0)[0];
        System.out.println(Integer.parseInt(oxygen, 2) * Integer.parseInt(cotwo, 2));
//        System.out.println(oxygen);
    }

    private static String[] oxygenGenerator(String[] binaries, int position) {
        if (binaries.length == 1) {
            return binaries;
        }
        else {
            int ones[] = new int[binaries[0].length()];
            int zeros[] = new int[binaries[0].length()];

            for (int i = 0; i < binaries.length; i++) {
                for (int j = 0; j < binaries[i].length(); j++) {
                    if (binaries[i].charAt(j) == '0') {
                        zeros[j] += 1;
                    }
                    else {
                        ones[j] += 1;
                    }
                }
            }

            List<String> list = new ArrayList<>();

            for (int i = 0; i < binaries.length; i++) {
                if (ones[position] > zeros[position]) {
                    if (binaries[i].charAt(position) == '1') {
                        list.add(binaries[i]);
                    }
                }
                else if (ones[position] < zeros[position]) {
                    if (binaries[i].charAt(position) == '0') {
                        list.add(binaries[i]);
                    }
                } else {
                    if (binaries[i].charAt(position) == '1') {
                        list.add(binaries[i]);
                    }
                }
            }

            return oxygenGenerator(
                list.toArray(new String[]{}),
                position + 1
            );
        }
    }

    private static String[] cotwo(String[] binaries, int position) {
        if (binaries.length == 1) {
            return binaries;
        }
        else {
            int ones[] = new int[binaries[0].length()];
            int zeros[] = new int[binaries[0].length()];

            for (int i = 0; i < binaries.length; i++) {
                for (int j = 0; j < binaries[i].length(); j++) {
                    if (binaries[i].charAt(j) == '0') {
                        zeros[j] += 1;
                    }
                    else {
                        ones[j] += 1;
                    }
                }
            }

            List<String> list = new ArrayList<>();

            for (int i = 0; i < binaries.length; i++) {
                if (ones[position] > zeros[position]) {
                    if (binaries[i].charAt(position) == '0') {
                        list.add(binaries[i]);
                    }
                }
                else if (ones[position] < zeros[position]) {
                    if (binaries[i].charAt(position) == '1') {
                        list.add(binaries[i]);
                    }
                } else {
                    if (binaries[i].charAt(position) == '0') {
                        list.add(binaries[i]);
                    }
                }
            }

            return cotwo(
                list.toArray(new String[]{}),
                position + 1
            );
        }
    }

    private static void puzzleOne() throws IOException {
        String[] lines = readFile("testInput.txt");
        int ones[] = new int[lines[0].length()];
//        Arrays.fill(ones, 0);
        int zeros[] = new int[lines[0].length()];
//        Arrays.fill(zeros, 0);

        for (int i = 0; i < lines.length; i++) {
            for (int j = 0; j < lines[i].length(); j++) {
                if (lines[i].charAt(j) == '0') {
                    zeros[j] += 1;
                }
                else {
                    ones[j] += 1;
                }
            }
        }

        String gamma = "";
        String epsilon = "";

        for (int i = 0; i < ones.length; i++) {
            if (ones[i] > zeros[i]) {
                gamma += "1";
                epsilon += "0";
            }
            else {
                epsilon += "1";
                gamma += "0";
            }
        }

        System.out.println(Integer.parseInt(gamma, 2) * Integer.parseInt(epsilon, 2));
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
