package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;

public class Multiple {
    public static void main(String[] args) {
        var s = "";
        try (FileOutputStream output = new FileOutputStream("data/multipleResult.txt")) {
            for (int i = 1; i < 10; i++) {
                for (int j = 1; j < 10; j++) {
                    s = i + " * " + j + " = " + j * i;
                    output.write(s.getBytes());
                    output.write(System.lineSeparator().getBytes());
                }
                output.write(System.lineSeparator().getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
