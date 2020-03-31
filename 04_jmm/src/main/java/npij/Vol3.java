package npij;

public class Vol3 {

    public static volatile int increments = 0;

    public static void main(String[] args) throws InterruptedException {

        for (int k = 0; k < 50; k++) {

            increments = 0;

            Thread t1 = new Thread(() -> {
                for (int i = 0; i < 200; i++) {
                    doWork();
                    increments++;
                }
            });

            Thread t2 = new Thread(() -> {
                for (int i = 0; i < 200; i++) {
                    doWork();
                    increments++;
                }
            });

            t1.start();
            t2.start();
            t1.join();
            t2.join();

            System.out.println("increments: " + increments);
        }
    }


    public static void doWork() {
        int s = 0;
        for (int i = 0; i < 1_000_000; i++) {
            s+=1;
        }
    }
}
