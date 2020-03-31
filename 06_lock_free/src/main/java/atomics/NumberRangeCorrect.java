package atomics;

import java.util.concurrent.atomic.AtomicInteger;

public class NumberRangeCorrect {

    // INVARIANT: lower <= upper is NOT GUARANTEED
    private final AtomicInteger lower = new AtomicInteger(0);
    private final AtomicInteger upper = new AtomicInteger(0);

    public int getLower() {
        return lower.get();
    }

    public void setLower(int i) {
        while (true) {
            int l = lower.get();
            int u = upper.get();

            if (i > u)
                throw new IllegalArgumentException();

            //                      expected value, new value
            if (lower.compareAndSet(l,              i))
                return;
        }
    }

    public int getUpper() {
        return upper.get();
    }

    public void setUpper(int i) {
        while (true) {
            int l = lower.get();
            int u = upper.get();
            if (i < l)
                throw new IllegalArgumentException();

            // Is in 'u' still the value of 'upper'?
            if (upper.compareAndSet(u, i))
                return;
        }
    }

    public boolean contains(int i) {
        return lower.get() <= i && i <= upper.get();
    }
}
