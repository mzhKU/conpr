package jmm.SingletonStuff;

public class SingletonDoubleCheck {
    private static SingletonDoubleCheck instance;

    public static SingletonDoubleCheck getInstance() {
        if (instance == null) {
            // Statischen lock auf Klasse kann es nur einmal geben.
            synchronized (SingletonDoubleCheck.class) {
                if (instance == null) {
                    instance = new SingletonDoubleCheck();
                }
            }
        }
        return instance;
    }

    private SingletonDoubleCheck() { }
}
