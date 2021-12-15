package za.co.ruanbotes.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileReader {
    public static String[] readFile(String filePath) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader abc = new BufferedReader(new java.io.FileReader(filePath))) {
            String line;

            while ((line = abc.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.println("File not read");
        }

        return lines.toArray(new String[]{});
    }
}
