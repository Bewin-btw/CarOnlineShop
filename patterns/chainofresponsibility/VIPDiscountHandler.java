package patterns.chainofresponsibility;

import models.Car;

public class VIPDiscountHandler extends DiscountHandler {
    @Override
    public double applyDiscount(Car car, double price) {
        System.out.println("Applying VIP discount for car ID: " + car.getId());
        price *= 0.9;  // 10% VIP скидка
        return next != null ? next.applyDiscount(car, price) : price;
    }
}
