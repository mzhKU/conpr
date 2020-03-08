package carpark;

public class CarPark0 implements CarPark {

    // Not more than two cars in the park, no negative spaces.

    private int places;

    public CarPark0(int places) { this.places = places; }

    public synchronized void enter() {
        if (places == 0) {
            System.out.println("IS FULL:");
            throw new IllegalStateException("CarPark is full!");
        }
        places --;
    }

    public synchronized void exit() {
        places ++;
    }

    public static void main(String[] args) {
        CarPark cp = new CarPark4(2);
        Car c1 = new Car("car1", cp); c1.start();
        Car c2 = new Car("car2", cp); c2.start();
        Car c3 = new Car("car3", cp); c3.start();
        Car c4 = new Car("car4", cp); c4.start();
    }
}


