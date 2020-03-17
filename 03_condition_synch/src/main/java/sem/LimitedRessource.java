package sem;

public class LimitedRessource {

    private int spaces;

    public LimitedRessource(int spaces) {
        this.spaces = spaces;
    }

    public int getSpaces() {
        return spaces;
    }


    public synchronized void occupySpace() {
        if (this.spaces > 0) {
            this.spaces--;
        }
    }


    // Does 'release' need to be synchronized?
    public void releaseSpace() {
        this.spaces++;
    }


    private void useRessource() {
        for (int i = 0; i<10000000; i++) {
            if (i % 1000 == 0) {
                System.out.println("i: " + i);
            }
        }
    }


}
