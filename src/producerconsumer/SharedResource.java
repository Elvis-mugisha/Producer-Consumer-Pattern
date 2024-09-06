package producerconsumer;

import java.util.LinkedList;
import java.util.Queue;

public class SharedResource {
    private final Queue<Integer> queue = new LinkedList<>();
    private final int LIMIT = 10;
    private final Object lock = new Object();

    public void produce(int value) throws InterruptedException {
        synchronized (lock) {
            while (queue.size() == LIMIT) {
                lock.wait();
            }
            queue.add(value);
            lock.notifyAll();
        }
    }

    public int consume() throws InterruptedException {
        synchronized (lock) {
            while (queue.isEmpty()) {
                lock.wait();
            }
            int value = queue.poll();
            lock.notifyAll();
            return value;
        }
    }

    public static void main(String[] args) {
        SharedResource resource = new SharedResource();

        Thread producer = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    resource.produce(i);
                    System.out.println("Produced: " + i);
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                System.err.println("Producer interrupted: " + e.getMessage());
                Thread.currentThread().interrupt();
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    int value = resource.consume();
                    System.out.println("Consumed: " + value);
                    Thread.sleep(150);
                }
            } catch (InterruptedException e) {
                System.err.println("Consumer interrupted: " + e.getMessage());
                Thread.currentThread().interrupt();
            }
        });

        producer.start();
        consumer.start();
    }
}
