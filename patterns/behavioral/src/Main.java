import observer.EmailAlert;
import observer.MobileApp;
import observer.Stock;
import observer.StockObserver;
import state.VendingMachine;
import strategy.CardPayment;
import strategy.PayPalPayment;
import strategy.PaymentService;
import strategy.PaymentStrategy;

public class Main {
    static void main() {
        // testStrategy();
        // testObserver();
        // testState();
    }

    private static void testStrategy() {
        PaymentStrategy paypalPaymentStrategy = new PayPalPayment();
        PaymentService service = new PaymentService(paypalPaymentStrategy);
        service.checkout(1000);

        PaymentStrategy cardPaymentStrategy = new CardPayment();
        service = new PaymentService(cardPaymentStrategy);
        service.checkout(1000);
    }

    private static void testObserver() {
        Stock stock = new Stock();

        StockObserver emailAlert = new EmailAlert();
        StockObserver mobileApp = new MobileApp();

        stock.subscribe(emailAlert);
        stock.subscribe(mobileApp);

        stock.setPrice(100.0);

        stock.unsubscribe(emailAlert);

        stock.setPrice(150.0);
    }

    private static void testState() {
        VendingMachine machine = new VendingMachine();

        machine.selectProduct();
        machine.insertMoney();
        machine.selectProduct();
        machine.insertMoney();
    }
}
