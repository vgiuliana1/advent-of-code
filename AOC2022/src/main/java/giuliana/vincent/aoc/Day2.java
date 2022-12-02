package giuliana.vincent.aoc;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static giuliana.vincent.aoc.Day2.Outcome.*;

public class Day2 {

    enum Outcome {
        WIN(6),
        LOSS(0),
        DRAW(3);

        private final int points;

        Outcome(final int points) {
            this.points = points;
        }
    }

    public static void solution() {
        File inputFile = new File("AOC2022/src/main/resources/Day2-input.txt");
        try (Stream<String> lines = Files.lines(Paths.get(inputFile.getAbsolutePath()))) {

            int totalScore = 0;

            for (String line : lines.collect(Collectors.toList())) {
                String[] plays = line.split(" ");

                totalScore += calculateRound(plays[0], plays[1]);

            }

            System.out.println("Total score: " + totalScore);

        } catch (IOException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    private static int calculateRound(final String opponentPlayString, final String youPlayString) {
        int outcomePoints = 0;
        switch (opponentPlayString) {
            case "A":
                if (youPlayString.equals("X")) outcomePoints = DRAW.points;
                if (youPlayString.equals("Y")) outcomePoints = WIN.points;
                if (youPlayString.equals("Z")) outcomePoints = LOSS.points;
                break;
            case "B":
                if (youPlayString.equals("X")) outcomePoints = LOSS.points;
                if (youPlayString.equals("Y")) outcomePoints = DRAW.points;
                if (youPlayString.equals("Z")) outcomePoints = WIN.points;
                break;
            case "C":
                if (youPlayString.equals("X")) outcomePoints = WIN.points;
                if (youPlayString.equals("Y")) outcomePoints = LOSS.points;
                if (youPlayString.equals("Z")) outcomePoints = DRAW.points;
                break;
        }

        if (youPlayString.equals("X")) return outcomePoints + 1;
        else if (youPlayString.equals("Y")) return outcomePoints + 2;
        else return outcomePoints + 3;
    }
}
