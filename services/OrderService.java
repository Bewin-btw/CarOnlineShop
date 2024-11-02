// файл: /services/OrderService.java
package services;

import models.Car;
import models.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private List<Car> cart = new ArrayList<>();  // Локальная корзина для хранения добавленных автомобилей

    // Метод для добавления автомобиля в корзину
    public void addToCart(Car car) {
        cart.add(car);
        System.out.println("Автомобиль добавлен в корзину: " + car);
    }

    // Метод для оформления заказа, используя автомобили в корзине
    public Order createOrder() {
        Order order = new Order(new ArrayList<>(cart)); // Создаем заказ с автомобилями из корзины
        cart.clear(); // Очищаем корзину после оформления заказа
        System.out.println("Создан заказ: " + order);
        return order;
    }

    // Возвращает список автомобилей в корзине
    public List<Car> getCart() {
        return cart;
    }
}
