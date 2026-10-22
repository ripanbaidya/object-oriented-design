package observer;

import java.util.ArrayList;
import java.util.List;

public class Stock {

    private double price;
    private List<StockObserver> observers = new ArrayList<>();

    public void subscribe(StockObserver observer) {
        observers.add(observer);
    }

    public void unsubscribe(StockObserver observer) {
        observers.remove(observer);
    }

    public void setPrice(double price) {
        this.price = price;

        for (StockObserver observer : List.copyOf(observers)) {
            observer.update(this.price);
        }
    }
}
