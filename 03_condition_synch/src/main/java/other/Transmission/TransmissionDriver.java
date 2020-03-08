package other.Transmission;

public class TransmissionDriver {
    public static void main(String[] args) {

        Medium   medium  = new Medium();
        Sender   send    = new Sender(medium);
        Receiver receive = new Receiver(medium);

        Thread sender   = new Thread(send);
        Thread receiver = new Thread(receive);

        sender.start();
        receiver.start();
    }
}
