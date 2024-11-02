// файл: /patterns/chainofresponsibility/ManagerDiscountHandler.java
package patterns.chainofresponsibility;

import models.Car;

public class ManagerDiscountHandler extends DiscountHandler {
    @Override
    public double applyDiscount(Car car, double price) {
        if (car.isManagerApproved()) {
            System.out.println("Applying manager discount");
            price *= 0.95;  // 5% скидка
        }
        return next != null ? next.applyDiscount(car, price) : price;
    }
}
