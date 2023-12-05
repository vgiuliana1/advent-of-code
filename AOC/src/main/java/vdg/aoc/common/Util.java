package vdg.aoc.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Util {

    final static List<String> DIGIT_WORDS = Arrays.asList("one", "two", "three", "four", "five", "six", "seven", "eight", "nine");
    final static List<String> NUMBER_WORDS = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9");

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

    public static String replaceDigitWordsWithNumbers(String line) {
        StringBuilder sb = new StringBuilder();
        String[] newLine = line.split("");

        for (String s : newLine) {
            sb.append(s);
            StringBuilder finalSb = sb;
            if (DIGIT_WORDS.stream().anyMatch(d -> finalSb.toString().contains(d))) {
                String sbReplaced = sb.toString();
                for (int i = 0; i <= 8; i++) {
                    sbReplaced = sbReplaced.replace(DIGIT_WORDS.get(i), NUMBER_WORDS.get(i));
                }
                sb = new StringBuilder(sbReplaced);
            }
        }
        return sb.toString();
    }
}