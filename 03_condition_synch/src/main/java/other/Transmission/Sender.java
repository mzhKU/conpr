package other.Transmission;

import java.util.Random;

import static java.lang.Thread.sleep;

public class Sender implements Runnable {

    private Medium medium;
    private String message;

    public Sender(Medium medium) {
        this.medium = medium;
    }

    @Override
    public void run() {
        while (true) {
            message = calculateMessage();
            medium.send(message);
            System.out.println("[SENDING]" + message);
        }
    }

    private String calculateMessage() {
        try {
            sleep(new Random().nextInt(10)*100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return String.valueOf(new Random().nextInt(10));
    }
}
