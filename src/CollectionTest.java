import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class CollectionTest {

    private static final LinkedList<Object> list = new LinkedList<Object>();
    private static final Queue<Object> queue = new ConcurrentLinkedQueue<Object>();
    private static final ExecutorService exec = Executors.newFixedThreadPool(4);
    private static final Lock lock = new ReentrantLock();
    private static final Object object = new Object();

    public static void main(String[] args) throws Exception {
        Future<Void> f1 = exec.submit(new ListTestExecutor());
        f1.get(); // Block until completion
        list.clear();

        Future<Void> f2 = exec.submit(new QueueTestExecutor());
        f2.get();
        exec.shutdown();
    }

    private static void addList(Object o) {
        lock.lock();
        try {
            list.add(o);
        } finally {
            lock.unlock();
        }
    }

    private static void addQueue(Object o) {
        queue.add(o);
    }

    public static class ListTestExecutor implements Callable<Void> {

        public Void call() {
            long start = time();
            List<ListTest> tasks = new LinkedList<ListTest>();
            for (int i = 0; i < 10; i++) {
                tasks.add(new ListTest());
            }
            try {
                List<Future<Void>> results = exec.invokeAll(tasks);
                for (Future<Void> f : results) {
                    f.get(); // Block until task completion
                }
            } catch (Exception e) {
                System.out.println("ListTestExecutor failed");
            }

            long end = time();
            System.out.println("ListTest time: " + (end - start));
            return null;
        }
    }

    public static class QueueTestExecutor implements Callable<Void> {

        public Void call() {
            long start = time();
            List<QueueTest> tasks = new LinkedList<QueueTest>();
            for (int i = 0; i < 10; i++) {
                tasks.add(new QueueTest());
            }
            try {
                List<Future<Void>> results = exec.invokeAll(tasks);
                for (Future<Void> f : results) {
                    f.get(); // Block until task completion
                }
            } catch (Exception e) {
                System.out.println("QueueTestExecutor failed");
            }

            long end = time();
            System.out.println("QueueTest time: " + (end - start));
            return null;
        }
    }

    private static class ListTest implements Callable<Void> {

        public Void call() {
            for (int i = 0; i < 1000000; i++) {
                addList(object);
            }
            return null;
        }
    }

    private static class QueueTest implements Callable<Void> {

        public Void call() {
            for (int i = 0; i < 1000000; i++) {
                addQueue(object);
            }
            return null;
        }
    }

    private static long time() {
        return System.nanoTime() / 1000000;
    }
}
