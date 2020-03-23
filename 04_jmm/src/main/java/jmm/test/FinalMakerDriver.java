package jmm.test;

public class FinalMakerDriver {
    public static void main(String[] args) {
        FinalMaker fm = new FinalMaker(new int[]{1, 2, 3}, new Data(3));

        System.out.println("as: " + fm.getAs());


        Data valueToSet = new Data(0);
        System.out.println("Value to set: " + valueToSet);

        fm.setAi(valueToSet, 7);

        System.out.println("as: " + fm.getAs());
    }
}
