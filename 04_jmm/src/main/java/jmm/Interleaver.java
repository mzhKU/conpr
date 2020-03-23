package jmm;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Interleaver {

    /**
     * Returns a Seq containing all possible interleavings of Seqs. The order of the
     * elements within the input Seqs is preserved.
     * <p>
     * (n*m)!/(m!^n) where n= #threads and m #atomic actions
     */
    private static <T> List<List<T>> interleave(List<T> a, List<T> b) {

        if (b.size() == 0) return Collections.singletonList(a);
        if (a.size() == 0) return Collections.singletonList(b);

        List<List<T>> res   = new LinkedList<List<T>>();
        List<T>       left  = new LinkedList<T>();
        List<T>       right = new LinkedList<T>(a);

        T       bHead = b.get(0);
        List<T> bTail = new LinkedList<T>(b);

        bTail.remove(0);

        do {
            for (List<T> list : interleave(right, bTail)) {
                LinkedList<T> interleaving = new LinkedList<T>(left);
                interleaving.add(bHead);
                interleaving.addAll(list);
                res.add(interleaving);
            }
            if (right.size() == 0) break;
            left.add(right.remove(0));
        } while (true);

        // = Interleaving
        return res;
    }

    public static void main(String[] args) {
        List<String> p1 = new LinkedList<String>();
        p1.add("x=1;");
        p1.add("b=y;");
        List<String> p2 = new LinkedList<String>();
        p2.add("y=1;");
        p2.add("a=x;");

        List<List<String>> interleavings = interleave(p1, p2);

        System.out.println(interleavings.size() + " interleavings:\n"); // 24/4

        for (List<String> interleaving : interleavings) {
            System.out.println("x=0; y=0; a=0; b=0;");
            for (String statement : interleaving) {
                System.out.println(statement);
            }
            System.out.println();
        }
    }
}

