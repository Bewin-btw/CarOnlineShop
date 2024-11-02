// файл: /patterns/command/AddToCartCommand.java
package patterns.command;

import models.Car;
import services.OrderService;

public class AddToCartCommand implements Command {
    private OrderService orderService;
    private Car car;

    public AddToCartCommand(OrderService orderService, Car car) {
        this.orderService = orderService;
        this.car = car;
    }

    @Override
    public void execute() {
        orderService.addToCart(car);
        System.out.println("Команда выполнена: добавлен автомобиль в корзину.");
    }
}
