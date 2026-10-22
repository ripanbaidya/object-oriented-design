package observer;

public class MobileApp implements StockObserver {

    @Override
    public void update(double price) {
        System.out.println("Mobile app: Stock price updated to: " + price);
    }
}
