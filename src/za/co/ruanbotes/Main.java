package za.co.ruanbotes;

import za.co.ruanbotes.day.eight.DayEight;
import za.co.ruanbotes.day.eleven.DayEleven;
import za.co.ruanbotes.day.five.DayFive;
import za.co.ruanbotes.day.four.DayFour;
import za.co.ruanbotes.day.nine.DayNine;
import za.co.ruanbotes.day.one.DayOne;
import za.co.ruanbotes.day.seven.DaySeven;
import za.co.ruanbotes.day.six.DaySix;
import za.co.ruanbotes.day.ten.DayTen;
import za.co.ruanbotes.day.three.DayThree;
import za.co.ruanbotes.day.twelve.DayTwelve;
import za.co.ruanbotes.day.two.DayTwo;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        dayTwelve();
        // dayEleven();
        // dayTen();
        //dayNine();
        // dayEight();
        // daySeven();
        // daySix();
        // dayFive();
        // dayFour();
        // dayThree();
        // dayTwo();
        // dayOne();
    }

    /**
     * Day 12: Passage Pathing
     */
    private static void dayTwelve() {
        DayTwelve dayTwelve = new DayTwelve();
        dayTwelve.run();
    }

    /**
     * Day 11: Dumbo Octopus
     */
    private static void dayEleven() {
        DayEleven dayEleven = new DayEleven();
        dayEleven.run();
    }

    /**
     * Day 10: Syntax Scoring
     */
    private static void dayTen() {
        DayTen dayTen = new DayTen();
        dayTen.run();
    }

    /**
     * Day 9: Smoke basin
     */
    private static void dayNine() {
        DayNine dayNine = new DayNine();
        try {
            dayNine.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Day 8: Seven Segment Search
     */
    private static void dayEight() {
        DayEight dayEight = new DayEight();
        try {
            dayEight.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Day 7: The treachery of whales
     */
    private static void daySeven() {
        DaySeven daySeven = new DaySeven();
        try {
            daySeven.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Day 6: Lanternfish
     */
    private static void daySix() {
        DaySix daySix = new DaySix();
        try {
            daySix.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Day 5: Hydrothermal Venture
     */
    private static void dayFive() {
        DayFive dayFive = new DayFive();
        try {
            dayFive.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Day 4: Giant Squid
     */
    private static void dayFour() {
        DayFour dayFour = new DayFour();
        try {
            dayFour.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Day 3: Binary Diagnostic
     */
    private static void dayThree() {
        DayThree dayThree = new DayThree();
        try {
            dayThree.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Day 2: Dive
     */
    private static void dayTwo() {
        DayTwo dayTwo = new DayTwo();
        try {
            dayTwo.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Day 1: Sonar Sweep
     */
    private static void dayOne() {
        DayOne dayOne = new DayOne();
        try {
            dayOne.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
