package npij;

public class Vol2 {

    public static volatile int x = 0;
    public static volatile int y = 0;

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 50; i++) {

            x = 0;
            y = 0;


            Thread t1 = new Thread(() -> {
                x = 5;
                doWork();
            });

            Thread t2 = new Thread(() -> {
                y = 6;
                doWork();
                System.out.println("x: " + x + ", y: " + y);
            });

            t1.start();
            t2.start();
            t1.join();
            t2.join();

        }
    }

    public static void doWork() {
        int s = 0;
        for (int i = 0; i < 1_000_000; i++) {
            s += i;
        }
    }
}
