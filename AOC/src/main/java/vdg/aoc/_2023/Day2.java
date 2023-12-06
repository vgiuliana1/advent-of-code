package vdg.aoc._2023;

import vdg.aoc.common.Util;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day2 {

    final static int MAX_RED = 12;
    final static int MAX_GREEN = 13;
    final static int MAX_BLUE = 14;

    final static String R = "red";
    final static String G = "green";
    final static String B = "blue";

    public static void main(String[] args) {
        final List<String> input = Util.readFile("AOC/src/main/resources/_2023/Day2.txt");
        part1(input);
        part2(input);
    }

    private static void part1(List<String> input) {
        int sum = 0;
        int gameId = 0;
        for (String s : input) {
            boolean allValid = true;
            gameId = Integer.parseInt(s.substring(s.indexOf(" ") + 1, s.indexOf(":")));
            final String games = s.substring(s.indexOf(":"));
            String[] gamesArray = games.split(";");
            gamesArray[0] = gamesArray[0].replace(": ", "");
            for (String game : gamesArray) {
                String[] cubes = Util.trimArray(game.split(","));
                Map<String, Integer> colorCount = new HashMap<>();
                for (String c : cubes) {
                    String[] cubeColor = Util.trimArray(c.split(" "));
                    colorCount.put(cubeColor[1], Integer.valueOf(cubeColor[0]));
                }
                if (Util.valueOrZero(colorCount.get(R)) > MAX_RED ||
                        Util.valueOrZero(colorCount.get(G)) > MAX_GREEN ||
                        Util.valueOrZero(colorCount.get(B)) > MAX_BLUE) {
                    allValid = false;
                }
            }
            if (allValid) {
                sum += gameId;
            }
        }
        // Correct answer: 2265
        // Failed attempts:
        System.out.println("part 1 = " + sum);
    }

    private static void part2(List<String> input) {
        int sum = 0;
        for (String s : input) {
            int minRed = 0;
            int minGreen = 0;
            int minBlue = 0;

            final String games = s.substring(s.indexOf(":"));
            String[] gamesArray = games.split(";");
            gamesArray[0] = gamesArray[0].replace(": ", "");
            for (String game : gamesArray) {
                String[] cubes = Util.trimArray(game.split(","));
                Map<String, Integer> colorCount = new HashMap<>();
                for (String c : cubes) {
                    String[] cubeColor = Util.trimArray(c.split(" "));
                    colorCount.put(cubeColor[1], Integer.valueOf(cubeColor[0]));
                }

                minRed = Objects.isNull(colorCount.get(R)) ? minRed : (minRed > colorCount.get(R) ? minRed : colorCount.get(R));
                minGreen = Objects.isNull(colorCount.get(G)) ? minGreen : (minGreen > colorCount.get(G) ? minGreen : colorCount.get(G));
                minBlue = Objects.isNull(colorCount.get(B)) ? minBlue : (minBlue > colorCount.get(B) ? minBlue : colorCount.get(B));
            }

            if (minRed == 0) minRed = 1;
            if (minGreen == 0) minGreen = 1;
            if (minBlue == 0) minBlue = 1;

            sum += (minRed * minGreen * minBlue);
        }
        // Correct answer: 64097
        // Failed attempts: 2224
        System.out.println("part 2 = " + sum);
    }
}