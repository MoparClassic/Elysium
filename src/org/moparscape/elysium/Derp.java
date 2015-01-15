package org.moparscape.elysium;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public class Derp {

    public static void main(String[] args) throws Exception {
        AtomicBoolean bool = new AtomicBoolean(false); // Safe for optimistic concurrency.

        List<Integer> a = new ArrayList<>(500);
        ConcurrentLinkedQueue<Integer> b = new ConcurrentLinkedQueue<>();
        //ArrayBlockingQueue<Integer> b = new ArrayBlockingQueue<Integer>(500); // Useless - doesn't grow in capacity.

        // An idea: Cache viewable entities at the beginning of each game pulse.

        int zz = 1;

        for (int i = 0; i < 10; i++) {
            a.add(i);
            b.add(i);
        }

        long start = time();
        for (int i = 0; i < 10; i++) {
            synchronized (a) {
                for (Integer x : a) {
                    zz += x;
                }
            }
        }
        long end = time();
        System.out.println(end - start);

        start = time();
        for (int x = 0; x < 10; x++) {
            for (Integer i : b) {
                zz += i;
            }
        }
        end = time();
        System.out.println(end - start);

        System.out.println(zz);

//        Something a = new Something("A", 1);
//        Something b = new Something("B", 2);
//        Something c = new Something("C", 3);
//
//        PriorityQueue<Something> q = new PriorityQueue<>();
//        q.add(a);
//        q.add(b);
//        q.add(c);
//
//        b.index = 5;
//
//        while (q.peek() != null) {
//            Something s = q.poll();
//            System.out.printf("%s %d\n", s.name, s.index);
//        }
    }

    public static void printSomething(Something s) {
        System.out.printf("%s %d\n", s.name, s.index);
    }

    private static long time() {
        //return System.nanoTime() / 1000000;
        return System.currentTimeMillis();
    }

    private static class Something implements Comparable<Something> {
        private int index;
        private String name;

        public Something(String name, int index) {
            this.name = name;
            this.index = index;
        }

        public int compareTo(Something other) {
            return Integer.compare(this.index, other.index);
        }

        @Override
        public int hashCode() {
            return index;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Something)) return false;

            return ((Something) o).index == this.index;
        }
    }
}
