package ru.job4j.ood.srp;

public interface Order {
    int getTotal();

    void getProducts();

    void saveToRepository();
}
