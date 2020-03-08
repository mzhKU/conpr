package other.Transmission;

public class TransmissionDriver {
    public static void main(String[] args) {

        Medium   data    = new Medium();
        Sender   send    = new Sender(data);
        Receiver receive = new Receiver(data);

        Thread sender   = new Thread(send);
        Thread receiver = new Thread(receive);

        sender.start();
        receiver.start();
    }
}
