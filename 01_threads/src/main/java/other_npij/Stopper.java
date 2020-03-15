package other_npij;

public class Stopper {

}

class StoppTask implements Runnable {
    // 'volatile': Force compiler to generate code
    //  such that values are read directly from RAM (not cache).
    private volatile Thread thisThread;
    private volatile boolean isStopped = false;

    public void stopRequest() {
        this.isStopped = true;
    }

    @Override
    public void run() {
        thisThread = Thread.currentThread();
        while (!isStopped) {
            // work
        }
    }
}
