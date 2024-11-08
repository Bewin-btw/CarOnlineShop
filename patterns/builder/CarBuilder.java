package patterns.builder;

import models.Car;

public class CarBuilder {
    private int id;
    private String model;
    private double price;
    private boolean sunroof;
    private boolean sportPackage;

    public CarBuilder(int id, String model, double price) {
        this.id = id;
        this.model = model;
        this.price = price;
    }

    public CarBuilder addSunroof() {
        this.sunroof = true;
        return this;
    }

    public CarBuilder addSportPackage() {
        this.sportPackage = true;
        return this;
    }

    public Car build() {
        Car car = new Car(id, model, price);
        car.setSunroof(sunroof);
        car.setSportPackage(sportPackage);
        return car;
    }
}
