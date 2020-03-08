package other.TM;

public class Receiver implements Runnable {


    private Medium medium;


    public Receiver(Medium medium) {
        this.medium = medium;
    }


    @Override
    public void run() {
        while (true) {
            System.out.println("[RECEIVE]" + medium.receive() + "\n");
        }
    }
}
