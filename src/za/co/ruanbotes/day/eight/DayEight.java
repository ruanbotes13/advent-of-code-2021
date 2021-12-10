package za.co.ruanbotes.day.eight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DayEight {
    public void run() throws IOException {
        puzzleOne();
        puzzleTwo();
    }

    private void puzzleOne() throws IOException {
        System.out.println("*********** Puzzle 1 **********");

        int one = 0;
        int four = 0;
        int seven = 0;
        int eight = 0;

        String[] lines = readFile("resources/day/eight/input.txt");

        for (int i = 0; i < lines.length; i++) {
            String[] leftRight = lines[i].split(" \\| ");
            String[] rightDigits = leftRight[1].split(" ");

            for (int j = 0; j < rightDigits.length; j++) {
                if (rightDigits[j].length() == 2) {
                    one++;
                }
                else if (rightDigits[j].length() == 4) {
                    four++;
                }
                else if (rightDigits[j].length() == 3) {
                    seven++;
                }
                else if (rightDigits[j].length() == 7) {
                    eight++;
                }
            }
        }

        System.out.println(one + four + seven + eight);


        System.out.println("*******************************");
    }

    private void puzzleTwo() throws IOException {
        System.out.println("*********** Puzzle 2 **********");

        String[] lines = readFile("resources/day/eight/input.txt");

        List<Integer> numberList = new ArrayList<>();

        for (int i = 0; i < lines.length; i++) {
            String[] leftRight = lines[i].split(" \\| ");
            String[] leftDigits = leftRight[0].split(" ");
            String[] rightDigits = leftRight[1].split(" ");

            Digit one = null;
            Digit two = null;
            Digit three = null;
            Digit four = null;
            Digit five = null;
            Digit six = null;
            Digit seven = null;
            Digit eight = null;
            Digit nine = null;
            Digit zero = null;



            for (int j = 0; j < leftDigits.length; j++) {
                if (leftDigits[j].length() == 2 && one == null) {
                    one = new Digit();
                    one.setPredefined(leftDigits[j]);
                }
                else if (leftDigits[j].length() == 4 && four == null) {
                    four = new Digit();
                    four.setPredefined(leftDigits[j]);
                }
                else if (leftDigits[j].length() == 3 && seven == null) {
                    seven = new Digit();
                    seven.setPredefined(leftDigits[j]);
                }
                else if (leftDigits[j].length() == 7 && eight == null) {
                    eight = new Digit();
                    eight.setPredefined(leftDigits[j]);
                }
            }

            for (int j = 0; j < leftDigits.length; j++) {
                if (leftDigits[j].length() == 5 || leftDigits[j].length() == 6) {
                    int correspondingWithOne = one.getCorresponding(leftDigits[j]);
                    int correspondingWithFour = four.getCorresponding(leftDigits[j]);
                    int correspondingWithSeven = seven.getCorresponding(leftDigits[j]);
                    int correspondingWithEigth = eight.getCorresponding(leftDigits[j]);

                    if (leftDigits[j].length() == 5) { // 3
                        if (correspondingWithOne == 2 && correspondingWithFour == 3 && correspondingWithSeven == 3 && correspondingWithEigth == 5) {  // done
                            three = new Digit();
                            three.setDigit(3, leftDigits[j]);
                        }
                        else if (correspondingWithOne == 1 && correspondingWithFour == 2 && correspondingWithSeven == 2 && correspondingWithEigth == 5) { // done
                            two = new Digit();
                            two.setDigit(2, leftDigits[j]);
                        }
                        else if (correspondingWithOne == 1 && correspondingWithFour == 3 && correspondingWithSeven == 2 && correspondingWithEigth == 5) { // done
                            five = new Digit();
                            five.setDigit(5, leftDigits[j]);
                        }


                    }
                    else if (leftDigits[j].length() == 6) {
                        if (correspondingWithOne == 1 && correspondingWithFour == 3 && correspondingWithSeven == 2 && correspondingWithEigth == 6) {
                            six = new Digit();
                            six.setDigit(6, leftDigits[j]);
                        }
                        else if (correspondingWithOne == 2 && correspondingWithFour == 4 && correspondingWithSeven == 3 && correspondingWithEigth == 6) {
                            nine = new Digit();
                            nine.setDigit(9, leftDigits[j]);
                        }
                        else if (correspondingWithOne == 2 && correspondingWithFour == 3 && correspondingWithSeven == 3 && correspondingWithEigth == 6) {
                            zero = new Digit();
                            zero.setDigit(0, leftDigits[j]);
                        }
                    }
                }
            }

            String number = "";

            for (int j = 0; j < rightDigits.length; j++) {

                if (one != null && one.isDigit(rightDigits[j])) {
                    number += "1";
                }
                else if (two != null && two.isDigit(rightDigits[j])) {
                    number += "2";
                }
                else if (three != null && three.isDigit(rightDigits[j])) {
                    number += "3";
                }
                else if (four != null && four.isDigit(rightDigits[j])) {
                    number += "4";
                }
                else if (five != null && five.isDigit(rightDigits[j])) {
                    number += "5";
                }
                else if (six != null && six.isDigit(rightDigits[j])) {
                    number += "6";
                }
                else if (seven != null && seven.isDigit(rightDigits[j])) {
                    number += "7";
                }
                else if (eight != null && eight.isDigit(rightDigits[j])) {
                    number += "8";
                }
                else if (nine != null && nine.isDigit(rightDigits[j])) {
                    number += "9";
                }
                else if (zero != null && zero.isDigit(rightDigits[j])) {
                    number += "0";
                }
            }

            numberList.add(Integer.parseInt(number));
            number = "";
        }

        int sum = 0;

        for (int i = 0; i < numberList.size(); i++) {
            sum = sum + numberList.get(i);
        }

        System.out.println(sum);

        System.out.println("*******************************");
    }

    private String[] readFile(String filePath) throws IOException {
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
