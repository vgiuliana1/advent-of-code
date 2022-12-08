package giuliana.vincent.aoc;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day5 {

    public static void solution() {
        File inputFile = new File("AOC2022/src/main/resources/Day5-input.txt");
        try (Stream<String> lines = Files.lines(Paths.get(inputFile.getAbsolutePath()))) {
            List<String> input = lines.collect(Collectors.toList());
            List<String> crates = new ArrayList<>();
            int iter = 0;

            while (input.get(iter).contains("[")) {
                crates.add(input.get(iter));
                iter++;
            }

            String[] noOfStacks = input.get(iter).split(" ");
            int numberOfStacks = Integer.parseInt(noOfStacks[noOfStacks.length - 1]);

            List<String> instructions = input.subList(iter + 2, input.size());
            List<Deque<String>> stackList = createStackedCratesList(numberOfStacks, crates);

            for (String instruction : instructions) {
                String[] movement = instruction.split(" ");
                int moveNumber = Integer.parseInt(movement[1]);
                int moveFrom = Integer.parseInt(movement[3]);
                int moveTo = Integer.parseInt(movement[5]);

                for (int i = 0; i < moveNumber; i++) {
                    stackList.get(moveTo - 1).add(stackList.get(moveFrom - 1).getLast());
                    stackList.get(moveFrom - 1).removeLast();
                }
            }

            StringBuilder sb = new StringBuilder();

            for (Deque<String> stack : stackList) {
                sb.append(stack.getLast());
            }

            System.out.println("Crate list (Part 1): " + sb);

        } catch (IOException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    private static List<Deque<String>> createStackedCratesList(final int numberOfStacks, final List<String> crates) {
        List<Deque<String>> crateStacksList = new ArrayList<>();
        for (int i = 0; i < numberOfStacks; i++)
            crateStacksList.add(new ArrayDeque<>());

        for (String row : crates) {
            for (int i = 0; i <= row.length() / 4; i++) {
                if (Character.isLetter(row.charAt(i * 4 + 1))) {
                    crateStacksList.get(i).addFirst(String.valueOf(row.charAt(i * 4 + 1)));
                }
            }
        }

        return crateStacksList;
    }
}