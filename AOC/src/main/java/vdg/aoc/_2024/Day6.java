package vdg.aoc._2024;

import vdg.aoc.common.Util;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class Day6 {

    final static char OBSTACLE = '#';
    static int LENGTH = 0;
    static int ROWS = 0;

    public static void main(String[] args) {
        final List<String> input = Util.readFile("AOC/src/main/resources/_2024/Day6.txt");
        ROWS = input.size();
        LENGTH = input.get(0).length();
        final char[][] labMap = new char[ROWS][LENGTH];

        Point start = new Point(0, 0);

        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.get(i).length(); j++) {
                labMap[i][j] = input.get(i).charAt(j);
                if (input.get(i).charAt(j) == '^') {
                    start = new Point(i, j); // new Point(row, column)
                }
            }
        }

        part1(labMap, start);
        part2(input);
    }

    private static void part1(char[][] labMap, Point point) {

        boolean goingUp = true;
        boolean goingRight = false;
        boolean goingDown = false;
        boolean exited = false;

        while (!exited) {
            if (goingUp) {
                int col = point.y;
                for (int row = point.x; row >= 0; row--) {
                    labMap[row][col] = 'X';
                    try {
                        if (labMap[row - 1][col] == OBSTACLE) {
                            goingUp = false;
                            goingRight = true;
                            point = new Point(row, col + 1);
                            break;
                        }
                    } catch (Exception e) {
                        exited = true;
                        break;
                    }
                }
            } else if (goingRight) {
                int row = point.x;
                for (int col = point.y; col <= LENGTH; col++) {
                    labMap[row][col] = 'X';
                    try {
                        if (labMap[row][col + 1] == OBSTACLE) {
                            goingRight = false;
                            goingDown = true;
                            point = new Point(row + 1, col);
                            break;
                        }
                    } catch (Exception e) {
                        exited = true;
                        break;
                    }
                }
            } else if (goingDown) {
                int col = point.y;
                for (int row = point.x; row <= ROWS; row++) {
                    labMap[row][col] = 'X';
                    try {
                        if (labMap[row + 1][col] == OBSTACLE) {
                            goingDown = false;
                            point = new Point(row, col - 1);
                            break;
                        }
                    } catch (Exception e) {
                        exited = true;
                        break;
                    }
                }
            } else {
                int row = point.x;
                for (int col = point.y; col >= 0; col--) {
                    labMap[row][col] = 'X';
                    try {
                        if (labMap[row][col - 1] == OBSTACLE) {
                            goingUp = true;
                            point = new Point(row - 1, col);
                            break;
                        }
                    } catch (Exception e) {
                        exited = true;
                        break;
                    }
                }
            }
        }

        Arrays.stream(labMap).forEach(System.out::println);
        final long sum = Arrays.stream(labMap).flatMapToInt(r -> new String(r).chars()).filter(c -> c == 'X').count();

        System.out.println("part 1 = " + sum);
    }

    private static void part2(List<String> input) {
        int sum = 0;

        System.out.println("part 2 = " + sum);

    }
}