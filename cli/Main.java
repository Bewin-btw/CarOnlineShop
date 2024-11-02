// файл: /cli/Main.java
package cli;

import models.*;
import services.*;
import patterns.command.*;
import patterns.observer.InventoryNotifier;
import patterns.observer.Observer;
import patterns.strategy.*;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final InventoryService inventoryService = InventoryService.getInstance();
    private static final OrderService orderService = new OrderService();
    private static final PaymentService paymentService = new PaymentService();
    private static final InventoryNotifier notifier = new InventoryNotifier(inventoryService);
    private static final User currentUser = new User("Иван");

    public static void main(String[] args) {
        System.out.println("Добро пожаловать в онлайн-магазин автомобилей!");
        notifier.addObserver(currentUser);

        boolean isRunning = true;
        while (isRunning) {
            System.out.println("\nВведите команду или выберите номер: \n1. catalog\n2. add-to-cart\n3. checkout\n4. pay\n5. set-pricing\n6. subscribe\n7. exit");
            String command = scanner.nextLine();

            switch (command) {
                case "1":
                case "catalog":
                    selectBrandAndDisplayCatalog();
                    break;
                case "2":
                case "add-to-cart":
                    addToCart();
                    break;
                case "3":
                case "checkout":
                    checkout();
                    break;
                case "4":
                case "pay":
                    pay();
                    break;
                case "5":
                case "set-pricing":
                    setPricingStrategy();
                    break;
                case "6":
                case "subscribe":
                    subscribeToInventoryNotifications();
                    break;
                case "7":
                case "exit":
                    isRunning = false;
                    break;
                default:
                    System.out.println("Неизвестная команда. Попробуйте снова.");
            }
        }
    }

    private static void selectBrandAndDisplayCatalog() {
        List<String> brands = inventoryService.getAvailableBrands();
        System.out.println("Выберите марку автомобиля:");
        for (int i = 0; i < brands.size(); i++) {
            System.out.println((i + 1) + ". " + brands.get(i));
        }

        int brandChoice = Integer.parseInt(scanner.nextLine()) - 1;
        if (brandChoice >= 0 && brandChoice < brands.size()) {
            String selectedBrand = brands.get(brandChoice);
            displayCarsByBrand(selectedBrand);
        } else {
            System.out.println("Неверный выбор марки.");
        }
    }

    private static void displayCarsByBrand(String brand) {
        System.out.println("Доступные автомобили марки " + brand + ":");
        List<Car> cars = inventoryService.getCarsByBrand(brand);
        for (Car car : cars) {
            System.out.println("ID: " + car.getId() + " - " + car.getModel() + ", Цена: " + car.getPrice());
        }
    }

    private static void addToCart() {
        System.out.println("Введите марку автомобиля:");
        String brand = scanner.nextLine();
        
        System.out.println("Введите ID автомобиля:");
        int carId = Integer.parseInt(scanner.nextLine());

        Car car = inventoryService.getCarById(brand, carId);
        if (car != null) {
            Command addToCartCommand = new AddToCartCommand(orderService, car);
            addToCartCommand.execute();
            notifier.checkAvailability(car);
        } else {
            System.out.println("Автомобиль с таким ID не найден.");
        }
    }

    private static void checkout() {
        Order order = orderService.createOrder();
        System.out.println("Заказ оформлен: " + order);
    }

    private static void pay() {
        System.out.println("Выберите метод оплаты (credit/paypal): ");
        String method = scanner.nextLine();
        if (paymentService.processPayment(method)) {
            System.out.println("Оплата прошла успешно.");
        } else {
            System.out.println("Оплата не удалась. Попробуйте другой метод.");
        }
    }

    private static void setPricingStrategy() {
        System.out.println("Выберите тип ценообразования: \n1. regular\n2. vip\n3. seasonal");
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
            case "regular":
                orderService.setPricingStrategy(new RegularPricingStrategy());
                System.out.println("Установлена обычная стратегия ценообразования.");
                break;
            case "2":
            case "vip":
                orderService.setPricingStrategy(new VIPPricingStrategy());
                System.out.println("Установлена стратегия ценообразования для VIP клиентов.");
                break;
            case "3":
            case "seasonal":
                orderService.setPricingStrategy(new SeasonalDiscountStrategy());
                System.out.println("Установлена сезонная стратегия ценообразования.");
                break;
            default:
                System.out.println("Неверный выбор стратегии.");
        }
    }

    private static void subscribeToInventoryNotifications() {
        System.out.println("Вы подписаны на уведомления о наличии автомобилей.");
    }
}
