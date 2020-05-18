package as.semaphore.queue;

public class AnotherSemaphore {

    // 'places' needs to be visible across threads.
    // --> release() in one thread happens-before
    //     acquire() in another thread.
    public volatile int places;

    public AnotherSemaphore(int places) {
        this.places = places;
    }

    public synchronized void acquire() {
        while (places==0) {
            try { wait(); }
            catch (InterruptedException e) { }
        }
        places--;
        // NOTIFYALL() HERE REQUIRED??
    }

    public synchronized void release() {
        places++;
        notifyAll();
    }
}
