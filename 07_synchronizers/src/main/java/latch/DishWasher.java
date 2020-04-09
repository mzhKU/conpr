package latch;

import java.util.concurrent.CountDownLatch;

public class DishWasher extends Thread {

    private CountDownLatch guestIsDone;

    public DishWasher(CountDownLatch guestIsDone) {
        this.guestIsDone = guestIsDone;
    }

    @Override
    public void run() {
        try {
            System.out.println("Waiting for dirty dishes.");
            guestIsDone.await();

            System.out.println("Washing dishes.");
            sleep(0);
        } catch (InterruptedException e) { }
    }
}
