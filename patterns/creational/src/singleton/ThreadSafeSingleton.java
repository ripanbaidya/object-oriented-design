package singleton;

public class ThreadSafeSingleton {

    private static ThreadSafeSingleton instance = null;

    private ThreadSafeSingleton() {
    }

    /**
     * The 'synchronized' keyword prevents multiple threads from being accessing this method.
     * It's thread-safe but introduces severe performance overhead.
     */
    public static synchronized ThreadSafeSingleton getInstance() {
        if (instance == null) {
            instance = new ThreadSafeSingleton();
        }
        return instance;
    }
}
