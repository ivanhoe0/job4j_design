package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream output = socket.getOutputStream();
                     BufferedReader input = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String s = input.readLine();
                    Pattern p = Pattern.compile("=\\S+");
                    Matcher matcher = p.matcher(s);
                    matcher.find();
                    output.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    System.out.println(s);
                    switch (matcher.group().substring(1)) {
                        case "Hello" -> output.write("Hello".getBytes());
                        case "Exit" -> server.close();
                        default -> output.write("What".getBytes());
                    }
                    for (String string = input.readLine(); string != null && !string.isEmpty(); string = input.readLine()) {
                        System.out.println(string);
                    }
                    output.flush();
                }
            }
        }
    }
}