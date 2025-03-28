package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    private final List<String> log = new ArrayList<>();

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> answers = readPhrases();
        Scanner scanner = new Scanner(System.in);
        String in = scanner.next();
        boolean flag = true;
        Random random = new Random();
        int i = 0;
        log.add(in);
        while (!OUT.equals(in)) {
            if (STOP.equals(in)) {
                flag = false;
            }
            if (CONTINUE.equals(in)) {
                flag = true;
            }
            if (flag) {
                i = random.nextInt(0, answers.size());
                System.out.println(answers.get(i));
                log.add(answers.get(i));
            }
            in = scanner.next();
            log.add(in);
        }
        saveLog(log);
    }

    private List<String> readPhrases() {
        List<String> rsl = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(botAnswers))) {
            rsl = reader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(path))) {
            log.forEach(writer::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat consoleChat = new ConsoleChat("./data/ConsoleChatLog.txt", "./data/BotAnswers.txt");
        consoleChat.run();
    }
}