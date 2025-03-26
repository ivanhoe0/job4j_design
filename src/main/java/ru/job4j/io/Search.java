package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        validateInput(args);
        Path start = Paths.get(args[0]);
        search(start, path -> path.toFile().getName().endsWith(args[1])).forEach(System.out::println);
        System.out.println(args[0]);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    private static void validateInput(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("There have to be two arguments");
        }
        File file1 = new File(args[0]);
        if (!file1.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file1.getAbsoluteFile()));
        }
        if (file1.isFile()) {
            throw new IllegalArgumentException(String.format("%s is not a directory", file1.getAbsoluteFile()));
        }

    }
}