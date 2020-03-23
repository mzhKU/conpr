package as.peterson;

public class Main {
    private static Mutex pm;
    private static int v = 0;

    public static void main(String[] args) {
        Thread t0 = new Thread(r, "Thread-0");
        Thread t1 = new Thread(r, "Thread-1");
        pm = new PetersonMutex(t0, t1);
        t0.start();
        t1.start();
    }

    private static Runnable r = () -> {
        while (true) {
            criticalSection();
        }
    };

    private static void criticalSection() {
        pm.lock();
        System.out.println(pm);
        v++;
        sleep(500);
        // Should always print 1
        System.out.println(">> " + v + " " + Thread.currentThread().getName());
        sleep(500);
        v--;
        pm.unlock();
    }

    private static void sleep(int millis) {
        try { Thread.sleep(millis); } catch (InterruptedException e) { }
    }
}
