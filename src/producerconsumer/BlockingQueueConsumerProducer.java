package producerconsumer;

public class BlockingQueueConsumerProducer {

    public static void main(String[] args) {
        SharedResourceBlockingQueue resource = new SharedResourceBlockingQueue();

        Thread producer = new Thread(() -> {
            try {
                for (int i = 0; i < 20; i++) {
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
                for (int i = 0; i < 20; i++) {
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
