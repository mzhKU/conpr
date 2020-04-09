package latch;

import java.util.concurrent.CountDownLatch;

public class Restaurant {

    public static void main(String[] args) {
        int nrGuests = 2;

        CountDownLatch mealIsReady = new CountDownLatch(1);
        CountDownLatch guestIsDone = new CountDownLatch(nrGuests);

        new Cook(mealIsReady).start();

        for (int i = 0; i < nrGuests; i++) {
            new Guest(mealIsReady, guestIsDone).start();
        }

        new DishWasher(guestIsDone).start();
    }
}
