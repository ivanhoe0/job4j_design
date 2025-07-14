package ru.job4j.ood.lsp;

public class Penguin extends Bird {
    @Override
    public void fly() {
        throw new RuntimeException("Penguins don't fly");
    }
}
