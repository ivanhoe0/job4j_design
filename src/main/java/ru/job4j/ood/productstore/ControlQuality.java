package ru.job4j.ood.productstore;

import java.util.ArrayList;
import java.util.List;

public class ControlQuality {
    private final List<Store> stores;

    public ControlQuality(List<Store> stores) {
        this.stores = stores;
    }

    public void allocateFood(Food food) {
        for (var store : stores) {
            if (store.add(food)) {
                break;
            }
        }
    }

    public void restore() {
        var foods = new ArrayList<Food>();
        stores.forEach(
                store -> foods.addAll(store.moveAllToList())
        );
        foods.forEach(this::allocateFood);
    }
}
