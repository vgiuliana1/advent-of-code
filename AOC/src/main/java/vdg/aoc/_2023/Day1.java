package vdg.aoc._2023;

import vdg.aoc.common.Util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day1 {

    public static void main(String[] args) {
        final List<String> input = Util.readFile("AOC/src/main/resources/_2023/Day1.txt");
        part1(input);
        part2(input);
    }

    private static void part1(List<String> input) {
        int sum = 0;
        for (String s : input) {
            String[] separated = s.split("");
            List<String> digits = Arrays.stream(separated).filter(Util::isDigit).collect(Collectors.toList());
            final String first = digits.get(0);
            final String last = digits.get(digits.size() - 1);
            sum += Integer.parseInt(first + last);
        }
        // Correct answer: 55386
        // Failed attempts:
        System.out.println("part 1 = " + sum);
    }

    private static void part2(List<String> input) {
        int sum = 0;
        for (String s : input) {
            final String first = Util.findFirstDigit(s);
            final String last = Util.findLastDigit(s);
            sum += Integer.parseInt(first + last);
        }
        // Correct answer: 54824
        // Failed attempts: 54807
        System.out.println("part 2 = " + sum);
    }
}