// файл: /services/InventoryService.java
package services;

import models.Car;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InventoryService {
    private static InventoryService instance;  // Singleton instance
    private Map<String, List<Car>> carInventory = new HashMap<>();

    private InventoryService() {
        addCars();
    }

    public static InventoryService getInstance() {
        if (instance == null) {
            instance = new InventoryService();
        }
        return instance;
    }

    private void addCars() {
        // Добавление автомобилей по маркам
        addCarsByBrand("Toyota", 10, 20000);
        addCarsByBrand("Ford", 10, 30000);
        addCarsByBrand("BMW", 10, 40000);
        addCarsByBrand("Mercedes", 10, 50000);
    }

    private void addCarsByBrand(String brand, int count, double price) {
        List<Car> cars = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            cars.add(new Car(i, brand + " Model " + i, price + i * 500));  // Уникальные цены для каждой машины
        }
        carInventory.put(brand, cars);
    }

    public List<String> getAvailableBrands() {
        return new ArrayList<>(carInventory.keySet());
    }

    public List<Car> getCarsByBrand(String brand) {
        return carInventory.getOrDefault(brand, new ArrayList<>());
    }

    public Car getCarById(String brand, int id) {
        return carInventory.getOrDefault(brand, new ArrayList<>())
                           .stream()
                           .filter(car -> car.getId() == id)
                           .findFirst()
                           .orElse(null);
    }

    public boolean isAvailable(Car car) {
        return carInventory.values().stream().anyMatch(list -> list.contains(car));
    }
}
