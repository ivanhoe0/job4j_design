package ru.job4j.algo.hash;

import java.util.HashMap;
import java.util.Map;

public class LongestUniqueSubstring {
    public static String longestUniqueSubstring(String str) {
        var result = "";
        var start = 0;
        var end = 0;
        var map = new HashMap<Character, Integer>();
        for (; end < str.length(); end++) {
            if (!map.containsKey(str.charAt(end)) || map.get(str.charAt(end)) < start) {
                map.put(str.charAt(end), end);
            } else {
                result = result.length() < end - start ? str.substring(start, end) : result;
                map.put(str.charAt(end), end);
                start = end;
            }
        }
        if (result.length() < end - start) {
            result = str.substring(start, end);
        }

        return result;
    }
}