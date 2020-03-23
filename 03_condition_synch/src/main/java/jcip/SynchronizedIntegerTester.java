package jcip;

public class SynchronizedIntegerTester {

    private static SynchronizedInteger si;

    public static void main(String[] args) throws InterruptedException {


        // --------------------------------------
        // Unsafe integer
        System.out.println("Unsafe");
        si = new SynchronizedIntegerUnsafe();
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) { }
            si.set(10);
        });

        Thread t2 = new Thread(() -> {
            System.out.println(si.get());
        });

        t1.start(); t2.start();
        t1.join(); t2.join();
        // --------------------------------------

        // --------------------------------------
        // Safe integer
        System.out.println("Safe");
        si = new SynchronizedIntegerSafe();
        Thread t3 = new Thread(() -> {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) { }
            si.set(10);
        });

        Thread t4 = new Thread(() -> {
            System.out.println(si.get());
        });

        t3.start(); t4.start();
        t3.join(); t4.join();
        // --------------------------------------

    }
}
