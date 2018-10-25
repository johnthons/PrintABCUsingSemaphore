package util;

import java.util.concurrent.Semaphore;

/**
 * 三个线程分别打印A，B，C，要求这三个线程一起运行，打印n次，输出形如“ABCABCABC....”的字符串。
 *
 */
public class TestSemaphore {

    private int times;
    private Semaphore semaphoreA = new Semaphore(1);
    private Semaphore semaphoreB = new Semaphore(0);
    private Semaphore semaphoreC = new Semaphore(0);

    public TestSemaphore(int times) {
        this.times = times;
    }

    public static void main(String[] args) {

        TestSemaphore printABC = new TestSemaphore(10);
        new Thread(printABC :: printA).start();
        new Thread(printABC :: printB).start();
        new Thread(printABC :: printC).start();
    }

    public void printA() {
        try {
            print("A", semaphoreA, semaphoreB);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void printB() {
        try {
            print("B", semaphoreB, semaphoreC);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void printC() {
            try {
                print("C", semaphoreC, semaphoreA);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }

    private void print (String name, Semaphore current, Semaphore next) throws InterruptedException {
        for (int i = 0; i < times; i++) {
            current.acquire();
            System.out.println(name);
            next.release();
        }
    }
}
