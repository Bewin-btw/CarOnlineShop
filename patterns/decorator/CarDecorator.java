// файл: /patterns/decorator/CarDecorator.java
package patterns.decorator;

import models.Car;

public abstract class CarDecorator extends Car {
    protected Car decoratedCar;

    public CarDecorator(Car car) {
        super(car.getId(), car.getModel(), car.getPrice());
        this.decoratedCar = car;
    }

    @Override
    public String getDescription() {
        return decoratedCar.getDescription();
    }
}
