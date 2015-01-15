package org.moparscape.elysium;

import org.moparscape.elysium.entity.EntityComparators;
import org.moparscape.elysium.entity.Heartbeat;

import java.util.PriorityQueue;
import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public class Derp {

    public static void main(String[] args) throws Exception {
        long start = time();
        String s = null;
        int progress = 0;
        for (int i = 0; i < 1000; i++) {
            UUID id = UUID.randomUUID();
            System.out.println(id);
            progress++;
        }
        //System.out.printf("%d %s\n", progress, s);
        long end = time();
        System.out.println(end - start);
        if (true) return;

        EntityComparators.HeartbeatComparator a = new EntityComparators.HeartbeatComparator();
        PriorityQueue<Heartbeat> pq = new PriorityQueue<>(new EntityComparators.HeartbeatComparator());

        pq.add(new HeartbeatImpl(1));
        pq.add(new HeartbeatImpl(1000));
        pq.add(new HeartbeatImpl(20));
        pq.add(new HeartbeatImpl(15));
        pq.add(new HeartbeatImpl(10));
        pq.add(new HeartbeatImpl(0));

        while (pq.peek() != null) System.out.println(pq.poll().getScheduledPulseTime());
    }

    public static void printSomething(Something s) {
        System.out.printf("%s %d\n", s.name, s.index);
    }

    private static long time() {
        //return System.nanoTime() / 1000000;
        return System.currentTimeMillis();
    }

    private static class HeartbeatImpl implements Heartbeat {

        private long pulseTime;

        public HeartbeatImpl(long time) {
            this.pulseTime = time;
        }

        @Override
        public long getScheduledPulseTime() {
            return pulseTime;
        }

        @Override
        public void setScheduledPulseTime(long time) {
            this.pulseTime = time;
        }

        @Override
        public void pulse() {

        }

        @Override
        public boolean isCancelled() {
            return false;
        }
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
