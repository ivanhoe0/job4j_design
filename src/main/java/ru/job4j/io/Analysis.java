package ru.job4j.io;

import java.io.*;

public class Analysis {
    public void unavailable(String source, String target) {
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
        PrintWriter writer = new PrintWriter(new FileOutputStream(target))
        ) {
           while ((line = reader.readLine()) != null) {
               if (line.startsWith(400 + "") || line.startsWith(500 + "")) {
                   writer.print(line.split(" ")[1] + ";");
                   do {
                       line = reader.readLine();
                   } while (line.startsWith(400 + "") || line.startsWith(500 + ""));
                   writer.println(line.split(" ")[1] + ";");
               }
           }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}