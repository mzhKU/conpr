package jcip;

public class SynchronizedIntegerUnsafe implements SynchronizedInteger {
    private int value;

    public int get() {
        return this.value;
    }

    public void set(int v) {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.value = v;
    }
}
