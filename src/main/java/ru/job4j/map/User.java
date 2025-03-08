package ru.job4j.map;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        User user1 = new User("Ivan", 4, calendar);
        User user2 = new User("Ivan", 4, calendar);
        Map<User, Object> map = new HashMap<>(16);
        int hash1 = user1.hashCode();
        int hash2 = user2.hashCode();
        int bucket1 = hash1 ^ (hash1 >>> 16) & 15;
        int bucket2 = hash2 ^ (hash2 >> 16) & 15;
        map.put(user1, new Object());
        map.put(user2, new Object());
        for (var set : map.entrySet()) {
            System.out.println(set);
        }
        System.out.println(bucket1 == bucket2);
        System.out.println(hash1 == hash2);
    }
}
