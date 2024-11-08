package patterns.strategy;

import models.Car;

public class SeasonalDiscountStrategy implements PricingStrategy {
    @Override
    public double calculatePrice(Car car) {
        return car.getPrice() * 0.85; // 15% сезонная скидка
    }
}
