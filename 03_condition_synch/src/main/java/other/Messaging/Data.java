package other.Messaging;

import javax.sound.midi.Soundbank;

public class Data {
    private String packet;


    // True if receiver should wait for sender to send the message.
    // False if sender should wait for receiver to receive the message.
    private boolean transfer = true;


    public synchronized void send(String packet) {
        while (!transfer) {
            try {
                // 'wait()' suspends the current thread, the thread releases the lock.
                wait();  // wait() is called on the thread that wants to send.
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread interrupted: " + e);
            }
        }
        transfer = false;
        this.packet = packet;
        notifyAll();    // Notify waiting threads to wake up and specify
                        // that an event occured and they can check if they
                        // can continue execution.
    }


    public synchronized String receive() {
        while (transfer) {
            try {
                wait(); // Wait on this thread to wait for sending to finish.
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread interrupted: " + e);
            }
        }
        transfer = true;
        notifyAll();    // Notify all threads to wake up and return the
        return packet;  // data packet that was receiver (?).
    }
}
