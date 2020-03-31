package jmm.SingletonStuff;

public class SingletonEager {

    private static SingletonEager instance = new SingletonEager();

    public static SingletonEager getInstance() {
        return instance;
    }

    private SingletonEager() { }
}
