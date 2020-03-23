package lecture.Stacker;

import net.jcip.annotations.NotThreadSafe;

@NotThreadSafe
public class StackUnsafe extends StackBaseClass implements Stack {

    public StackUnsafe(int size){
        super(size);
    }

    public void push(int x) {
        data[top] = x;
        top++;
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) { }
    }

    @Override
    public int pop() {
        top--;
        return data[top];
    }

    public int size() {
        return top;
    }
}
