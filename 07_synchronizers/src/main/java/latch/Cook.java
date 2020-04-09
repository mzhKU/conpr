package latch;

import java.util.concurrent.CountDownLatch;

class Cook extends Thread {

    private CountDownLatch mealIsReady;

    public Cook(CountDownLatch mealIsReady) {
        this.mealIsReady = mealIsReady;
    }

    @Override
    public void run() {
        System.out.println("Start Cooking..");
        try { sleep(5000); } catch (InterruptedException e) { }

        System.out.println("Meal is ready");
        mealIsReady.countDown();
    }
}