package other_npij;


import java.util.Random;

class Task implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i<1000_000; i++) {
            if (i%100000 == 0) {
                System.out.println("Thread: " + this + "i: " + i);
                Thread sub = new Thread(() -> {
                    for (int k = 0; k < 1000_000; k++) {
                        if (k%100000 == 0) {
                            Random r = new Random();
                            System.out.println("Sub-" + k + ": " + r.nextInt());
                        }
                    }
                });
                sub.start();
            }
        }
    }
}


public class Tasker {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (int i = 0; i<4; i++) {
            Thread t = new Thread(new Task());
            t.start();
            System.out.println("Thread " + t.getName() + " is alive: " + t.isAlive());
        }
        long end = System.currentTimeMillis();
        long duration = end - start;
        System.out.println("Duration: " + duration);
    }
}
