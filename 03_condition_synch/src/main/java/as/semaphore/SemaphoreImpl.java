package as.semaphore;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public final class SemaphoreImpl implements Semaphore {
    private int value;

    Stack<Thread> stack;

    public SemaphoreImpl(int initial) {
        if (initial < 0) throw new IllegalArgumentException();
        value = initial;
        this.stack = new Stack<>();
    }

    @Override
    public synchronized int available() {
        return value;
    }

    @Override
    public synchronized void acquire() {
        stack.push(Thread.currentThread());
        while (this.value <= 0 || stack.get(0) != Thread.currentThread()) {
            try {
                Thread.currentThread().wait();
            } catch (InterruptedException e) { }
        }


        stack.pop();
        notifyAll();
        this.value--;
    }

    @Override
    public synchronized void release() {
        this.value++;
        notifyAll();
    }
}
