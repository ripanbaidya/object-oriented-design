package singleton;

public class BillPughSingleton {

    private BillPughSingleton() {
    }

    // The static nested class is not loaded into memory until
    // getInstance() is called
    private static class SingletonHolder {
        private static final BillPughSingleton INSTANCE = new BillPughSingleton();
    }

    public static BillPughSingleton getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
