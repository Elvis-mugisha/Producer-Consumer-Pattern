package producerconsumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class SharedResourceBlockingQueue {
    private final BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();

    public void produce(int value) throws InterruptedException {
        queue.put(value);
    }

    public int consume() throws InterruptedException {
        return queue.take();
    }

    public static void main(String[] args) {
        SharedResourceBlockingQueue resource = new SharedResourceBlockingQueue();

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
