package ru.job4j.ood.parking;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
@Disabled
class SmallParkingTest {
    @Test
    void whenThereIsNoPlaceInTruckParkingButThereIsPlaceInLightParking() {
        Parking parking = new SmallParking(4, 3);
        Car truck1 = new Truck(2);
        Car truck2 = new Truck(2);
        Car truck3 = new Truck(3);
        parking.allocateCar(truck1);
        parking.allocateCar(truck2);
        assertThat(parking.allocateCar(truck3)).isTrue();
    }

    @Test
    void whenThereIsNoPlaceForLightCar() {
        Parking parking = new SmallParking(5, 2);
        Car truck1 = new Truck(2);
        Car truck2 = new Truck(2);
        Car lightCar1 = new LightCar();
        Car lightCar2 = new LightCar();
        Car lightCar3 = new LightCar();
        Car lightCar4 = new LightCar();
        Car lightCar5 = new LightCar();
        Car lightCar6 = new LightCar();
        parking.allocateCar(truck1);
        parking.allocateCar(truck2);
        parking.allocateCar(lightCar1);
        parking.allocateCar(lightCar2);
        parking.allocateCar(lightCar3);
        parking.allocateCar(lightCar4);
        parking.allocateCar(lightCar5);
        assertThat(parking.allocateCar(lightCar6)).isFalse();
    }
}