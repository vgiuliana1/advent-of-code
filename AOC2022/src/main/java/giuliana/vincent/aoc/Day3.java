package giuliana.vincent.aoc;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day3 {

    public static void solution() {
        File inputFile = new File("AOC2022/src/main/resources/Day3-input.txt");
        try (Stream<String> lines = Files.lines(Paths.get(inputFile.getAbsolutePath()))) {

            List<String> linesList = lines.collect(Collectors.toList());

            /* Part 1 */
            int sum = 0;
            for (String line : linesList) {
                String[] splitLine = new String[]{line.substring(0, line.length() / 2), line.substring(line.length() / 2)};
                sum += getLeftoverAmount(splitLine);
            }

            System.out.println("Sum (Part 1): " + sum);

            /* Part 2 */
            int sumP2 = 0;
            String[] groupLines = new String[3];
            for (int i = 0; i <= linesList.size() - 3; i += 3) {
                groupLines[0] = linesList.get(i);
                groupLines[1] = linesList.get(i + 1);
                groupLines[2] = linesList.get(i + 2);
                sumP2 += findCommonCharacter(groupLines);
            }

            System.out.println("Sum (Part 2): " + sumP2);

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

    private static int findCommonCharacter(String[] groupLines) {
        for (String s : groupLines[0].split("")) {
            if (groupLines[1].contains(s) && groupLines[2].contains(s)) {
                char c = s.toCharArray()[0];
                if (!Character.isUpperCase(c))
                    return c - 96;
                else return c - 38;
            }
        }
        throw new RuntimeException("WRONG P2");
    }
}