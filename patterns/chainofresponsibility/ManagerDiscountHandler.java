package patterns.chainofresponsibility;

import models.Car;

public class ManagerDiscountHandler extends DiscountHandler {
    @Override
    public double applyDiscount(Car car, double price) {
        System.out.println("Applying Manager discount for car ID: " + car.getId());
        price *= 0.97;  // 3% скидка от менеджера
        return next != null ? next.applyDiscount(car, price) : price;
    }
}
