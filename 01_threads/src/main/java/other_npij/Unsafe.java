package other_npij;

public class Unsafe {
    int commonData;

    Object o;

    public synchronized void inc() {
        commonData++;
    }
}

class UnsafeDriver {

    public static void main(String[] args) {

        Unsafe unsafeData = new Unsafe();
        unsafeData.commonData = 0;

        Thread t1 = new Thread(() -> {
            // Thread.sleep(2000);
            unsafeData.inc();
            System.out.println("T1: " + unsafeData.commonData);
        });

        Thread t2 = new Thread(() -> {
            System.out.println("T2: " + unsafeData.commonData);
            unsafeData.inc();
            System.out.printf("T2: %d%n", unsafeData.commonData);
        });

        t1.start();
        t2.start();
    }
}
