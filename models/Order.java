package models;

import java.util.List;

public class Order {
    private List<Car> cars;

    public Order(List<Car> cars) {
        this.cars = cars;
    }

    public List<Car> getCars() {
        return cars;
    }

    @Override
    public String toString() {
        return "Order: " + cars.toString();
    }
}
