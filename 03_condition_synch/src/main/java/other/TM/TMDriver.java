package other.TM;

public class TMDriver {

    public static void main(String[] args) {

        Medium medium = new Medium();

        Thread sender = new Thread(new Sender(medium));
        Thread receiver = new Thread(new Receiver(medium));


        sender.start();
        receiver.start();


    }

}
