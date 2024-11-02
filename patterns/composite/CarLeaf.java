// файл: /patterns/composite/CarLeaf.java
package patterns.composite;

import models.Car;

public class CarLeaf implements CarComponent {
    private Car car;

    public CarLeaf(Car car) {
        this.car = car;
    }

    @Override
    public void showDetails() {
        System.out.println(car);
    }
}
