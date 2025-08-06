package ru.job4j.ood.productstore;

public class Shop extends AbstractStore {

    @Override
    public boolean add(Food food) {
        double remainder = calculateRemainder(food);
        if (remainder >= 0.25 && remainder < 0.75) {
            foodStore.add(food);
            return true;
        } else if (remainder >= 0.75 && remainder < 1) {
            food.setDiscount(20);
            foodStore.add(food);
            return true;
        }
        return false;
    }

}
