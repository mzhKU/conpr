package jmm.test;

public class RunOnConstructionTester {
    public static void main(String[] args) throws ClassNotFoundException {
        ClassLoader classLoader = RunOnConstructionTester.class.getClassLoader();

        try {
            Class roc = classLoader.loadClass("jmm.test.RunOnConstruction");
            System.out.println(roc.getName());
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }



    }
}
