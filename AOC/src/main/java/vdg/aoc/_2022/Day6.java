package vdg.aoc._2022;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day6 {

    private final static int CHARACTER_COUNT = 14; // switch between 4 and 14 for Part 1, Part 2 respectively

    public static void solution() {
        File inputFile = new File("AOC2022/src/main/resources/Day6-input.txt");
        try (Stream<String> lines = Files.lines(Paths.get(inputFile.getAbsolutePath()))) {
            String inputLine = lines.collect(Collectors.toList()).get(0);

            for (int i = 0; i <= inputLine.length() - 1; i++) {
                Set<Character> characterSet = new HashSet<>();
                for (int k = 0; k < CHARACTER_COUNT; k++) {
                    characterSet.add(inputLine.charAt(i + k));
                }
                if (characterSet.size() == CHARACTER_COUNT) {
                    System.out.println("Marker count: " + (i + CHARACTER_COUNT));
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Error:" + e.getMessage());
        }
    }
}