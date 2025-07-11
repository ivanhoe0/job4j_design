package ru.job4j.ood.ocp;

public class AreaCalculator {
    public double calculateArea(Object shape) {
        double result = 0;
        if (shape instanceof Circle) {
            result = Math.PI * Math.pow(((Circle) shape).getRadius(), 2);
        }
        return result;
    }

    private static class Circle {
        double radius;

        public double getRadius() {
            return radius;
        }

    }
}
