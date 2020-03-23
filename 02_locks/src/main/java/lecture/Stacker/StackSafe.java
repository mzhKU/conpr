package lecture.Stacker;

import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class StackSafe extends StackBaseClass implements Stack {

    public StackSafe(int size) {
        super(size);
    }

    public synchronized void push(int x) {
            data[top] = x;
            top++;
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) { }
    }

    public synchronized int pop() {
            top--;
            return data[top];
    }

    @Override
    public synchronized int size() {
            return top;
    }
}
