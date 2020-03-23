package jcip;

public class SynchronizedIntegerUnsafe implements SynchronizedInteger {
    private int value;

    public int get() {
        return this.value;
    }

    public void set(int v) {
        this.value = v;
    }
}
