package strategy;

public class PaymentService {

    private final PaymentStrategy strategy;

    public PaymentService(PaymentStrategy paymentStrategy) {
        this.strategy = paymentStrategy;
    }

    public void checkout(double amount) {
        strategy.pay(amount);
    }
}
