package myOwnTests;

public class TestCASTester {
    public static void main(String[] args) {

        TestCAS tc = new TestCAS();

        System.out.println("TC: " + tc.getValue());
        tc.increment();
        System.out.println("TC: " + tc.getValue());

    }
}
