// файл: /patterns/chainofresponsibility/SeasonalDiscountHandler.java
package patterns.chainofresponsibility;

import models.Car;

public class SeasonalDiscountHandler extends DiscountHandler {
    @Override
    public double applyDiscount(Car car, double price) {
        if (car.isSeasonalDiscountAvailable()) {
            System.out.println("Applying seasonal discount");
            price *= 0.85;  // 15% скидка
        }
        return next != null ? next.applyDiscount(car, price) : price;
    }
}
