// файл: /patterns/observer/InventoryNotifier.java
package patterns.observer;

import models.Car;
import services.InventoryService;

public class InventoryNotifier extends Subject {
    private InventoryService inventoryService;

    public InventoryNotifier(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    public void checkAvailability(Car car) {
        if (inventoryService.isAvailable(car)) {
            notifyObservers("Автомобиль " + car.getModel() + " доступен в наличии!");
        }
    }
}
