package za.co.ruanbotes.utils;

public class Printer {
    public static void print(int part, int value) {
        System.out.println("*********** Puzzle " + part + " **********");
        String answer = String.valueOf(value);
        System.out.print("* ");
        System.out.print(value);
        for (int i = 0; i < (30-2-answer.length()); i++) {
            System.out.print(" ");
        }
        System.out.print("*\n");
        System.out.println("*******************************");
    }

    public static void print(int part, long value) {
        System.out.println("*********** Puzzle " + part + " **********");
        String answer = String.valueOf(value);
        System.out.print("* ");
        System.out.print(value);
        for (int i = 0; i < (30-2-answer.length()); i++) {
            System.out.print(" ");
        }
        System.out.print("*\n");
        System.out.println("*******************************");
    }

    public static void print(int part, String value) {
        System.out.println("*********** Puzzle " + part + " **********");
        String answer = String.valueOf(value);
        System.out.print("* ");
        System.out.print(value);
        for (int i = 0; i < (30-2-answer.length()); i++) {
            System.out.print(" ");
        }
        System.out.print("*\n");
        System.out.println("*******************************");
    }
}
