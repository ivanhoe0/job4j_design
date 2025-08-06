package ru.job4j.ood.productstore;

public class Trash extends AbstractStore {
    @Override
    public boolean add(Food food) {
        double remainder = calculateRemainder(food);
        if (remainder >= 1) {
            foodStore.add(food);
            return true;
        }
        return false;
    }
}
