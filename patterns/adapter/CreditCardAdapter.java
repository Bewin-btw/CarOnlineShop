package patterns.adapter;

public class CreditCardAdapter implements PaymentAdapter {
    public boolean processPayment(double amount) {
        System.out.println("Processing Credit Card payment of $" + amount);
        return true;
    }
}
