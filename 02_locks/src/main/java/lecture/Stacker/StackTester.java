package lecture.Stacker;

public class StackTester {

    private static final int SIZE = 10;
    private Stack stackUnsafe;
    private Stack stackSafe;

    public static void main(String[] args) throws InterruptedException {

        StackTester tester = new StackTester();

        tester.stackUnsafe = new StackUnsafe(SIZE);
        tester.stackSafe   = new StackSafe(SIZE);


        // ------------------------------------------------------------------
        // UNSAFE
        Thread t1 = new Thread(() -> {
            for(int i = 0; i<SIZE; i++) {
                tester.stackUnsafe.push(i);
            }
        });

        Thread t2 = new Thread(() -> {
            System.out.println("Unsafe size: " + tester.stackUnsafe.size());
        });

        t1.start(); t2.start();
        t1.join();  t2.join();
        // ------------------------------------------------------------------


        // ------------------------------------------------------------------
        // SAFE
        Thread t3 = new Thread(() -> {
            for(int i = 0; i<SIZE; i++) {
                tester.stackSafe.push(i);
            }
        });

        Thread t4 = new Thread(() -> {
            System.out.println("Safe size: " + tester.stackSafe.size());
        });

        t3.start(); t4.start();
        t3.join();  t4.join();
        // ------------------------------------------------------------------

    }
}
