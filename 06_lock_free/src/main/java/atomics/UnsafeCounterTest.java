package atomics;

import java.util.concurrent.CyclicBarrier;

public class UnsafeCounterTest {

    static class T extends Thread {
        private UnsafeCounter c;
        private CyclicBarrier latch;

        public T(UnsafeCounter c, CyclicBarrier latch) {
            this.c = c;
            this.latch = latch;
        }

        public void run() {
            try { latch.await(); } catch (Exception e) { }
            for (int i = 0; i < 10_000_000; i++) {
                c.increment();
            }
        }
    }

    public static void main(String[] args) {
        CyclicBarrier latch = new CyclicBarrier(4);
        UnsafeCounter c = new UnsafeCounter();
        T t0 = new T(c, latch);
        T t1 = new T(c, latch);
        T t2 = new T(c, latch);
        T t3 = new T(c, latch);
        t0.start();
        t1.start();
        t2.start();
        t3.start();

        try {
            t0.join();
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
        }

        System.out.println(c.getValue());
    }
}
