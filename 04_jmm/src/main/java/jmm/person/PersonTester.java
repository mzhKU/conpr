package jmm.person;

public class PersonTester {

    static volatile Person p = null;
    public final static Object WAIT_LOCK = new Object();

    public static void main(String[] args) throws InterruptedException {

        Thread t2 = new Thread(() -> {
            p = new Person("Meier");
            // p = new Person();
            // p.setName("Meier");
            synchronized (WAIT_LOCK) {
                WAIT_LOCK.notifyAll();
            }
        });


        Thread t3 = new Thread(() -> {
                while (p == null) {
                    synchronized (WAIT_LOCK) {
                        try {
                            WAIT_LOCK.wait();
                        } catch (InterruptedException e) { }
                    }
                }
                System.out.println("Name: " + p.getName());
        });

        t2.start(); t3.start();
        t2.join();  t3.join();

    }

}
