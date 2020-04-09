package latch;

import java.util.concurrent.CountDownLatch;

public class Guest extends Thread {

    private CountDownLatch mealIsReady, guestIsDone;

    public Guest(CountDownLatch mealIsReady, CountDownLatch guestsAreDone) {
        this.mealIsReady = mealIsReady;
        this.guestIsDone = guestsAreDone;
    }

    @Override
    public void run() {
        try {
            sleep(1000);

            System.out.println("Entering restaurant and placing order.");
            mealIsReady.await();

            System.out.println("Enjoying meal.");
            sleep(4000);

            System.out.println("Meal was excellent!");
            guestIsDone.countDown();

        } catch (InterruptedException e) { }
    }
}
