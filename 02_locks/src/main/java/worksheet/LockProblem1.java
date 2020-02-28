package worksheet;

import java.util.LinkedList;
import java.util.List;

class LockProblem1 {
    private List<String> list = new LinkedList<String>();

    private final Object lock = new Object();
    // Broken version.
    // public synchronized void add(String s) {
    //     list.add(s);
    // }
    // public synchronized void remove(String s) {
    //     list.remove(s);
    // }

    // Fixed version.
    public void add(String s) {
        synchronized (this.lock) {
            list.add(s);
        }
    }
    public void remove(String s) {
        list.remove(s);
    }

    public String get() {
        synchronized (this.lock) {
            if (!list.isEmpty()) {
                return list.get(0);
            } else {
                return null;
            }
        }
    }
}
