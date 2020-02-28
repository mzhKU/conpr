package worksheet;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import static org.junit.Assert.assertEquals;


public class CollectionTest {
    public static final int N_INSERTS = 2_000_000;

    @Test
    public void testLinkedListSequential() throws InterruptedException {
        testCollection(new LinkedList<Integer>(), 1);
    }

    @Test
    public void testLinkedListParallel() throws InterruptedException {
        // testCollection(new LinkedList<Integer>(), Runtime.getRuntime().availableProcessors());
        // testCollection(synchronizedList(new LinkedList<Integer>()), Runtime.getRuntime().availableProcessors());
        testCollection(new ConcurrentLinkedQueue<>(), 4);

    }


//    @Test
//    public void testArrayListParallel() throws InterruptedException {
//       testCollection(new ArrayList<Integer>(), Runtime.getRuntime().availableProcessors());
//    }

    /**
     * Tests concurrent insertion of N_INSERTS elements into the given col using nThreads.
     * Each thread performs N_INSERTS/nThreads inserts.
     */
    private void testCollection(final Collection<Integer> col, final int nThreads) throws InterruptedException {
        if (N_INSERTS % nThreads != 0) throw new IllegalArgumentException("");

        /* 1. Create threads. */
        List<Thread> threads = new ArrayList<Thread>(nThreads);
        for (int n = 0; n < nThreads; n++) {
            Thread inserter = new Thread(() -> {
                for (int i = 0; i < N_INSERTS / nThreads; i++) {
                    col.add(i);
                }
            });
            threads.add(inserter);
        }

        /* 2. Start threads. */
        for (Thread t : threads) { t.start(); }

        /* 3. Wait for termination. */
        for (Thread t : threads) { t.join(); }

        /* 4. Check number of inserts. */
        assertEquals(N_INSERTS, col.size());
    }
}