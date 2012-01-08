package org.moparscape.elysium;

import org.moparscape.elysium.entity.DefaultEntityFactory;
import org.moparscape.elysium.entity.Entity;
import org.moparscape.elysium.entity.EntityFactory;
import org.moparscape.elysium.util.EntityCopyOnWriteArrayList;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public class Derp {

    public static void main(String[] args) throws Exception {
        Object o = new Object();
        CopyOnWriteArrayList<Object> list = new CopyOnWriteArrayList<Object>();

        long start = System.nanoTime() / 1000000;
        for (int i = 0; i < 2000; i++) {
            list.add(o);
        }
        long finish = System.nanoTime() / 1000000;
        System.out.println(finish - start);

        EntityFactory factory = new DefaultEntityFactory();
        Entity e = factory.newPlayer();
        EntityCopyOnWriteArrayList<Entity> elist = new EntityCopyOnWriteArrayList<Entity>(2000);
        start = System.nanoTime() / 1000000;
        for (int i = 0; i < 2000; i++) {
            elist.add(e);
        }
        finish = System.nanoTime() / 1000000;
        System.out.println(finish - start);

        elist.clear();

        for (int i = 0; i < 50; i++) {
            elist.add(factory.newPlayer());
        }

        List<Iterable<Entity>> partitions = elist.divide(7);
        for (Iterable<Entity> iterable : partitions) {
            System.out.println("Starting partition");
            for (Entity ee : iterable) {
                System.out.println(ee);
            }
        }

        System.out.println("\n\nNow for multi-threading :)");

        Executor exec = Executors.newCachedThreadPool();
        partitions = elist.divide(4);
        for (final Iterable<Entity> iterable : partitions) {
            Runnable r = new Runnable() {
                @Override
                public void run() {
                    for (Entity ee : iterable) {
                        System.out.println(ee);
                    }
                }
            };
            exec.execute(r);
        }
    }
}
