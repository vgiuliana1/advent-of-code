package vdg.aoc._2024;

import vdg.aoc.common.Util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day1 {

    public static void main(String[] args) {
        final List<String> input = Util.readFile("AOC/src/main/resources/_2024/Day1.txt");
        part1(input);
        part2(input);
    }

    private static void part1(final List<String> input) {
        List<Integer> leftColumn = new ArrayList<>();
        List<Integer> rightColumn = new ArrayList<>();

        for (String l : input) {
            String[] inputs = l.split(" ");
            leftColumn.add(Integer.parseInt(inputs[0]));
            rightColumn.add(Integer.parseInt(inputs[3]));
        }

        Collections.sort(leftColumn);
        Collections.sort(rightColumn);

        int sum = 0;

        for (int i = 0; i < leftColumn.size(); i++) {
            sum += Math.abs(leftColumn.get(i) - rightColumn.get(i));
        }

        System.out.println("part 1 = " + sum);
    }

    private static void part2(final List<String> input) {
        List<Integer> leftColumn = new ArrayList<>();
        List<Integer> rightColumn = new ArrayList<>();

        for (String l : input) {
            String[] inputs = l.split(" ");
            leftColumn.add(Integer.parseInt(inputs[0]));
            rightColumn.add(Integer.parseInt(inputs[3]));
        }

        int sum = 0;
        for (int i = 0; i < leftColumn.size(); i++) {
            sum += leftColumn.get(i) * Collections.frequency(rightColumn, leftColumn.get(i));
        }

        System.out.println("part 2 = " + sum);
    }
}
