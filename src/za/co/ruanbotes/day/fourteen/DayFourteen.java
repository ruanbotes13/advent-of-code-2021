package za.co.ruanbotes.day.fourteen;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class DayFourteen {
    public void run() {
        puzzleOne();
        puzzleTwo();
    }

    private void puzzleOne() {
        System.out.println("*********** Puzzle 1 **********");
        String[] lines = readFile("resources/day/fourteen/input.txt");
        String polymerTemplate = lines[0];
        List<Pair> pairList = getPairs(lines);
        String polymer = getPolymer(polymerTemplate, pairList, 40);
        System.out.println(polymer.length());
        System.out.println(getValue(polymer));
        System.out.println("*******************************");
    }

    private List<Pair> getPairs(String[] lines) {
        List<Pair> pairList = new ArrayList<>();

        for (int i = 2; i < lines.length; i++) {
            String[] parts = lines[i].split(" -> ");
            pairList.add(new Pair(parts[0], parts[1]));
        }

        return pairList;
    }

    private String getPolymer(String polymerTemplate, List<Pair> pairs, int steps) {
        String polymer = polymerTemplate;
        String newPolymer = "";

        for (int i = 0; i < steps; i++) {
            for (int k = 0; k < polymer.length() - 1; k++){
                for (int j = 0; j < pairs.size(); j++) {
                    if (pairs.get(j).pairMatches(polymer.substring(k, k + 2))) {
                        if (newPolymer.length() > 1 && newPolymer.charAt(newPolymer.length()-1) == polymer.substring(k, k + 2).charAt(0)) {
                            newPolymer = newPolymer + pairs.get(j).replacement + polymer.substring(k, k + 2).charAt(1);
                            break;
                        }
                        else {
                            newPolymer = newPolymer + polymer.substring(k, k + 2).charAt(0) + pairs.get(j).replacement + polymer.substring(k, k + 2).charAt(1);
                            break;
                        }

                    }
                }
            }

            polymer = newPolymer;
            newPolymer = "";
            System.out.println("Step: " + i + " - " + polymer.length());
        }

        return polymer;
    }

    private long getValue(String polymer) {
        Map<Character, Long> counts = new HashMap<>();
        Long leastCommon = Long.MAX_VALUE;
        Long mostCommon = 0l;

        for (int i = 0; i < polymer.length(); i++) {
            if (counts.containsKey(polymer.charAt(i))) {
                Long value = counts.get(polymer.charAt(i));
                counts.replace(polymer.charAt(i), value + 1);
            }
            else {
                counts.put(polymer.charAt(i), 1l);
            }
        }

        for (Map.Entry<Character, Long> entry : counts.entrySet()) {
            Long value = entry.getValue();

            if (value < leastCommon) {
                leastCommon = value;
            }

            if (value > mostCommon) {
                mostCommon = value;
            }
        }

        return mostCommon - leastCommon;
    }

    private void puzzleTwo() {
        System.out.println("*********** Puzzle 2 **********");
        String[] lines = readFile("resources/day/fourteen/input.txt");
        String polymerTemplate = lines[0];
        List<Pair> pairList = getPairs(lines);
        Map<String, Long> polymer = getPolymerImproved(polymerTemplate, pairList, 40);
        System.out.println(getValueImproved(polymer, polymerTemplate));
        System.out.println("*******************************");
    }

    private Map<String, Long>  getPolymerImproved(String polymerTemplate, List<Pair> pairs, int steps) {
        Map<String, String> ruleMap = new HashMap<>();
        Map<String, Long> counter = new HashMap<>();

        for (Pair pair : pairs) {
            ruleMap.put(pair.pattern, pair.replacement);
            counter.put(pair.pattern, 0l);
        }

        for (int i = 0; i < polymerTemplate.length() - 1; i++) {
            long currentValue = counter.get(polymerTemplate.substring(i, i + 2));
            counter.put(polymerTemplate.substring(i, i + 2), currentValue + 1);
        }

        for (int i = 0; i < steps; i++) {
            Map<String, Long> newCounter = new HashMap<>();

            for (Map.Entry<String, String> entry : ruleMap.entrySet()) {
                newCounter.put(entry.getKey(), 0l);
            }

            for (Map.Entry<String, Long> entry : counter.entrySet()) {
                if (newCounter.containsKey(entry.getKey().charAt(0) + ruleMap.get(entry.getKey()))) {
                    long value1 = newCounter.get(entry.getKey().charAt(0) + ruleMap.get(entry.getKey()));
                    newCounter.put(entry.getKey().charAt(0) + ruleMap.get(entry.getKey()), value1 + counter.get(entry.getKey()));
                }
                else {
                    newCounter.put(entry.getKey().charAt(0) + ruleMap.get(entry.getKey()), 1l);
                }

                if (newCounter.containsKey((ruleMap.get(entry.getKey()) + entry.getKey().charAt(1)))) {
                    long value2 = newCounter.get(ruleMap.get(entry.getKey()) + entry.getKey().charAt(1));
                    newCounter.put(ruleMap.get(entry.getKey()) + entry.getKey().charAt(1), value2 + counter.get(entry.getKey()));
                }
                else {
                    newCounter.put(ruleMap.get(entry.getKey()) + entry.getKey().charAt(1), 1l);
                }

            }

            counter = new HashMap<>(newCounter);
        }

        return counter;
    }

    private long getValueImproved(Map<String, Long> polymer, String template) {
        Map<Character, Long> counts = new HashMap<>();
        Long leastCommon = Long.MAX_VALUE;
        Long mostCommon = 0l;

        for (Map.Entry<String,Long> entry : polymer.entrySet()) {
            counts.put(entry.getKey().charAt(0), 0l);
        }

        for (Map.Entry<String,Long> entry : polymer.entrySet()) {
            long value = counts.get(entry.getKey().charAt(0));
            counts.replace(entry.getKey().charAt(0), entry.getValue() + value);
        }

        long value = counts.get(template.charAt(template.length() - 1));
        counts.replace(template.charAt(template.length() - 1), value + 1);

        for (Map.Entry<Character, Long> entry : counts.entrySet()) {
            Long value1 = entry.getValue();

            if (value1 < leastCommon) {
                leastCommon = value1;
            }

            if (value1 > mostCommon) {
                mostCommon = value1;
            }
        }

        return mostCommon - leastCommon;
    }

    private String[] readFile(String filePath) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader abc = new BufferedReader(new FileReader(filePath))) {
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
