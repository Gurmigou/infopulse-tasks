package hometask_2.Task_1_singleton;

public class ConcurrentSingleton {
    private static volatile ConcurrentSingleton singleton; // чтобы JMV не переставляла процесс создания объекта

    private ConcurrentSingleton() {}

    public static ConcurrentSingleton getInstance() {
        if (singleton == null) {
            synchronized (ConcurrentSingleton.class) {
                if (singleton == null)
                    singleton = new ConcurrentSingleton();
            }
        }
        return singleton;
    }
}
