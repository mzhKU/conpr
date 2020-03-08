package other.Transmission;

public class Medium {

    private String value;
    private boolean notReceived;

    public Medium() { this.notReceived = true; this.value = ""; }

    public synchronized void send(String value) {
        this.value       = value;
        this.notReceived = false;
        notifyAll();
    }

    public synchronized String receive() {
        while (didNotReceiveNewMessage()) {
            try {
                wait();
            } catch (InterruptedException e) { }
        }
        this.notReceived = true;
        return this.value;
    }

    private boolean didNotReceiveNewMessage() {
        return this.notReceived;
    }
}
