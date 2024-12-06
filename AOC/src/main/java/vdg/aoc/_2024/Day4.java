package vdg.aoc._2024;

import vdg.aoc.common.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day4 {

    public static int ROW_LENGTH;
    public static int NUMBER_ROWS;
    public static String XMAS = "XMAS";
    public static String SAMX = "SAMX";

    public static void main(String[] args) {
        final List<String> input = Util.readFile("AOC/src/main/resources/_2024/Day4.txt");

        part1(input); // wrong: 2190 (too low)
        part2(input);
    }

    private static void part1(List<String> crossword) {
        ROW_LENGTH = crossword.get(0).length();
        NUMBER_ROWS = crossword.size();
        int sum = 0;

        // stream through crossword and count the number of times XMAS and SAMX appears in each string
        sum += crossword.stream().mapToInt(s -> countOccurrences(s, XMAS)).sum();
        sum += crossword.stream().mapToInt(s -> countOccurrences(s, SAMX)).sum();

        char[][] _cRotatedCrossword = new char[NUMBER_ROWS][ROW_LENGTH];
        for (int i = 0; i < ROW_LENGTH; i++) {
            for (int j = 0; j < NUMBER_ROWS; j++) {
                _cRotatedCrossword[j][ROW_LENGTH - 1 - i] = crossword.get(i).charAt(j);
            }
        }
        List<String> rotatedCrossword = Arrays.stream(_cRotatedCrossword).map(String::valueOf).collect(Collectors.toList());
        sum += rotatedCrossword.stream().mapToInt(s -> countOccurrences(s, XMAS)).sum();
        sum += rotatedCrossword.stream().mapToInt(s -> countOccurrences(s, SAMX)).sum();

        char[][] inputMatrix = new char[NUMBER_ROWS][ROW_LENGTH];

        for (int i = 0; i < NUMBER_ROWS; i++) {
            for (int j = 0; j < ROW_LENGTH; j++) {
                inputMatrix[i][j] = crossword.get(i).charAt(j);
            }
        }

        /* BOTTOM LEFT TO TOP RIGHT */
        List<String> ltr_diagonals = new ArrayList<>();
        for (int row = NUMBER_ROWS - 1; row >= 0; row--) {
            ltr_diagonals.add(bottomRight(inputMatrix, row, 0, ""));
        }

        for (int col = 1; col < ROW_LENGTH; col++) {
            ltr_diagonals.add(bottomRight(inputMatrix, 0, col, ""));
        }

        System.out.println("diagonals1 = " + ltr_diagonals);

        sum += ltr_diagonals.stream().mapToInt(s -> countOccurrences(s, XMAS)).sum();
        sum += ltr_diagonals.stream().mapToInt(s -> countOccurrences(s, SAMX)).sum();


        /* BOTTOM RIGHT TO TOP LEFT */
        List<String> rtl_diagonals = new ArrayList<>();
        for (int row = NUMBER_ROWS - 1; row >= 0; row--) {
            rtl_diagonals.add(bottomLeft(inputMatrix, row, ROW_LENGTH - 1, ""));
        }

        for (int col = ROW_LENGTH - 1; col > 0; col--) {
            rtl_diagonals.add(bottomLeft(inputMatrix, 0, col - 1, ""));
        }

        System.out.println("diagonals2 = " + rtl_diagonals);

        sum += rtl_diagonals.stream().mapToInt(s -> countOccurrences(s, XMAS)).sum();
        sum += rtl_diagonals.stream().mapToInt(s -> countOccurrences(s, SAMX)).sum();

        System.out.println("part 1 = " + sum);
    }

    private static void part2(final List<String> input) {
        int sum = 0;

        System.out.println("part 2 = " + sum);
    }

    private static int countOccurrences(final String s, final String w) {
        int lastIndex = 0;
        int count = 0;

        while ((lastIndex = s.indexOf(w, lastIndex)) != -1) {
            count++;
            lastIndex += w.length();
        }

        return count;
    }

    private static String bottomRight(char[][] crossword, final int row, final int col, String str) {
        try {
            return bottomRight(crossword, row + 1, col + 1, str + crossword[row][col]);
        } catch (Exception e) {
            return str;
        }
    }

    private static String bottomLeft(char[][] crossword, final int row, final int col, String str) {
        try {
            return bottomLeft(crossword, row + 1, col - 1, str + crossword[row][col]);
        } catch (Exception e) {
            return str;
        }
    }
}