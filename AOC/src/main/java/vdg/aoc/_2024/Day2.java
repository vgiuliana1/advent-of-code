package vdg.aoc._2024;

import vdg.aoc.common.Util;

import java.util.*;
import java.util.stream.Collectors;

import static vdg.aoc.common.Util.between;

public class Day2 {

    public static void main(String[] args) {
        final List<String> input = Util.readFile("AOC/src/main/resources/_2024/Day2.txt");
        part1(input);
        part2(input);
    }

    private static void part1(final List<String> input) {
        int safeCount = 0;
        for (String l : input) {
            List<Integer> levels = Arrays.stream(l.split(" ")).map(Integer::parseInt).collect(Collectors.toList());

            safeCount += safe(levels) ? 1 : 0;

            System.out.println("Levels: " + levels + " = " + safe(levels));
        }

        System.out.println("part 1 = " + safeCount);
    }

    private static boolean safe(List<Integer> levels) {

        Boolean increasing = null;
        Boolean decreasing = null;

        for (int i = 0; i < levels.size() - 1; i++) {
            final int _f = levels.get(i);
            final int _s = levels.get(i + 1);
            final int _diff = _s - _f;

            if (Objects.equals(_f, _s)) return false;
            if (!between(1, Math.abs(_diff), 3, true)) return false;

            if (_diff > 0) increasing = true;
            if (_diff < 0) decreasing = true;

            if (increasing == decreasing) return false;
        }

        return true;
    }

    private static void part2(final List<String> input) {
        int safeCount = 0;
        for (String l : input) {
            int levelCount = l.split(" ").length;
            int levelSafeCount = 0;
            int levelUnsafeCount = 0;

            for (int i = 0; i < levelCount; i++) {
                List<Integer> levels = Arrays.stream(l.split(" ")).map(Integer::parseInt).collect(Collectors.toList());
                levels.remove(i);
                if (safe(levels)) levelSafeCount++;
                else levelUnsafeCount++;
            }

            if (levelSafeCount >= 1) {
                safeCount++;
                System.out.println(l + " SAFE");
            } else {
                System.out.println(l + " UNSAFE");
            }
        }

        System.out.println("part 2 = " + safeCount);
    }
}
