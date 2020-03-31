package jmm.person;

public class Person {
    private String name;

    // public Person() { }

    public Person(String name) {
        this.name = name;
    }

    // public synchronized void setName(String name) {
    //     this.name = name;
    //     notifyAll();
    // }

    public String getName() {
        return this.name;
    }
}
