package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class Department {
    private boolean flag;
    private int size;
    private String title;

    private Division division;

    private String[] employees;

    public Department(boolean flag, int size, String title, Division division, String[] employees) {
        this.flag = flag;
        this.size = size;
        this.title = title;
        this.division = division;
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Department{"
                + "flag=" + flag + ", size="
                + size + ", title='"
                + title + '\'' + ", division=" + division
                + ", employees=" + Arrays.toString(employees) + '}';
    }

    public static void main(String[] args) {
        Department department = new Department(true, 35, "Отдел продаж", new Division("Отдел продаж г.Москва"),
                new String[] {"employee1", "employee2"}
                );
        Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(department));
        Department department2 = gson.fromJson(gson.toJson(department), Department.class);
        System.out.println(department2);

    }
}
