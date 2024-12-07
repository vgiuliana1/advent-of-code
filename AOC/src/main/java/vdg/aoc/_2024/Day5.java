package vdg.aoc._2024;

import org.apache.commons.lang3.StringUtils;
import vdg.aoc.common.Util;

import java.util.*;
import java.util.stream.Collectors;

public class Day5 {


    public static void main(String[] args) {
        final List<String> input = Util.readFile("AOC/src/main/resources/_2024/Day5.txt");

        List<String> orderingRules = new ArrayList<>();
        List<String> updateNumbers = new ArrayList<>();

        boolean switched = false;
        for (String line : input) {
            if (StringUtils.isBlank(line)) {
                switched = true;
                continue;
            }

            if (!switched) {
                orderingRules.add(line);
            } else {
                updateNumbers.add(line);
            }
        }

        part1(orderingRules, updateNumbers);
        part2(input);
    }

    private static void part1(List<String> orderingRules, List<String> updateNumbers) {
        int sum = 0;

        try {
            Map<String, Set<String>> pagesAndOrders = new HashMap<>();
            Set<String> allPageNumbers = new HashSet<>();

            for (String line : orderingRules) {
                String[] split = line.split("\\|");
                allPageNumbers.addAll(Arrays.asList(split));
                if (pagesAndOrders.containsKey(split[0])) {
                    Set<String> afters = new HashSet<>(pagesAndOrders.get(split[0]));
                    afters.add(split[1]);
                    pagesAndOrders.put(split[0], afters);
                } else {
                    pagesAndOrders.put(split[0], Set.of(split[1]));
                }
            }

            allPageNumbers.removeIf(pagesAndOrders::containsKey); // left with all page numbers that aren't before others
            if (!allPageNumbers.isEmpty())
                pagesAndOrders.put(new ArrayList<>(allPageNumbers).get(0), Collections.emptySet());

            List<String> pageOrder = pagesAndOrders
                    .entrySet()
                    .stream()
                    .sorted((f, s) -> Integer.compare(s.getValue().size(), f.getValue().size()))
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());
            System.out.println(pageOrder);

            final int numberOfPages = pageOrder.size();
            List<List<String>> correctUpdates = new ArrayList<>();

            for (String update : updateNumbers) {
                List<String> updatePages = Arrays.asList(update.split(","));
                List<String> pagesToCompare = new ArrayList<>(pageOrder);

                if (numberOfPages != updatePages.size()) {
                    pagesToCompare.removeIf(p -> !updatePages.contains(p));
                }

                if (pagesToCompare.equals(updatePages)) {
                    correctUpdates.add(updatePages);
                }
            }

            sum = correctUpdates.stream().mapToInt(u -> Integer.parseInt(u.get(Math.round((float) u.size() /2) - 1))).sum();

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