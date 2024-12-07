package vdg.aoc._2024;

import org.apache.commons.lang3.StringUtils;
import vdg.aoc.common.Util;

import java.util.*;
import java.util.stream.Collectors;

public class Day5 {


    public static void main(String[] args) {
        final List<String> input = Util.readFile("AOC/src/main/resources/_2024/Day5.txt");

        List<String> orderingRules = new ArrayList<>();
        List<List<Integer>> updateNumbers = new ArrayList<>();

        boolean switched = false;
        for (String line : input) {
            if (StringUtils.isBlank(line)) {
                switched = true;
                continue;
            }

            if (!switched) {
                orderingRules.add(line);
            } else {
                updateNumbers.add(Arrays.stream(line.split(",")).map(Integer::parseInt).collect(Collectors.toList()));
            }
        }

        part1(orderingRules, updateNumbers);
        part2(input);
    }

    private static void part1(List<String> orderingRules, List<List<Integer>> updateNumbers) {
        int sum = 0;

        try {
            Map<Integer, List<Integer>> rules = new HashMap<>();

            for (String line : orderingRules) {
                Integer[] split = Arrays.stream(line.split("\\|")).map(Integer::parseInt).toArray(Integer[]::new);
                if (rules.containsKey(split[0])) {
                    List<Integer> afters = new ArrayList<>(rules.get(split[0]));
                    afters.add(split[1]);
                    rules.put(split[0], afters);
                } else {
                    rules.put(split[0], List.of(split[1]));
                }
            }

            List<List<Integer>> correctUpdates = new ArrayList<>();

            for (List<Integer> update : updateNumbers) {
                List<Integer> pages = new ArrayList<>();
                boolean validUpdate = true;

                for (Integer page : update) {
                    final List<Integer> rule = rules.get(page);

                    if (rule != null && pages.stream().anyMatch(rule::contains)) {
                        validUpdate = false;
                    }

                    pages.add(page);
                }

                if (validUpdate) correctUpdates.add(pages);
            }

            sum = correctUpdates.stream().mapToInt(u -> u.get((u.size() - 1) / 2)).sum();

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("part 1 = " + sum);
    }

    private static void part2(final List<String> input) {
        int sum = 0;

        System.out.println("part 2 = " + sum);
    }
}