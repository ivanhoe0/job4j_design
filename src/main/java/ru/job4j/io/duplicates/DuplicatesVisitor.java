package ru.job4j.io.duplicates;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private Map<FileProperty, List<Path>> map = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file,
                                     BasicFileAttributes attributes) throws IOException {
        FileProperty fileProperty = new FileProperty(attributes.size(), file.toFile().getName());
        map.putIfAbsent(fileProperty, new ArrayList<>());
        map.get(fileProperty).add(file);
        return super.visitFile(file, attributes);
    }

    public void toConsole() {
        map.entrySet().stream()
                .filter(e -> e.getValue().size() > 1)
                .forEach((e) -> {
                    System.out.printf("%s - %sb\n", e.getKey().getName(), e.getKey().getSize());
                    e.getValue().forEach(p -> System.out.println(p.toFile().getAbsoluteFile()));
                });
    }
}