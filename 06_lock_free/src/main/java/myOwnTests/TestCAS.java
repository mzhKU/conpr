package myOwnTests;

import java.util.concurrent.atomic.AtomicInteger;

public class TestCAS {

    private volatile AtomicInteger value = new AtomicInteger(0);

    public int getValue() {
        return this.value.get();
    }

    public void increment() {
        int current = value.get();
        int next    = current + 1;

        if (value.compareAndSet(current, next)) {
            return;
        }
    }
}
