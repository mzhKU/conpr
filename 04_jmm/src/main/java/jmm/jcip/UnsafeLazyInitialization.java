package jmm.jcip;

public class UnsafeLazyInitialization {


    private static Resource resource;


    public static Resource getInstance() {
        if (resource == null) {
            resource = new Resource(10);
        }
        return resource;
    }

    public void whooli() throws InterruptedException {
        this.wait();
    }


    public static void main(String[] args) {


        new Thread("a") {
            public void run() {
                Resource r1 = getInstance();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) { }
                System.out.println("r1.a: " + r1.getA());
            }
        }.start();

        new Thread("b") {
            public void run() {
                Resource r2 = getInstance();
                System.out.println("r2.a: " + r2.getA());
            }
        }.start();

    }



    static class Resource {
        private int a;
        public Resource(int a) {
            this.a = a;
        }
        public int getA() {
            return this.a;
        }
    }
}



