package giuliana.vincent.aoc;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day3 {

    public static void solution() {
        File inputFile = new File("AOC2022/src/main/resources/Day3-input.txt");
        try (Stream<String> lines = Files.lines(Paths.get(inputFile.getAbsolutePath()))) {
            int sum = 0;

            for (String line : lines.collect(Collectors.toList())) {
                String[] splitLine = new String[]{line.substring(0, line.length() / 2), line.substring(line.length() / 2)};
                sum += getLeftoverAmount(splitLine);
            }

            System.out.println("Sum: " + sum);

        } catch (IOException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    private static int getLeftoverAmount(final String[] splitLine) {
        String firstHalf = splitLine[0];
        String secondHalf = splitLine[1];

        for (String s : secondHalf.split("")) {
            if (firstHalf.contains(s)) {
                char c = s.toCharArray()[0];
                if (!Character.isUpperCase(c))
                    return c - 96;
                else return c - 38;
            }
        }

        throw new RuntimeException("WRONG");
    }
}
