package vdg.aoc._2024;

import vdg.aoc.common.Util;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day3 {
    final static private Pattern MULTIPLY_PATTERN = Pattern.compile("mul\\(\\d{1,3},\\d{1,3}\\)");

    public static void main(String[] args) {
        final List<String> input = Util.readFile("AOC/src/main/resources/_2024/Day3.txt");
        part1(input);
        part2(input);
    }

    private static void part1(final List<String> input) {
        int sum = 0;

        String fullInput = String.join("", input);

        Matcher matcher = MULTIPLY_PATTERN.matcher(fullInput);

        while(matcher.find()) {
            String mul = matcher.group();
            String numsToMultiply = mul.replace("mul(","").replace(")","");
            List<Integer> nums = Arrays.stream(numsToMultiply.split(",")).map(Integer::parseInt).collect(Collectors.toList());
            sum += Math.multiplyExact(nums.get(0), nums.get(1));
        }

        System.out.println("part 1 = " + sum);
    }

    private static void part2(final List<String> input) {
        int sum = 0;

        String fullInput = String.join("", input);

        Pattern doDontPattern = Pattern.compile("don't\\(\\).*?(do\\(\\)|$)");
        Matcher doDontMatcher = doDontPattern.matcher(fullInput);
        String sanitizedFullInput = doDontMatcher.replaceAll("");

        Matcher multiplyMatcher = MULTIPLY_PATTERN.matcher(sanitizedFullInput);

        while(multiplyMatcher.find()) {
            String mul = multiplyMatcher.group();
            String numsToMultiply = mul.replace("mul(","").replace(")","");
            List<Integer> nums = Arrays.stream(numsToMultiply.split(",")).map(Integer::parseInt).collect(Collectors.toList());
            sum += Math.multiplyExact(nums.get(0), nums.get(1));
        }

        System.out.println("part 2 = " + sum);
    }
}