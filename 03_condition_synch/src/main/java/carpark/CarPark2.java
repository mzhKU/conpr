package carpark;


public class CarPark2 implements CarPark {
    private int places;

    public CarPark2(int places) {
        this.places = places;
    }

    private synchronized boolean isFull() {
        return places == 0;
    }

    private synchronized void decPlaces() {
        places--;
        System.out.println("places: " + places);
    }

    private synchronized void incPlaces() {
        places++;
    }

    @Override
    public void enter() {
        /* Check and enter is not consistent over time. */
        while (isFull()) { } // busy waiting
        log("enter carpark");
        decPlaces();
    }

    @Override
    public void exit() {
        log("exit carpark");
        incPlaces();
    }

    public static void main(String[] args) {
        CarPark cp = new CarPark2(2);
        Car c1 = new Car("car1", cp); c1.start();
        Car c2 = new Car("car2", cp); c2.start();
        Car c3 = new Car("car3", cp); c3.start();
        Car c4 = new Car("car4", cp); c4.start();
    }

    /*
     ----------------------------------------------------------------
     EXAMPLE OUTPUT
     ----------------------------------------------------------------
     car3 try to enter carpark
     car3 enter carpark
     places: 0
     car1 try to enter carpark
     car4 try to enter carpark
     car2 try to exit carpark
     car2 exit carpark           // C1 and C4 are in while loop of enter method
     car4 enter carpark          // Park is not full and both threads can enter
     car1 enter carpark          // Context is switched from C4 after isFull but
     places: 0                   // before decreasing the places counter, so both
     places: -1                  // threads C1 and C4 can decrease -> -1 places.
     car3 try to exit carpark
     car3 exit carpark
     ----------------------------------------------------------------
     */

    private void log(String msg) {
        System.out.println(Thread.currentThread().getName() + " " + msg);
    }
}
