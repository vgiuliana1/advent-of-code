package vdg.aoc._2024;

import java.util.Objects;
import java.util.Optional;

public class Day5Node {

    String data;
    Day5Node left;
    Day5Node right;

    public Day5Node(String data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    public static Optional<Day5Node> pageNumberExists(final String pageNumber, final Day5Node rootNode) {
        if (Objects.equals(rootNode.data, pageNumber)) return Optional.of(rootNode);
        else if (Integer.parseInt(pageNumber) < Integer.parseInt(rootNode.data)) return pageNumberExists(pageNumber, rootNode.left);
        else if (Integer.parseInt(pageNumber) > Integer.parseInt(rootNode.data)) return pageNumberExists(pageNumber, rootNode.right);
        else return Optional.empty();
    }
}
