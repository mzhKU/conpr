package jmm.test;

public class RunOnConstruction {

    private String v;

    public static RunOnConstruction instance = new RunOnConstruction("was run");

    public RunOnConstruction(String n) {
        this.v = n;
        System.out.println("V: " + this.v);
    }


}
