package as.semaphore;

import java.util.LinkedList;
import java.util.List;


public class SemaphoreSol implements Semaphore {
    private int value;

    private final List<Thread> queue = new LinkedList<Thread>();

    public SemaphoreSol(int initial) {
        if (initial < 0) throw new IllegalArgumentException();
        value = initial;
    }



    public synchronized int available() {
        return value;
    }


    public synchronized void acquire () { // uninterruptibly
        queue.add(Thread.currentThread());
        while (value <= 0 || queue.get(0) != Thread.currentThread()) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        // value > 0 && queue.get(0) == Thread.currentThread()
        queue.remove(0);
        notifyAll(); // as queue changed
        value--;
    }

    public synchronized void release() {
        value++;
        notifyAll(); // all need to be woken up!
    }
}

