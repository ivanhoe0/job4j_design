package ru.job4j.ood.productstore;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStore implements Store {
    protected final List<Food> foodStore = new ArrayList<>();

    protected double calculateRemainder(Food food) {
        long createDate = food.getCreateDate().getTimeInMillis();
        long expireDate = food.getExpireDate().getTimeInMillis();
        long now = System.currentTimeMillis();
        return (double) (now - createDate) / (expireDate - createDate);
    }

    public Food get(int index) {
        return foodStore.get(index);
    }

}
