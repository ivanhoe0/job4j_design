package ru.job4j.ood.parking;

public class SmallParking implements Parking {
    @Override
    public boolean allocateCar(Car car) {
        return false;
    }
}
