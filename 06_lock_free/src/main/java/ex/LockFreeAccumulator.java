package ex;

import java.util.concurrent.atomic.AtomicReference;

public class LockFreeAccumulator {
    private int total = 0;
    private int lastValue = 0;

    private AtomicReference<Result> values = new
            AtomicReference<>(new Result(0, 0));

    public synchronized void update(int newValue) {
        total += newValue;
        lastValue = newValue;
    }

    public synchronized Result get() {
        // return new Result(total, lastValue);
        return values.get();
    }
}
