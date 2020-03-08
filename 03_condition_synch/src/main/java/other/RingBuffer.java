package other;

public class RingBuffer<T> {
    private final Object[] data;
    private int head;  // Next available object.
    private int tail;  // Next free position.
    private int count; // Number of elements.

    public RingBuffer(int cap) {
        data = new Object[cap];
        head = 0;
        tail = 0;
        count = 0;
    }

    public synchronized void put(T elem) throws InterruptedException {
        while (count == data.length) {
            wait();
        }
        count++;
        data[tail] = elem;
        tail = (tail+1)%data.length;
        if (count==1) {
            // If an element was placed in an empty buffer.
            notifyAll();
        }
    }

    public synchronized T get() throws InterruptedException {
        while (count == 0) {
            wait();
        }
        count--;
        T obj = (T) data[head];
        data[head] = null;
        head = (head+1)%data.length;

        if (count == data.length-1) {
            notifyAll();
        }
        return obj;
    }

}
