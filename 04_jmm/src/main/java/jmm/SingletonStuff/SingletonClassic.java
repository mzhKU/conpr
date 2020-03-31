package jmm.SingletonStuff;

// Correct under JMM and recommended if lazy initialization is not required.

public class SingletonClassic {
    private static SingletonClassic instance;

    public static SingletonClassic getInstance() {
        if (instance == null) {
            instance = new SingletonClassic();
        }

        return instance;
    }

    private SingletonClassic() { }
}
