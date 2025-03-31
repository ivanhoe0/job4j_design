package ru.job4j.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        File source = new File(argsName.get("path"));
        String delimiter = argsName.get("delimiter");
        List<String> filter = new ArrayList<>(Arrays.asList(argsName.get("filter").split(",")));
        List<List<String>> array = new ArrayList<>();
        int i = 0;
        try (var scanner = new Scanner(source)) {
            while (scanner.hasNextLine()) {
                try (var rowScanner = new Scanner(scanner.nextLine()).useDelimiter(delimiter)) {
                    while (rowScanner.hasNext()) {
                        if (array.size() == i) {
                            array.add(new ArrayList<>());
                        }
                        array.get(i++).add(rowScanner.next());
                    }
                    i = 0;
                }
            }
        }
        array.sort(new Comparator<List<String>>() {
            @Override
            public int compare(List<String> o1, List<String> o2) {
                return Integer.compare(
                        filter.indexOf(o1.get(0)), filter.indexOf(o2.get(0))
                );
            }
        });
        List<StringJoiner> line = new ArrayList<>();
        for (int column = 0; column < array.get(0).size(); column++) {
            line.add(new StringJoiner(delimiter));
            for (var row : array) {
                if (filter.contains(row.get(0))) {
                    line.get(column).add(row.get(column));
                }
            }
        }
        if ("stdout".equals(argsName.get("out"))) {
            line.forEach(l -> System.out.println(l.toString()));
        } else {
            try (PrintWriter writer = new PrintWriter(new FileWriter(argsName.get("out")))) {
                line.forEach(l -> writer.println(l.toString()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void validateInput(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }
        for (var s : args) {
            if (!s.startsWith("-")) {
                throw new IllegalArgumentException(String.format("Error: This argument '%s' does not start with a '-' character", s));
            }
            if (!s.contains("=")) {
                throw new IllegalArgumentException(String.format("Error: This argument '%s' does not contain an equal sign", s));
            }
            String[] str = s.substring(1).split("=", 2);
            if (str[0].isEmpty()) {
                throw new IllegalArgumentException(String.format("Error: This argument '%s' does not contain a key", s));
            }
            if (str[1].isEmpty()) {
                throw new IllegalArgumentException(String.format("Error: This argument '%s' does not contain a value", s));
            }
        }
    }

    public static void main(String[] args) {
        validateInput(args);
        ArgsName argsName = ArgsName.of(args);
        try {
            handle(argsName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}