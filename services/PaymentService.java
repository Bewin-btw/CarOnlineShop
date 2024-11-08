package services;

public class PaymentService {
    public boolean processPayment(String method) {
        if ("credit".equalsIgnoreCase(method) || "paypal".equalsIgnoreCase(method)) {
            return true;
        }
        System.out.println("Метод оплаты не поддерживается.");
        return false;
    }
}
