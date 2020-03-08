package other.Messaging;

import other.Messaging.Data;

import java.util.concurrent.ThreadLocalRandom;

public class Sender implements Runnable {

    private Data data;

    public Sender(Data d) { this.data = d; }

    @Override
    public void run() {
        String packets[] = {"First packet",
                "Second packet",
                "Third packet",
                "Fourth Packet",
                "End"};


        for (String packet : packets) {
            data.send(packet);

            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 2000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread interrupted: " + e);
            }
        }
    }
}
