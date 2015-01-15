package org.moparscape.elysium.task;

import java.util.concurrent.CountDownLatch;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class CountdownTaskExecutor implements Runnable {

    private final CountDownLatch latch;
    private final Runnable r;

    public CountdownTaskExecutor(Runnable r, CountDownLatch latch) {
        this.r = r;
        this.latch = latch;
    }

    public void run() {
        try {
            r.run();
        } finally {
            latch.countDown();
        }
    }
}
