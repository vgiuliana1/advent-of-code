package vdg.aoc.common;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Util {

    final static Map<String, String> NUMBERS = Map.of(
            "one", "1",
            "two", "2",
            "three", "3",
            "four", "4",
            "five", "5",
            "six", "6",
            "seven", "7",
            "eight", "8",
            "nine", "9"
    );

    public static List<String> readFile(final String path) {
        List<String> list = new ArrayList<>();

        try (BufferedReader br = Files.newBufferedReader(Paths.get(path))) {
            list = br.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static boolean isDigit(String s) {
        return s.matches("\\d");
    }

    public static String findFirstDigit(String line) {
        StringBuilder sb = new StringBuilder();
        String[] newLine = line.split("");

        for (String s : newLine) {
            if (isDigit(s)) {
                return s;
            } else {
                sb.append(s);
                if (NUMBERS.keySet().stream().anyMatch(d -> sb.toString().contains(d))) {
                    for (String k : NUMBERS.keySet()) {
                        if (sb.toString().contains(k))
                            return NUMBERS.get(k);
                    }
                }
            }
        }
        return "0";
    }

    public static String findLastDigit(String line) {
        StringBuilder sb = new StringBuilder();
        String[] newLine = StringUtils.reverse(line).split("");

        for (String s : newLine) {
            if (isDigit(s)) {
                return s;
            } else {
                sb.append(s);
                if (NUMBERS.keySet().stream().anyMatch(d -> sb.toString().contains(StringUtils.reverse(d)))) {
                    for (String k : NUMBERS.keySet()) {
                        if (sb.toString().contains(k))
                            return NUMBERS.get(k);
                    }
                }
            }
        }
        return "0";
    }
}