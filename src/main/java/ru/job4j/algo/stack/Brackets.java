package ru.job4j.algo.stack;

import java.util.Stack;

public class Brackets {
    public boolean isValid(String s) {
        var stack = new Stack<Character>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else if (!stack.empty() && (c == stack.peek() + 2 ||  c == stack.peek() + 1)) {
                stack.pop();
            } else {
                return false;
            }
        }
        return stack.empty();
    }
}
