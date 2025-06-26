package ru.job4j.cache;

import java.io.*;
import java.nio.file.Path;
import java.util.StringJoiner;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        var sj = new StringJoiner(System.lineSeparator());
        Path dir = Path.of(cachingDir);
        try (BufferedReader br = new BufferedReader(new FileReader(dir + "/" + key))) {
            var line = br.readLine();
            while (line != null) {
                sj.add(line);
                line = br.readLine();
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("Указанный файл не существует, просьба указать корректный файл");
        } catch (IOException e) {
            e.printStackTrace();
        }
        put(key, sj.toString());
        return sj.toString();
    }
}