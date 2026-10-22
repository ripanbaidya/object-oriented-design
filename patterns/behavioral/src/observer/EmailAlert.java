package observer;

public class EmailAlert implements StockObserver {

    @Override
    public void update(double price) {
        System.out.println("Email Alert: Stock price updated to: " + price);
    }
}
