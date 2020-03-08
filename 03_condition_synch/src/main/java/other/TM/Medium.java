package other.TM;

public class Medium {

    private int value;
    private boolean hasNoNumber;


    public Medium() {
        this.hasNoNumber = true;
    }

    public synchronized void send(int number) {
        this.value = number;
        hasNoNumber = false;
        notifyAll();
    }


    public synchronized int receive() {
        while (hasNoNumber) {
            try {
                wait();
            } catch (InterruptedException e) { }
        }
        hasNoNumber = true;
        return this.value;
    }

}
