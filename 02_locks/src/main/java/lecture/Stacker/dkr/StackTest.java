package lecture.Stacker.dkr;


import java.util.concurrent.atomic.AtomicBoolean;

public class StackTest {
    static final AtomicBoolean testDone = new AtomicBoolean();

    static boolean t1Done = false;
    static StackUnsafe stack = new StackUnsafe();

    public static void main(String[] args) throws InterruptedException {

        while(! testDone.get()) {
            t1Done = false;
            stack = new StackUnsafe();

            Thread t1 = new Thread(() -> {
                for(int i = 0; i < 5; i++) {
                    stack.push(i);
                    t1Done = true;
                }
            });

            Thread t2 = new Thread(() -> {
                if(t1Done && stack.size() != 5) {
                    testDone.set(true);
                }
            });

            t1.start(); t2.start();
            t1.join(); t2.join();
        }
    }
}

class StackUnsafe {

    private int top = 0;

    private int[] data = new int[10];

    public synchronized void push(int x) {
        data[top] = x;
        top++;
    }

    public synchronized int pop() {
        top--;
        return data[top];
    }

    public synchronized int size() {
        return top;
    }
}
