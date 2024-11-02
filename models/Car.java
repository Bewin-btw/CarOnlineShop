// файл: /models/Car.java
package models;

public class Car {
    private int id;
    private String model;
    private double price;
    
    public Car(int id, String model, double price) {
        this.id = id;
        this.model = model;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Model: " + model + ", Price: " + price;
    }
}
