package worksheet;

public class ThreadCounter {
    public static void main(String[] args) {
        for(int i = 0; i<10000; i++) {
            Thread t = new Thread(new M());
            t.start();
            System.out.println(i);
        }
    }
}

class M implements Runnable {

    @Override
    public void run() {
        try {
            Thread.sleep(Long.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
