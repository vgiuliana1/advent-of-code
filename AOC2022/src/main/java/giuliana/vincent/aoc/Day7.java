package giuliana.vincent.aoc;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day7 {
    public static void solution() {
        File inputFile = new File("AOC2022/src/main/resources/Day7-input.txt");
        try (Stream<String> lines = Files.lines(Paths.get(inputFile.getAbsolutePath()))) {

            Directory root = new Directory();
            Directory currentDirectory = root;
            Directory parentDirectory = null;

            for (String line : lines.collect(Collectors.toList())) {
                System.out.println(root);
                String[] lineParts = line.split(" ");
                String firstPart = lineParts[0];
                if (Objects.equals(firstPart, "$")) {
                    if (lineParts[1].equals("cd")) {
                        if (!lineParts[2].equals("..") && !lineParts[2].equals("/")) {
                            parentDirectory = currentDirectory;
                            currentDirectory = currentDirectory
                                    .getDirectories()
                                    .stream()
                                    .filter(d -> d.getDirectoryName().equals(lineParts[2]))
                                    .collect(Collectors.toList()).get(0);
                        } else if (lineParts[2].equals("..")) {
                            currentDirectory = parentDirectory;
                        }
                    }
                } else {
                    if (isDirectory(firstPart)) {
                        currentDirectory
                                .getDirectories()
                                .add(Directory.builder()
                                        .directoryName(lineParts[1])
                                        .directories(new ArrayList<>())
                                        .fileNamesAndSizes(new HashMap<>())
                                        .build());
                    } else {
                        currentDirectory
                                .getFileNamesAndSizes()
                                .putIfAbsent(lineParts[1], Integer.valueOf(lineParts[0]));
                    }
                }
            }
/*

            Directory d = new Directory();
            Map<String, Integer> d_files = new HashMap<>();
            d_files.put("j", 4060174);
            d_files.put("d.log", 8033020);
            d_files.put("d.ext", 5626152);
            d_files.put("k", 7214296);
            d.setFileNamesAndSizes(d_files);

            Directory e = new Directory();
            Map<String, Integer> e_files = new HashMap<>();
            e_files.put("i", 584);
            e.setFileNamesAndSizes(e_files);

            Directory a = new Directory();
            Map<String, Integer> a_files = new HashMap<>();
            a_files.put("f", 29116);
            a_files.put("g", 2557);
            a_files.put("h.lst", 62596);
            a.setFileNamesAndSizes(a_files);
            a.setDirectories(Collections.singletonList(e));

            Directory root2 = new Directory();
            root2.setDirectories(Arrays.asList(a, d));
            Map<String, Integer> root_files = new HashMap<>();
            root_files.put("b.txt", 14848514);
            root_files.put("c.dat", 8504156);
            root2.setFileNamesAndSizes(root_files);

            System.out.println(getDirectorySize(root2, 0));

 */
        } catch (IOException e) {
            System.out.println("Error:" + e.getMessage());
        }
    }

    public static int getDirectorySize(Directory directory, int sum) {
        if (directory.getDirectories().size() == 0) {
            int total = directory.getFileNamesAndSizes().values().stream().mapToInt(Integer::intValue).sum();
            return total > 100000 ? 0 : total;
        } else {
            for (Directory d : directory.getDirectories()) {
                sum += getDirectorySize(d, sum);
            }
        }
        int total = directory.getFileNamesAndSizes().values().stream().mapToInt(Integer::intValue).sum();

        return sum + (total > 100000 ? 0 : total);
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    private static class Directory {
        private String directoryName;
        private List<Directory> directories = new ArrayList<>();
        private Map<String, Integer> fileNamesAndSizes = new HashMap<>();
        private Directory parentDirectory;
    }

    private static boolean isDirectory(String s) {
        return !StringUtils.isNumeric(s);
    }
}