// файл: /cli/Main.java
package cli;

import patterns.state.SoldState;
import patterns.state.ReservedState;
import models.Car;
import patterns.builder.CarBuilder;
import patterns.chainofresponsibility.*;
import patterns.decorator.SportPackageDecorator;
import patterns.decorator.SunroofDecorator;
import patterns.facade.CarShopFacade;
import patterns.adapter.CreditCardAdapter;
import patterns.adapter.PayPalAdapter;
import patterns.adapter.PaymentAdapter;
import services.InventoryService;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final CarShopFacade carShopFacade = new CarShopFacade();
    private static final InventoryService inventoryService = InventoryService.getInstance();

    public static void main(String[] args) {
        System.out.println("Добро пожаловать в онлайн-магазин автомобилей!");

        boolean isRunning = true;
        while (isRunning) {
            System.out.println("\nВыберите команду или введите номер:");
            System.out.println("1. catalog\n2. add-to-cart\n3. view-cart\n4. checkout\n5. set-discounts\n6. add-options\n7. pay\n8. exit");
            String command = scanner.nextLine().trim();
        
            switch (command) {
                case "1":
                case "catalog":
                    carShopFacade.showCatalog();
                    break;
                case "2":
                case "add-to-cart":
                    addToCart();
                    break;
                case "3":
                case "view-cart":
                    viewCart();
                    break;
                case "4":
                case "checkout":
                    checkout();
                    break;
                case "5":
                case "set-discounts":
                    applyDiscounts();
                    break;
                case "6":
                case "add-options":
                    addCarOptions();
                    break;
                case "7":
                case "pay":
                    pay();
                    break;
                case "8":
                case "exit":
                    isRunning = false;
                    break;
                default:
                    System.out.println("Неверная команда. Попробуйте снова.");
            }
        }
    }

    private static void addToCart() {
        System.out.println("Введите марку автомобиля для добавления в корзину:");
        String brand = scanner.nextLine().trim();

        System.out.println("Введите ID автомобиля:");
        int carId = Integer.parseInt(scanner.nextLine().trim());

        Car car = inventoryService.getCarById(brand, carId);

        // Проверяем состояние автомобиля
        if (car == null) {
            System.out.println("Автомобиль уже куплен.");
            return;
        } else if (car.getState() instanceof SoldState) {
            System.out.println("Автомобиль уже куплен.");
            return;
        } else if (car.getState() instanceof ReservedState) {
            System.out.println("Автомобиль зарезервирован.");
            return;
        }

        // Если доступен, добавляем в корзину через фасад
        carShopFacade.addToCart(car);
        car.setState(new ReservedState()); // Изменяем состояние на Зарезервировано
    }




    private static void checkout() {
        System.out.println("Оформление заказа...");

        for (Car car : carShopFacade.getCart()) {
            inventoryService.removeCar(car.getModel().split(" ")[0], car);  // Удаляем из инвентаря
            car.setState(new SoldState()); // Изменяем состояние на Продано
        }
        carShopFacade.checkout();  // Завершение оформления заказа через фасад
        System.out.println("Заказ успешно оформлен. Товары удалены из каталога.");
    }

    private static void viewCart() {
        System.out.println("Ваши автомобили в корзине:");
        if (carShopFacade.getCart().isEmpty()) {
            System.out.println("Корзина пуста.");
        } else {
            for (Car car : carShopFacade.getCart()) {
                System.out.println("ID: " + car.getId() + " - " + car.getDescription() + ", Цена: $" + car.getPrice());
            }
        }
    }

    private static void applyDiscounts() {
        System.out.println("Выберите скидки для применения:");
        System.out.println("1. VIP Скидка\n2. Сезонная Скидка\n3. Скидка от Менеджера");
    
        DiscountHandler vipDiscountHandler = new VIPDiscountHandler();
        DiscountHandler seasonalDiscountHandler = new SeasonalDiscountHandler();
        DiscountHandler managerDiscountHandler = new ManagerDiscountHandler();
    
        // Настраиваем цепочку в зависимости от выбора пользователя
        System.out.println("Введите номера скидок, которые хотите применить (например, 1 2):");
        String[] chosenDiscounts = scanner.nextLine().trim().split("\\s+");
    
        for (String discount : chosenDiscounts) {
            switch (discount) {
                case "1":
                    vipDiscountHandler.setNext(seasonalDiscountHandler);
                    break;
                case "2":
                    seasonalDiscountHandler.setNext(managerDiscountHandler);
                    break;
                case "3":
                    managerDiscountHandler.setNext(null);
                    break;
                default:
                    System.out.println("Некорректный выбор.");
            }
        }
    
        System.out.println("Применение скидок к автомобилям в корзине...");
        for (Car car : carShopFacade.getCart()) {
            double originalPrice = car.getPrice();
            double discountedPrice = vipDiscountHandler.applyDiscount(car, originalPrice);
            System.out.println("ID: " + car.getId() + " - Старая цена: $" + originalPrice + ", Новая цена: $" + discountedPrice);
        }
    }

    private static void addCarOptions() {
        System.out.println("Выберите ID автомобиля в корзине для добавления опций:");
        int carId = Integer.parseInt(scanner.nextLine().trim());
        Car car = carShopFacade.getCarByIdFromCart(carId);

        if (car == null) {
            System.out.println("Автомобиль не найден в корзине.");
            return;
        }

        System.out.println("Выберите опцию для добавления:");
        System.out.println("1. Sunroof\n2. Sport Package");
        int option = Integer.parseInt(scanner.nextLine().trim());

        switch (option) {
            case 1:
                car = new SunroofDecorator(car);
                System.out.println("Добавлена опция: Sunroof.");
                break;
            case 2:
                car = new SportPackageDecorator(car);
                System.out.println("Добавлена опция: Sport Package.");
                break;
            default:
                System.out.println("Неверный выбор опции.");
                return;
        }

        carShopFacade.updateCarInCart(car);  // Обновляем автомобиль в корзине с новой опцией
    }

    private static void pay() {
        System.out.println("Выберите способ оплаты:\n1. PayPal\n2. Credit Card");
        int paymentMethod = Integer.parseInt(scanner.nextLine().trim());

        PaymentAdapter paymentAdapter;
        switch (paymentMethod) {
            case 1:
                paymentAdapter = new PayPalAdapter();
                break;
            case 2:
                paymentAdapter = new CreditCardAdapter();
                break;
            default:
                System.out.println("Неверный выбор метода оплаты.");
                return;
        }

        double totalAmount = carShopFacade.calculateTotalPrice();
        if (paymentAdapter.processPayment(totalAmount)) {
            System.out.println("Оплата прошла успешно.");
        } else {
            System.out.println("Ошибка при оплате. Попробуйте снова.");
        }
    }
}
