package util.TestProducerAndConsumer;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable{
    protected BlockingQueue<Object> queue;

    Consumer(BlockingQueue<Object> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Object obj = queue.take();
                System.out.println("Consumed resource - Queue size now = "
                        + queue.size());
                take(obj);
            }
        }catch (InterruptedException e) {
            System.out.println("CONSUMER INTERRUPTED");
        }
    }

    void take(Object obj) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            System.out.println("Consumer Read INTERRUPTED");
        }
        System.out.println("Consuming object " + obj);
    }
}
