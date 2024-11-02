// файл: /patterns/strategy/VIPPricingStrategy.java
package patterns.strategy;

import models.Car;

public class VIPPricingStrategy implements PricingStrategy {
    @Override
    public double calculatePrice(Car car) {
        return car.getPrice() * 0.9; // 10% скидка для VIP
    }
}
