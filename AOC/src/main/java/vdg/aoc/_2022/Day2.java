package vdg.aoc._2022;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static vdg.aoc._2022.Day2.Outcome.*;

public class Day2 {

    enum Outcome {
        WIN(6, "Z"),
        LOSS(0, "X"),
        DRAW(3, "Y");

        private final int points;
        private final String outcomeString;

        Outcome(final int points, final String outcome) {
            this.points = points;
            this.outcomeString = outcome;
        }
    }

    enum Shape {
        ROCK(1, "A"),
        PAPER(2, "B"),
        SCISSORS(3, "C");

        final int points;
        final String letter;

        Shape(final int points, final String letter) {
            this.points = points;
            this.letter = letter;
        }
    }

    public static void solution() {
        File inputFile = new File("AOC2022/src/main/resources/Day2-input.txt");
        try (Stream<String> lines = Files.lines(Paths.get(inputFile.getAbsolutePath()))) {

            int totalScoreP1 = 0;
            int totalScoreP2 = 0;

            for (String line : lines.collect(Collectors.toList())) {
                String[] plays = line.split(" ");

                totalScoreP1 += calculateRoundPart1(plays[0], plays[1]);
                totalScoreP2 += calculateRoundPart2(plays[0], plays[1]);
            }

            System.out.println("Total score (part 1): " + totalScoreP1);
            System.out.println("Total score (part 2): " + totalScoreP2);

        } catch (IOException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    private static int calculateRoundPart1(final String opponentPlayString, final String youPlayString) {
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

    private static int calculateRoundPart2(final String opponentPlayString, final String outcomeString) {
        int outcomePoints = 0;
        Shape youPlayString = null;

        switch (outcomeString) {
            case "X": // lose
                if (opponentPlayString.equals(Shape.ROCK.letter)) youPlayString = Shape.SCISSORS;
                if (opponentPlayString.equals(Shape.PAPER.letter)) youPlayString = Shape.ROCK;
                if (opponentPlayString.equals(Shape.SCISSORS.letter)) youPlayString = Shape.PAPER;
                break;
            case "Y": // draw
                outcomePoints = 3;
                if (opponentPlayString.equals(Shape.ROCK.letter)) youPlayString = Shape.ROCK;
                if (opponentPlayString.equals(Shape.PAPER.letter)) youPlayString = Shape.PAPER;
                if (opponentPlayString.equals(Shape.SCISSORS.letter)) youPlayString = Shape.SCISSORS;
                break;
            case "Z": // win
                outcomePoints = 6;
                if (opponentPlayString.equals(Shape.ROCK.letter)) youPlayString = Shape.PAPER;
                if (opponentPlayString.equals(Shape.PAPER.letter)) youPlayString = Shape.SCISSORS;
                if (opponentPlayString.equals(Shape.SCISSORS.letter)) youPlayString = Shape.ROCK;
                break;
            default:
                throw new RuntimeException("Invalid 'outcomeString'");
        }

        return outcomePoints + youPlayString.points;
    }
}