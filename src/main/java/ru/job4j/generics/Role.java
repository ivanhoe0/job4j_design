package ru.job4j.generics;

public class Role extends Base {
    private final String roleName;

    public Role(String id, String roleName) {
        super(id);
        this.roleName = roleName;
    }

    public String getName() {
        return roleName;
    }
}
