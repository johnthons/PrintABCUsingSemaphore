package util.TestProducerAndConsumer;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable{
    protected BlockingQueue<Object> queue;

    Producer(BlockingQueue<Object> queue) {
        this.queue = queue;
    }
    @Override
    public void run() {
        try {
            while (true) {
                Object justProduced = getResource();
                queue.put(justProduced);
                System.out.println("Produced resource === Queue size now: " + queue.size());
            }
        } catch (InterruptedException e) {
            System.out.println("Producer INTERRUPTED");
        }

    }

    Object getResource() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Object();
    }
}
