package other_npij;

public class LockDemo implements Runnable{
    private Object lock1 = new Object();
    private Object lock2 = new Object();

    @Override
    public void run() {
        synchronized (lock1) {
            for (int i = 0; i<10; i++) {
                System.out.println("Method 1: " + i);
            }
        }
    }
}


class LockDemoDriver {
    public static void main(String[] args) {


        Thread t1 = new Thread();





    }
}