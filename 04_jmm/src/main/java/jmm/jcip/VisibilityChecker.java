package jmm.jcip;

public class VisibilityChecker {

    // State shared by threads
    int y;
    int x;

    // Object lock = new Object()

    class Threader extends Thread {
        @Override
        public void run() {
            burnCycles();
            y = 1;
            x = 1;
        }
    }


    public static void main(String[] args) throws Exception {
        VisibilityChecker v = new VisibilityChecker();
        Thread t1 = v.new Threader();
        Thread t2 = v.new Threader();
    }




    static void burnCycles() {
        int CYCLES = 1_000_000_000;
        int m = 0;
        for (int i = 0; i< CYCLES; i++) {
            m++;
        }
    }
}
