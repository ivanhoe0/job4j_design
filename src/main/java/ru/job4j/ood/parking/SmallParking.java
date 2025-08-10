package ru.job4j.ood.parking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SmallParking implements Parking {

    private int lightParkingSize;

    private int truckParkingSize;
    private final List<Car> lightCarParking = new ArrayList<>(lightParkingSize);
    private final List<Car> truckParking = new ArrayList<>(truckParkingSize);

    public SmallParking(int lightParkingSize, int truckParkingSize) {
        this.lightParkingSize = lightParkingSize;
        this.truckParkingSize = truckParkingSize;
    }

    @Override
    public boolean allocateCar(Car car) {
        var size = car.getSize();
        var result = false;
        if (size > 1) {
            result = truckParking.add(car);
        }
        if (!result && size < lightParkingSize - lightCarParking.size()) {
            result = lightCarParking.add(car);
        }
        return result;
    }
}
