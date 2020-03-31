package jmm.SingletonStuff;

public class SingletonTester {


    public static void main(String[] args) {


        SingletonEager   se = SingletonEager.getInstance();
        SingletonClassic sc = SingletonClassic.getInstance();


        System.out.println("se: " + se);
        System.out.println("sc: " + sc);

    }
}
