package other;

public class Other {
    public static void main(String[] args) {

        R r = new R();
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        t1.start();
        t2.start();
        System.out.println("done");

    }
}


class R implements Runnable {
    int v;
    R() {
        v = 1;
    }


    synchronized void doSomething() {

    }


    @Override
    public void run() {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
