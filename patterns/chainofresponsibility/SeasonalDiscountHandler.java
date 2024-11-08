package patterns.chainofresponsibility;

import models.Car;

public class SeasonalDiscountHandler extends DiscountHandler {
    @Override
    public double applyDiscount(Car car, double price) {
        System.out.println("Applying Seasonal discount for car ID: " + car.getId());
        price *= 0.95;  // 5% сезонная скидка
        return next != null ? next.applyDiscount(car, price) : price;
    }
}
