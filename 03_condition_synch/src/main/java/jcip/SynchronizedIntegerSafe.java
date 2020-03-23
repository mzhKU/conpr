package jcip;

public class SynchronizedIntegerSafe implements SynchronizedInteger {
    private int value;

    public synchronized int get() {
        return this.value;
    }

    public synchronized void set(int v) {
        this.value = v;
    }
}
