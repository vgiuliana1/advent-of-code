package giuliana.vincent.aoc;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day4 {

    public static void solution() {
        File inputFile = new File("AOC2022/src/main/resources/Day4-input.txt");
        try (Stream<String> lines = Files.lines(Paths.get(inputFile.getAbsolutePath()))) {
            int fullyContainedCount = 0;
            int anyOverlapCount = 0;

            for (String line : lines.collect(Collectors.toList())) {
                String[] pairs = line.split(",");
                String[] firstRange = pairs[0].split("-");
                String[] secondRange = pairs[1].split("-");

                int firstStart = Integer.parseInt(firstRange[0]);
                int firstEnd = Integer.parseInt(firstRange[1]);
                int secondStart = Integer.parseInt(secondRange[0]);
                int secondEnd = Integer.parseInt(secondRange[1]);

                if (fullyContainsRange(firstStart, firstEnd, secondStart, secondEnd) ||
                        fullyContainsRange(secondStart, secondEnd, firstStart, firstEnd))
                    fullyContainedCount++;

                if (anyOverlap(firstStart, firstEnd, secondStart, secondEnd) ||
                        anyOverlap(secondStart, secondEnd, firstStart, firstEnd))
                    anyOverlapCount++;
            }
            System.out.println("Total pairs (Part 1): " + fullyContainedCount);
            System.out.println("Total pairs (Part 2): " + anyOverlapCount);
        } catch (IOException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    private static boolean fullyContainsRange(final int firstStart, final int firstEnd, final int secondStart, final int secondEnd) {
        return (firstStart <= secondStart) && (firstEnd >= secondEnd);
    }

    private static boolean anyOverlap(final int firstStart, final int firstEnd, final int secondStart, final int secondEnd) {
        if ((firstStart <= secondStart) && (firstEnd >= secondStart)) {
            return true;
        } else return (secondStart <= firstStart) && (secondEnd >= firstStart);
    }
}
