package za.co.ruanbotes.utils;

import java.util.ArrayList;
import java.util.List;

public class Converter {
    public static Integer[] convertToIntArray(String[] lines) {
        List<Integer> integerList = new ArrayList<>();

        for (int i = 0; i < lines.length; i++) {
            integerList.add(Integer.parseInt(lines[i]));
        }

        return integerList.toArray(new Integer[]{});
    }
}
