package other_npij;

public class ModuloCounter {
    private int count = 0;
    private final int mod;

    public ModuloCounter(int mod) {
        this.mod = mod;
    }

    public synchronized void increment() {
        count = (count+1)%mod;
    }

    public synchronized void decrement() {
        count = (count-1+mod)%mod;
    }

    public synchronized int getValue() {
        return count;
    }
}
