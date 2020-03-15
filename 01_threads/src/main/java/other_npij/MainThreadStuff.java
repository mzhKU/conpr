package other_npij;

public class MainThreadStuff {
    public static void main(String[] args) {
        // Anzahl der Prozessoren abfragen
        int nr = Runtime.getRuntime().availableProcessors();

        System.out.println("Anzahl der Prozessoren " + nr);

        // Eigenschaften des main-Threads
        Thread self = Thread.currentThread();
        System.out.println("Name : "      + self.getName());
        System.out.println("Priorität : " + self.getPriority());
        System.out.println("ID : "        + self.getId());
    }
}
