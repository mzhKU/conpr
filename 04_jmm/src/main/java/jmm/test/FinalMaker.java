package jmm.test;

public class FinalMaker {

    private final int[] as;
    private final Data d;


    public int[] getAs() {
        return this.as;
    }


    public void setAi(Data di, int ai) {
        System.out.println("di: " + di);
        this.as[di.getElem()] = ai;
    }


    public FinalMaker(int[] as, Data d) {
        this.as = as;
        this.d = d;
    }

}
