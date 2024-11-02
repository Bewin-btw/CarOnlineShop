// файл: /patterns/chainofresponsibility/VIPDiscountHandler.java
package patterns.chainofresponsibility;

import models.Car;

public class VIPDiscountHandler extends DiscountHandler {
    @Override
    public double applyDiscount(Car car, double price) {
        if (car.isVip()) {
            System.out.println("Applying VIP discount");
            price *= 0.9;  // 10% скидка
        }
        return next != null ? next.applyDiscount(car, price) : price;
    }
}
