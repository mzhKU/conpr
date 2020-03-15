package jmm.test;

public class JMM1 {
    private static int value = 0;

    // - Mit Volatile: wenn ich etwas printe, dann wird es 77 sein,
    //   trotzdem kann auch T2 vorher ausfuehren und dann wird man immer
    //   noch 0 im value haben.
    // - Ohne volatile kann es sein dass nichts ausgegeben wird
    //   (ready ist noch immer false) oder 0 oder 77 sieht.
    private static volatile boolean ready = false;

    public static void main(String[] args) {
        new Thread("T1") {
            public void run() {
                value = 77;
                ready = true;
            }
        }.start();

        new Thread("T2") {
            public void run() {
                if (ready) {
                    System.out.println(value);
                }
            }
        }.start();
    }
}
