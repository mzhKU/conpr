package worksheet;

public final class Semaphore {
    private int value;
    private final Object lock = new Object();

    public Semaphore(int initial) {
        if (initial < 0) throw new IllegalArgumentException();
        value = initial;
    }

    public int available() {
        synchronized (lock) {
            return value;
        }
    }

    // Decrements ressource availability counter.
    public void acquire() {
        synchronized (lock) {
            while (this.value <= 0) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {}
            }
            this.value--;
            lock.notifyAll();
        }
    }

    // Increment ressource availability counter.
    public void release() {
        synchronized (lock) {
            this.value++;
            lock.notifyAll();
        }
    }

}
