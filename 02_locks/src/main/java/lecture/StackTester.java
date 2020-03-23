package lecture;

public class StackTester {

    private static final int SIZE = 50;

    static class Pusher implements Runnable {

        private StackUnsafe stackUnsafe;

        public Pusher(StackUnsafe stackUnsafe) {
            this.stackUnsafe = stackUnsafe;
        }

        @Override
        public void run() {
            for (int i = 0; i<SIZE; i++) {
                if (stackUnsafe.size() < SIZE) {
                    stackUnsafe.push(i);
                }
            }

        }
    }


    public static void main(String[] args) throws InterruptedException {

        StackUnsafe stackUnsafe = new StackUnsafe(SIZE);

        Thread t1 = new Thread(new Pusher(stackUnsafe), "t1");
        Thread t2 = new Thread(new Pusher(stackUnsafe), "t2");

        t2.start();
        t1.start();

        // t1.join(); t2.join();

        for (int k = 0; k < SIZE; k++) {
            System.out.println("pop: " + stackUnsafe.pop());
        }
    }
}
