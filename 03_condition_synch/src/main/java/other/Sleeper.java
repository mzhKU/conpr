package other;

import java.util.Random;

import static java.lang.Thread.sleep;

public class Sleeper {
    public static void main(String[] args) {
        while(true) {
            int sleepDuration = (int)(10.0*Math.random()*100);
            System.out.println("Duration: " + sleepDuration);
            try {
                sleep(sleepDuration);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
