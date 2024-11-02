// файл: /patterns/strategy/PricingStrategy.java
package patterns.strategy;

import models.Car;

public interface PricingStrategy {
    double calculatePrice(Car car);
}
