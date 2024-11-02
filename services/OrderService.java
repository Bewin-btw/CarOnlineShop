// файл: /services/OrderService.java
package services;

import models.Car;
import models.Order;
import patterns.strategy.PricingStrategy;
import patterns.strategy.RegularPricingStrategy;

import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private List<Car> cart = new ArrayList<>();
    private InventoryService inventoryService = InventoryService.getInstance();

    // Установка стандартной стратегии ценообразования по умолчанию
    private PricingStrategy pricingStrategy = new RegularPricingStrategy();

    public void setPricingStrategy(PricingStrategy strategy) {
        this.pricingStrategy = strategy;
    }

    public void addToCart(Car car) {
        cart.add(car);
    }

    public Order createOrder() {
        Order order = new Order(new ArrayList<>(cart));

        // Удаление автомобилей из инвентаря после оформления заказа
        for (Car car : cart) {
            String brand = car.getModel().split(" ")[0];  // Предполагаем, что первая часть имени — марка
            inventoryService.removeCar(brand, car);
        }

        cart.clear();
        return order;
    }
}
