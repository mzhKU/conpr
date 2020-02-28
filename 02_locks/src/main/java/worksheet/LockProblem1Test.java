package worksheet;

import org.junit.Test;

public class LockProblem1Test {

    @Test
    public void add() throws InterruptedException {
        LockProblem1 l1 = new LockProblem1();
        Adder a = new Adder(l1);
        Thread t1 = new Thread(a);
        Thread t2 = new Thread(a);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(l1.get());
        // assertTrue(l1.get() == null);
    }

    @Test
    public void remove() {
    }

    @Test
    public void get() {
    }
}

class Adder implements Runnable {
    private LockProblem1 p1;
    public Adder(LockProblem1 p1) { this.p1 = p1; }
    @Override
    public void run() {
        for (int i = 0; i < 1000_000; i++) {
            p1.add("a");
            p1.remove(p1.get());
        }
    }
}