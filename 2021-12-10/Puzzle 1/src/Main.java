import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
//        puzzleOne();
        puzzleTwo();
    }

    private static void puzzleOne() {
        System.out.println("*********** Puzzle 1 **********");
        String[] lines = readFile("input.txt");
        List<String> syntaxErrors = getSyntaxErrors(lines);
        System.out.println(calculateSyntaxScore(syntaxErrors));
        System.out.println("*******************************");
    }

    private static List<String> getSyntaxErrors(String[] lines) {
        List<String> syntaxErrors = new ArrayList<>();

        for (int i = 0; i < lines.length; i++) {
            String[] lineCharacters = lines[i].split("(?!^)");
            Stack stack = new Stack();

            for (int j = 0; j < lineCharacters.length; j++) {
                if (lineCharacters[j].equals("(") || lineCharacters[j].equals("[") || lineCharacters[j].equals("{") || lineCharacters[j].equals("<")) {
                    stack.push(lineCharacters[j]);
                } else {
                    if (stack.isEmpty()) {
                        break;
                    }

                    String topOfStack = stack.pop();

                    if (lineCharacters[j].equals(")") && !topOfStack.equals("(")) {
                        syntaxErrors.add(lineCharacters[j]);
                        break;
                    }
                    else if (lineCharacters[j].equals("]") && !topOfStack.equals("[")) {
                        syntaxErrors.add(lineCharacters[j]);
                        break;
                    }
                    else if (lineCharacters[j].equals("}") && !topOfStack.equals("{")) {
                        syntaxErrors.add(lineCharacters[j]);
                        break;
                    }
                    else if (lineCharacters[j].equals(">") && !topOfStack.equals("<")) {
                        syntaxErrors.add(lineCharacters[j]);
                        break;
                    }
                }
            }
        }

        return syntaxErrors;
    }

    private static long calculateSyntaxScore(List<String> syntaxErrors) {
        long score = 0l;

        for (int i = 0; i < syntaxErrors.size(); i++) {
            if (syntaxErrors.get(i).equals(")")) {
                score = score + 3;
            }
            else if (syntaxErrors.get(i).equals("]")) {
                score = score + 57;
            }
            else if (syntaxErrors.get(i).equals("}")) {
                score = score + 1197;
            }
            else if (syntaxErrors.get(i).equals(">")) {
                score = score + 25137;
            }
        }

        return score;
    }

    private static void puzzleTwo() {
        System.out.println("*********** Puzzle 2 **********");
        String[] lines = readFile("input.txt");
        List<String> closingCharacters = getClosingCharacters(lines);
        System.out.println(getScore(closingCharacters));
        System.out.println("*******************************");
    }

    private static List<String> getClosingCharacters(String[] lines) {
        List<String> closingCharactersList = new ArrayList<>();

        for (int i = 0; i < lines.length; i++) {
            String[] lineCharacters = lines[i].split("(?!^)");
            Stack stack = new Stack();
            String closingCharacters = "";
            boolean incompleteLine = true;

            for (int j = 0; j < lineCharacters.length; j++) {
                if (lineCharacters[j].equals("(") || lineCharacters[j].equals("[") || lineCharacters[j].equals("{") || lineCharacters[j].equals("<")) {
                    stack.push(lineCharacters[j]);
                } else {
                    if (stack.isEmpty()) {
                        break;
                    }

                    String topOfStack = stack.pop();

                    if (lineCharacters[j].equals(")") && !topOfStack.equals("(")) {
                        incompleteLine = false;
                        break;
                    }
                    else if (lineCharacters[j].equals("]") && !topOfStack.equals("[")) {
                        incompleteLine = false;
                        break;
                    }
                    else if (lineCharacters[j].equals("}") && !topOfStack.equals("{")) {
                        incompleteLine = false;
                        break;
                    }
                    else if (lineCharacters[j].equals(">") && !topOfStack.equals("<")) {
                        incompleteLine = false;
                        break;
                    }
                }
            }

            if (incompleteLine == true) {
                while (!stack.isEmpty()) {
                    String character = stack.pop();

                    if (character.equals("(")) {
                        closingCharacters = closingCharacters + ")";
                    }
                    else if (character.equals("[")) {
                        closingCharacters = closingCharacters + "]";
                    }
                    else if (character.equals("{")) {
                        closingCharacters = closingCharacters + "}";
                    }
                    else if (character.equals("<")) {
                        closingCharacters = closingCharacters + ">";
                    }
                }

                if (closingCharacters.length() > 0) {
                    closingCharactersList.add(closingCharacters);
                }
            }
        }

        return closingCharactersList;
    }

    private static long getScore(List<String> completionStrings) {
        List<Long> scores = new ArrayList<>();

        for (int i = 0; i < completionStrings.size(); i++) {
            Long score  = 0l;
            String[] characters = completionStrings.get(i).split("(?!^)");

            for (int j = 0; j < characters.length; j++) {
                if (characters[j].equals(")")) {
                    score = (score * 5) + 1;
                }
                else if (characters[j].equals("]")) {
                    score = (score * 5) + 2;
                }
                else if (characters[j].equals("}")) {
                    score = (score * 5) + 3;
                }
                else if (characters[j].equals(">")) {
                    score = (score * 5) + 4;
                }
            }

            scores.add(score);
        }

        scores.sort((o1, o2) -> o2.compareTo(o1));

        int middle = (scores.size() + 1) / 2;

        return scores.get(middle - 1);
    }

    private static String[] readFile(String filePath) {
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
