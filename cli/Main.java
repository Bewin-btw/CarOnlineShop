// файл: /cli/Main.java
package cli;

import models.*;
import services.*;
import patterns.command.*;
import patterns.observer.InventoryNotifier;
import patterns.strategy.*;

import java.util.InputMismatchException;
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
            String command = scanner.nextLine().trim();

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
        
        if (brands.isEmpty()) {
            System.out.println("Нет доступных автомобилей.");
            return;
        }

        System.out.println("Выберите марку автомобиля:");
        for (int i = 0; i < brands.size(); i++) {
            System.out.println((i + 1) + ". " + brands.get(i));
        }

        int brandChoice = getUserChoice(brands.size());
        if (brandChoice == -1) {
            System.out.println("Неверный выбор марки.");
            return;
        }

        String selectedBrand = brands.get(brandChoice - 1);
        displayCarsByBrand(selectedBrand);
    }

    private static void displayCarsByBrand(String brand) {
        System.out.println("Доступные автомобили марки " + brand + ":");
        List<Car> cars = inventoryService.getCarsByBrand(brand);
        
        if (cars.isEmpty()) {
            System.out.println("Нет доступных автомобилей для марки " + brand);
            return;
        }

        for (Car car : cars) {
            System.out.println("ID: " + car.getId() + " - " + car.getModel() + ", Цена: " + car.getPrice());
        }
    }

    private static void addToCart() {
        List<String> brands = inventoryService.getAvailableBrands();
        
        if (brands.isEmpty()) {
            System.out.println("Нет доступных автомобилей.");
            return;
        }

        System.out.println("Выберите марку автомобиля для добавления в корзину:");
        for (int i = 0; i < brands.size(); i++) {
            System.out.println((i + 1) + ". " + brands.get(i));
        }

        int brandChoice = getUserChoice(brands.size());
        if (brandChoice == -1) {
            System.out.println("Неверный выбор марки.");
            return;
        }

        String selectedBrand = brands.get(brandChoice - 1);
        List<Car> cars = inventoryService.getCarsByBrand(selectedBrand);

        System.out.println("Введите ID автомобиля для добавления в корзину:");
        for (Car car : cars) {
            System.out.println("ID: " + car.getId() + " - " + car.getModel() + ", Цена: " + car.getPrice());
        }

        int carId = getUserChoice(cars.size());
        if (carId == -1) {
            System.out.println("Неверный выбор ID автомобиля.");
            return;
        }

        Car car = inventoryService.getCarById(selectedBrand, carId);
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
        String method = scanner.nextLine().trim();
        if (paymentService.processPayment(method)) {
            System.out.println("Оплата прошла успешно.");
        } else {
            System.out.println("Оплата не удалась. Попробуйте другой метод.");
        }
    }

    private static void setPricingStrategy() {
        System.out.println("Выберите тип ценообразования: \n1. regular\n2. vip\n3. seasonal");
        String choice = scanner.nextLine().trim();
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

    private static int getUserChoice(int maxOption) {
        try {
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) return -1;
            
            int choice = Integer.parseInt(input);
            if (choice < 1 || choice > maxOption) {
                return -1;
            }
            return choice;
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
