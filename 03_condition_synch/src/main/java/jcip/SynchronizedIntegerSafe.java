package jcip;

public class SynchronizedIntegerSafe implements SynchronizedInteger {
    private int value;

    public synchronized int get() {
        return this.value;
    }

    public synchronized void set(int v) {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) { }
        this.value = v;
    }
}
