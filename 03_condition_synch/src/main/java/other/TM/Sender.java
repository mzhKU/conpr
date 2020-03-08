package other.TM;

import java.util.Random;

import static java.lang.Thread.sleep;

public class Sender implements Runnable {

    private Medium medium;

    public Sender(Medium medium) {
        this.medium = medium;
    }

    @Override
    public void run() {
        while (true) {
            int number = new Random().nextInt(10);
            medium.send(number);
            System.out.println("[SEND]" + number);
            try {
                sleep(new Random().nextInt(10)*100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
