package other.Transmission;

public class Medium {

    private String oldValue;
    private String newValue;

    public synchronized void send(String newValue) {
        this.oldValue = this.newValue;
        notifyAll();
        this.newValue = newValue;
    }

    public synchronized String receive() {
        while (notNew()) {
            try {
                wait();
            } catch (InterruptedException e) { }
        }
        return this.newValue;
    }

    private boolean notNew() {
        return this.oldValue == this.newValue;
    }
}
