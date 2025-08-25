package ru.job4j.ood.productstore;

import java.util.List;

public interface Store {
    boolean add(Food food);

    List<Food> moveAllToList();
}