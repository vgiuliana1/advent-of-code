package vdg.aoc._2022;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day1 {

    private static int TOTAL_ZEROS = 3;

    public static void solution() {
        File inputFile = new File("AOC2022/src/main/resources/Day1-input.txt");
        try (Stream<String> lines = Files.lines(Paths.get(inputFile.getAbsolutePath()))) {

            int max = 0;
            int sum = 0;
            int[] maxCalories = new int[3];

            for (String l : lines.collect(Collectors.toList())) {
                if (l.isEmpty()) {
                    if (sum > max) {
                        max = sum;
                    }
                    checkMaxCalories(maxCalories, sum);
                    sum = 0;
                } else {
                    sum += Integer.parseInt(l);
                }
            }

            System.out.println("Max calories:       " + max);
            System.out.println("Three max calories: " + IntStream.of(maxCalories).sum());
        } catch (IOException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    private static void checkMaxCalories(int[] maxCalories, final Integer max) {
        for (int i = 0; i < 3; i++) {
            if (TOTAL_ZEROS != 0) {
                if (maxCalories[i] == 0) {
                    maxCalories[i] = max;
                    TOTAL_ZEROS--;
                    break;
                }
            } else if (max > maxCalories[i]) {
                maxCalories[i] = max;
                break;
            }
        }
    }
}
