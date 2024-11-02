// файл: /patterns/facade/CarShopFacade.java
package patterns.facade;

import models.Car;
import services.InventoryService;
import services.OrderService;

import java.util.ArrayList;
import java.util.List;

public class CarShopFacade {
    private InventoryService inventoryService = InventoryService.getInstance();
    private OrderService orderService = new OrderService();

    // Показ каталога автомобилей с группировкой по маркам
    public void showCatalog() {
        inventoryService.getAvailableBrands().forEach(brand -> {
            System.out.println("Марка: " + brand);
            inventoryService.getCarsByBrand(brand).forEach(System.out::println);
        });
    }

    // Добавление автомобиля в корзину
    public void buyCar(String brand, int id) {
        Car car = inventoryService.getCarById(brand, id);
        if (car != null) {
            orderService.addToCart(car);  // Добавляем автомобиль в корзину напрямую через OrderService
        } else {
            System.out.println("Автомобиль не найден.");
        }
    }

    // Получение всех автомобилей в корзине
    public List<Car> getCart() {
        return orderService.getCart();  // Получаем корзину из OrderService
    }

    // Оформление заказа
    public void checkout() {
        if (orderService.getCart().isEmpty()) {
            System.out.println("Корзина пуста. Добавьте автомобили для оформления заказа.");
            return;
        }

        orderService.createOrder();  // Оформляем заказ без передачи списка автомобилей
        System.out.println("Заказ успешно оформлен.");
    }

    // Получение автомобиля из корзины по ID
    public Car getCarByIdFromCart(int carId) {
        return orderService.getCart().stream()
                .filter(car -> car.getId() == carId)
                .findFirst()
                .orElse(null);
    }

    // Обновление автомобиля в корзине
    public void updateCarInCart(Car updatedCar) {
        List<Car> cart = orderService.getCart();
        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i).getId() == updatedCar.getId()) {
                cart.set(i, updatedCar);
                System.out.println("Автомобиль обновлен в корзине: " + updatedCar);
                return;
            }
        }
        System.out.println("Автомобиль не найден в корзине.");
    }

    // Расчет общей стоимости всех автомобилей в корзине
    public double calculateTotalPrice() {
        return orderService.getCart().stream()
                .mapToDouble(Car::getPrice)
                .sum();
    }
}
