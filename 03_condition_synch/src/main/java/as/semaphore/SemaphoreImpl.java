package as.semaphore;

import java.util.ArrayList;
import java.util.List;

public final class SemaphoreImpl implements Semaphore {
    private int value;

    List<Thread> queue;

    public SemaphoreImpl(int initial) {
        if (initial < 0) throw new IllegalArgumentException();
        value = initial;
        this.queue = new ArrayList<>();
    }

    @Override
    public int available() {
        return value;
    }

    @Override
    public synchronized void acquire() {
        while (this.value <= 0) {
            try {
                Thread t = Thread.currentThread();
                queue.add(t);
                wait();
            } catch (InterruptedException e) { }
        }
        this.value--;
        notifyAll();
    }

    @Override
    public void release() {

    }
}
