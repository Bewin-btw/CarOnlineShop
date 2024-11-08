package patterns.chainofresponsibility;

import models.Car;

public abstract class DiscountHandler {
    protected DiscountHandler next;

    public void setNext(DiscountHandler next) {
        this.next = next;
    }

    public abstract double applyDiscount(Car car, double price);
}
