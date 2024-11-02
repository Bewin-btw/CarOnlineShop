// файл: /patterns/composite/CarBrandComposite.java
package patterns.composite;

import java.util.ArrayList;
import java.util.List;

public class CarBrandComposite implements CarComponent {
    private String brand;
    private List<CarComponent> cars = new ArrayList<>();

    public CarBrandComposite(String brand) {
        this.brand = brand;
    }

    public void addCar(CarComponent car) {
        cars.add(car);
    }

    @Override
    public void showDetails() {
        System.out.println("Brand: " + brand);
        for (CarComponent car : cars) {
            car.showDetails();
        }
    }
}
