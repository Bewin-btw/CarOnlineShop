// файл: /patterns/strategy/RegularPricingStrategy.java
package patterns.strategy;

import models.Car;

public class RegularPricingStrategy implements PricingStrategy {
    @Override
    public double calculatePrice(Car car) {
        return car.getPrice();
    }
}
