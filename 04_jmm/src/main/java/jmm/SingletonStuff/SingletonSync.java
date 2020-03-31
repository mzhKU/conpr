package jmm.SingletonStuff;

public class SingletonSync {

    private static SingletonSync instance;

    public synchronized static SingletonSync getInstance() {
        if (instance == null) {
            instance = new SingletonSync();
        }
        return instance;
    }

    private SingletonSync() { }

}
