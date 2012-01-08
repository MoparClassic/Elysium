package org.moparscape.elysium;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public class Derp {

    public static void main(String[] args) throws Exception {
        int len = 1000;
        ChannelBuffer buffer = ChannelBuffers.buffer(len);
        for (int i = 0; i < len; i++) {
            buffer.writeByte(i * 2);
        }

//        for (int i = 0; i < 60; i++) {
//            System.out.println(buffer.readByte() + " " +
//                    buffer.readShort() + " " +
//                    buffer.readInt() + " " +
//                    buffer.readLong());
//        }

//        byte[] sample = new byte[500];
//        buffer.readBytes(sample);
//        StringBuilder sb = new StringBuilder(2000);
//        sb.append(sample[0]);
//        for (int i = 1; i < sample.length; i++) {
//            sb.append(", ").append(sample[i]);
//        }
//        System.out.println(sb.toString());

//        Object o = new Object();
//        CopyOnWriteArrayList<Object> list = new CopyOnWriteArrayList<Object>();
//
//        long start = System.nanoTime() / 1000000;
//        for (int i = 0; i < 2000; i++) {
//            list.add(o);
//        }
//        long finish = System.nanoTime() / 1000000;
//        System.out.println(finish - start);
//
//        EntityFactory factory = new DefaultEntityFactory();
//        Entity e = factory.newPlayer();
//        EntityCopyOnWriteArrayList<Entity> elist = new EntityCopyOnWriteArrayList<Entity>(2000);
//        start = System.nanoTime() / 1000000;
//        for (int i = 0; i < 2000; i++) {
//            elist.add(e);
//        }
//        finish = System.nanoTime() / 1000000;
//        System.out.println(finish - start);
//
//        elist.clear();
//
//        for (int i = 0; i < 50; i++) {
//            elist.add(factory.newPlayer());
//        }
//
//        List<Iterable<Entity>> partitions = elist.divide(7);
//        for (Iterable<Entity> iterable : partitions) {
//            System.out.println("Starting partition");
//            for (Entity ee : iterable) {
//                System.out.println(ee);
//            }
//        }
//
//        System.out.println("\n\nNow for multi-threading :)");
//
//        Executor exec = Executors.newCachedThreadPool();
//        partitions = elist.divide(4);
//        for (final Iterable<Entity> iterable : partitions) {
//            Runnable r = new Runnable() {
//                @Override
//                public void run() {
//                    for (Entity ee : iterable) {
//                        System.out.println(ee);
//                    }
//                }
//            };
//            exec.execute(r);
//        }
    }
}
