package ru.job4j.kiss.fool;

import java.util.Scanner;

public class Fool {

    public static void main(String[] args) {
        System.out.println("Игра FizzBuzz.");
        var startAt = 1;
        var input = new Scanner(System.in);
        while (startAt < 100) {
            System.out.println(check(startAt));
            startAt++;
            var answer = input.nextLine();
            if (!check(startAt).equals(answer)) {
                System.out.println("Ошибка. Начинай снова");
                startAt = 0;
            }
            startAt++;
        }
    }

    public static String check(int number) {
        if (number % 15 == 0) {
            return "FizzBuzz";
        } else if (number % 3 == 0) {
            return "Fizz";
        } else if (number % 5 == 0) {
            return "Buzz";
        } else {
            return Integer.valueOf(number).toString();
        }
    }
}
