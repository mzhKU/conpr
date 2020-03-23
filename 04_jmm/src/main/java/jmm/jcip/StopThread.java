package jmm.jcip;

public class StopThread {
    private static volatile boolean stopRequested;


    public static void main(String[] args) throws InterruptedException {

        Thread backgroundThread = new Thread(() -> {
            int i = 0;
            while (!stopRequested) {
                i++;
            }
        });
        backgroundThread.start();


        Thread.sleep(1000);
        stopRequested = true;
    }


}
