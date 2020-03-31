package npij;

public class SharedObject {
    private volatile int count = 0;

    public void inc() {
        count++;
    }

    public int getCount() {
        return count;
    }
}
