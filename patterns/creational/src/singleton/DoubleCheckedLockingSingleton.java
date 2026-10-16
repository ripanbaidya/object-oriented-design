package singleton;

public class DoubleCheckedLockingSingleton {

    /**
     * The 'volatile' keyword ensures changes to this variable are immediately
     * visible across threads and prevents local instruction reordering during
     * optimization phases
     */
    private static volatile DoubleCheckedLockingSingleton instance = null;

    private DoubleCheckedLockingSingleton() {
    }

    public static DoubleCheckedLockingSingleton getInstance() {
        // First check: Executed without locking to optimize execution flow
        if (instance == null) {
            // Synchronize on the class monitor block to manage contention
            synchronized (DoubleCheckedLockingSingleton.class) {
                // Second check: Verifies no other thread initialized the instance
                // while waiting for the lock
                if (instance == null) {
                    instance = new DoubleCheckedLockingSingleton();
                }
            }
        }

        return instance;
    }
}
