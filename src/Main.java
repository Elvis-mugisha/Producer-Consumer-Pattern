import producerconsumer.ProducerConsumer;
import producerconsumer.PerformanceComparison;
import producerconsumer.BlockingQueueConsumerProducer;
import producerconsumer.SharedResource;
import producerconsumer.SharedResourceBlockingQueue;

public class Main {

    public static void main(String[] args) {
        System.out.println("Running Basic ProducerConsumer Example:");
        ProducerConsumer.main(args);

        System.out.println("\n--- BlockingQueue ProducerConsumer Example ---\n");
        BlockingQueueConsumerProducer.main(args);

        System.out.println("\n--- Performance Comparison ---\n");
        PerformanceComparison.main(args);

        System.out.println("\n--- Shared Resource Example ---\n");
        SharedResource.main(args);

        System.out.println("\n--- BlockingQueue Shared Resource Example ---\n");
        SharedResourceBlockingQueue.main(args);
    }
}
