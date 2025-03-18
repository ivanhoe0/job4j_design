package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Analize {
    public static Info diff(Set<User> previous, Set<User> current) {
        Map<Integer, String> users = new HashMap<>();
        Info result = new Info(0, 0, 0);
        for (User u : previous) {
            users.put(u.getId(), u.getName());
        }
        for (User u : current) {
            if (!users.containsKey(u.getId())) {
                result.setAdded(result.getAdded() + 1);
                continue;
            }
            if (!Objects.equals(users.get(u.getId()), u.getName())) {
                result.setChanged(result.getChanged() + 1);
            }
        }
        result.setDeleted(previous.size() - current.size() + result.getAdded());
        return result;
    }
}
