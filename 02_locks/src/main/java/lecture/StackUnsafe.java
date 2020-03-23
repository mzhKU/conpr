package lecture;

import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class StackUnsafe {

    // 'top' points to next FREE field, not the currently last item position.
    private int top = 0;
    private int[] data;

    public StackUnsafe(int size) {
        this.data = new int[size];
    }

    public void push(int x) {
        data[top] = x;
        top++;
    }

    public int pop() {
        top--;
        return data[top];
    }

    public int size() {
        return top;
    }
}
