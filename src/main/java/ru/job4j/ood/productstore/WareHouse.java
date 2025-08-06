package ru.job4j.ood.productstore;

public class WareHouse extends AbstractStore {
    @Override
    public boolean add(Food food) {
        double remainder = calculateRemainder(food);
        if (remainder < 0.25) {
            foodStore.add(food);
            return true;
        }
        return false;
    }
}
