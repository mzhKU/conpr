package other.Messaging;

public class DataDriver {
    public static void main(String[] args) {

        Data d = new Data();
        Thread sender = new Thread(new Sender(d));
        Thread receiver = new Thread(new Receiver(d));

        sender.start();
        receiver.start();
    }
}
