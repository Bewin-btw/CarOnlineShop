// файл: /patterns/decorator/SunroofDecorator.java
package patterns.decorator;

import models.Car;

public class SunroofDecorator extends CarDecorator {
    public SunroofDecorator(Car car) {
        super(car);
    }

    @Override
    public String getDescription() {
        return decoratedCar.getDescription() + ", with Sunroof";
    }
}
