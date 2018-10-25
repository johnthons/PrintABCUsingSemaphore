package util;

import java.util.concurrent.Semaphore;

/**
 * B,C线程获取到的值由A线程产生
 */
public class TestThreadCommunication {

    private static int num;

    private static Semaphore semaphore = new Semaphore(0);

    public static void main(String[] args) {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    num = 1;
                    semaphore.release(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "获取到的num值为：" + num);
            }
        });

        Thread threadC = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "获取到的num值为：" + num);
            }
        });

        threadA.start();
        threadB.start();
        threadC.start();
    }
}
