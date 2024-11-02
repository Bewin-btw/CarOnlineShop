// файл: /patterns/decorator/SportPackageDecorator.java
package patterns.decorator;

import models.Car;

public class SportPackageDecorator extends CarDecorator {
    public SportPackageDecorator(Car car) {
        super(car);
    }

    @Override
    public String getDescription() {
        return decoratedCar.getDescription() + ", with Sport Package";
    }
}
