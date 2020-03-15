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
    public int available() {
        return value;
    }

    @Override
    public synchronized void acquire() {
        while (this.value <= 0) {
            try {
                stack.push(Thread.currentThread());
                Thread.currentThread().wait();
            } catch (InterruptedException e) { }
        }
        this.value--;
    }

    @Override
    public synchronized void release() {
        this.value++;
    }
}
