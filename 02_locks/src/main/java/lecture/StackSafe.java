package lecture;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class StackSafe {
    private final Object lock = new Object();

    // 'top' points to next FREE field, not the currently last item position.

    @GuardedBy("lock")
    private int top = 0;

    @GuardedBy("lock")
    private int[] data = new int[10];

    public void push(int x) {
        synchronized (lock) {
            data[top] = x;
            top++;
        }
    }

    public int pop() {
        synchronized (lock) {
            top--;
            return data[top];
        }
    }
}