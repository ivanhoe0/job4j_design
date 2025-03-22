package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        String line = "";
        try (BufferedReader reader = new BufferedReader(
                new FileReader(path)
        )) {
           while ((line = reader.readLine()) != null) {
               if (line.startsWith("#") || line.isEmpty()) {
                   continue;
               }
               String[] keyValuePair = line.split("=", 2);
               if (keyValuePair.length <= 1 || keyValuePair[0].isEmpty()
               || keyValuePair[1].isEmpty()
               ) {
                   throw new IllegalArgumentException();
               }
               values.put(keyValuePair[0], keyValuePair[1]);
           }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner output = new StringJoiner(System.lineSeparator());
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            reader.lines().forEach(output::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("data/app.properties"));
        String ex = "saewga=";
        System.out.println(ex.split("=").length);
    }

}