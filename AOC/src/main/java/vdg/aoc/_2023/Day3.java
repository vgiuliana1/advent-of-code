package vdg.aoc._2023;

import org.apache.commons.lang3.StringUtils;
import vdg.aoc.common.Util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

public class Day3 {

    final private static String SYMBOLS = "@#$%&*+-=/";

    public static void main(String[] args) {
        final String[] input = Util.readFileArray("AOC/src/main/resources/_2023/Day3.txt");
        part1(input);
        part2(input);
    }

    private static void part1(String[] input) {
        int sum = 0;
        Set<Integer> symbolPlacements = new HashSet<>();
        for (String s : input) {
            final String[] lineArray = s.split("");
            for (String l : lineArray) {
                Stream.of(s.split("")).forEach(c -> {
                    if (SYMBOLS.contains(c))
                        symbolPlacements.add(l.indexOf(c));
                });
            }
        }


        // Correct answer:
        // Failed attempts:
        System.out.println("part 1 = " + sum);
    }

    private static void part2(String[] input) {
        int sum = 0;
        // Correct answer:
        // Failed attempts:
        System.out.println("part 2 = " + sum);
    }
}