package jmm.stop;

class StoppableThread extends Thread {

    // 'volatile': local caching of a value is not allowed.
    private volatile boolean running = true;

    public void terminate() {
        this.running = false;
    }

    public boolean isRunning() {
        return this.running;
    }

    @Override
    public void run() {
        while (isRunning()) {
            doSomeWork();
            // System.out.println("isRunning() = " + isRunning());
        }
        // System.out.println("isRunning() = " + isRunning());
    }

    private void doSomeWork() {
        for (int i = 0; i < 10; i++) { }
    }
}
