package other_npij;

public class MyWorker extends Thread {
    public MyWorker(String name) {
        super(name);
    }

    @Override
    public void run() {
        // Thread self = Thread.currentThread();
        // self.getName();
        System.out.println("Name: " + this.getName());
    }
}

class Driver {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (int i = 0; i<3; i++) {
            MyWorker t = new MyWorker("i"+i);
            t.start();
        }
        long end = System.currentTimeMillis();
        long duration = end - start;
        System.out.println("Duration: " + duration);
    }
}
