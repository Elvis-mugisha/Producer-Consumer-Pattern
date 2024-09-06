package producerconsumer;

public class PerformanceComparison {

    public static void main(String[] args) {
        long startTime, endTime;

        // Test Basic Implementation
        SharedResource basicResource = new SharedResource();
        Thread basicProducer = new Thread(() -> {
            try {
                for (int i = 0; i < 10000; i++) {
                    basicResource.produce(i);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread basicConsumer = new Thread(() -> {
            try {
                for (int i = 0; i < 10000; i++) {
                    basicResource.consume();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        startTime = System.nanoTime();
        basicProducer.start();
        basicConsumer.start();
        try {
            basicProducer.join();
            basicConsumer.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        endTime = System.nanoTime();
        System.out.println("Basic Implementation Time: " + (endTime - startTime) + " ns");

        // Test BlockingQueue Implementation
        SharedResourceBlockingQueue blockingQueueResource = new SharedResourceBlockingQueue();
        Thread blockingQueueProducer = new Thread(() -> {
            try {
                for (int i = 0; i < 10000; i++) {
                    blockingQueueResource.produce(i);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread blockingQueueConsumer = new Thread(() -> {
            try {
                for (int i = 0; i < 10000; i++) {
                    blockingQueueResource.consume();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        startTime = System.nanoTime();
        blockingQueueProducer.start();
        blockingQueueConsumer.start();
        try {
            blockingQueueProducer.join();
            blockingQueueConsumer.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        endTime = System.nanoTime();
        System.out.println("BlockingQueue Implementation Time: " + (endTime - startTime) + " ns");
    }
}
