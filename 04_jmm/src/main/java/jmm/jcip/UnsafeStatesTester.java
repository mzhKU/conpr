package jmm.jcip;

import sun.misc.Unsafe;

public class UnsafeStatesTester {
    public static void main(String[] args) {
        UnsafeStates us = new UnsafeStates();
        for(String s : us.getStates()) {
            System.out.println(s);
        }
        us.getStates()[0] = "PI";
        for(String s : us.getStates()) {
            System.out.println(s);
        }
    }
}
