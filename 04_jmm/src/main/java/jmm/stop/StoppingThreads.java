package jmm.stop;

public class StoppingThreads {

    public static void main(String[] args) throws InterruptedException {

        StoppableThread t = new StoppableThread();

        System.out.println("starting thread t");
        t.start();
        Thread.sleep(1000);
        System.out.println("t.isRunning() = " + t.isRunning());

        // Setting 'running = false'.
        t.terminate();

        System.out.println("t.isRunning() = " + t.isRunning());

        t.join();
        System.out.println("DONE");
    }

}
