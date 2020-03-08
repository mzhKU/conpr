package other.Transmission;

public class Receiver implements Runnable {

    private Medium medium;

    public Receiver(Medium medium) {
        this.medium = medium;
    }

    @Override
    public void run() {
        while (true) {
            String message = medium.receive();
            System.out.println("[RECEIVING]" + message);
        }
    }
}
