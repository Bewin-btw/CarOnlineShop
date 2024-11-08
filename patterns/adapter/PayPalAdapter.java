package patterns.adapter;

public class PayPalAdapter implements PaymentAdapter {
    public boolean processPayment(double amount) {
        System.out.println("Processing PayPal payment of $" + amount);
        return true;
    }
}
