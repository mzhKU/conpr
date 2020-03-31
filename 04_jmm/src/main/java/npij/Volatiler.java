package npij;

import java.util.HashSet;
import java.util.Set;

public class Volatiler {

    static          int a = 0;
    static volatile int b = 0;

    public static void main(String[] args) throws InterruptedException {



        // --------------------------------------------------------------
        Thread t1 = new Thread(() -> {
            // try { Thread.sleep(1000); } catch (InterruptedException e) { }
            a = 3;
            b = 2;
        });
        // --------------------------------------------------------------




        // --------------------------------------------------------------
        Thread t2 = new Thread(() -> {
            // System.out.println(b + " " + a);  // Should return '2 3'.
            System.out.println(a + " " + b);     // Can also return '0 0'.
        });
        // --------------------------------------------------------------




        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}
