package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    private File directory;

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
                for (var source : sources) {
                    ZipEntry file = new ZipEntry(new ZipEntry(source.toFile().getPath().substring(directory.getPath().length() + 1)));
                    zip.putNextEntry(file);
                    try (BufferedInputStream output = new BufferedInputStream(new FileInputStream(source.toFile()))) {
                        zip.write(output.readAllBytes());
                    }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getName()));
            try (BufferedInputStream output = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(output.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void validateInput(ArgsName names) {
        String directory = names.get("d");
        String output = names.get("o");
        String exclude = names.get("e");
        if (!Path.of(directory).toFile().exists()) {
            throw new IllegalArgumentException(String.format("There is no such directory '%s'", directory));
        }
        if (!exclude.startsWith(".") || exclude.length() == 1) {
            throw new IllegalArgumentException(String.format("Error: invalid format of extension '%s'", exclude));
        }
        if (!output.endsWith(".zip")) {
            throw new IllegalArgumentException(String.format("Error: invalid extension of '%s', it should be '.zip'", output));
        }
    }

    public static void main(String[] args) {
        ArgsName names = ArgsName.of(args);
       validateInput(names);
        Zip zip = new Zip();
        zip.directory = new File(names.get("d"));
        try {
            zip.packFiles(Search.search(Path.of(names.get("d")),
                    p -> !p.toFile().getName().endsWith(names.get("e"))),
                    Path.of(names.get("o")).toFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );
    }
}