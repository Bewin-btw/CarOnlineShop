// файл: /patterns/factory/CarFactory.java
package patterns.factory;

import models.Car;

public class CarFactory {
    public static Car createCar(String brand, int id, double price) {
        switch (brand) {
            case "Toyota":
                return new Car(id, "Toyota Model " + id, price);
            case "Ford":
                return new Car(id, "Ford Model " + id, price);
            case "BMW":
                return new Car(id, "BMW Model " + id, price);
            case "Mercedes":
                return new Car(id, "Mercedes Model " + id, price);
            default:
                throw new IllegalArgumentException("Unknown car brand: " + brand);
        }
    }
}
