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
    private PricingStrategy pricingStrategy = new RegularPricingStrategy();

    public void setPricingStrategy(PricingStrategy pricingStrategy) {
        this.pricingStrategy = pricingStrategy;
    }

    public void addToCart(Car car) {
        cart.add(car);
    }

    public Order createOrder() {
        List<Car> discountedCars = new ArrayList<>();
        for (Car car : cart) {
            double discountedPrice = pricingStrategy.calculatePrice(car);
            discountedCars.add(new Car(car.getId(), car.getModel(), discountedPrice));
        }
        Order order = new Order(discountedCars);
        cart.clear();
        return order;
    }
}
