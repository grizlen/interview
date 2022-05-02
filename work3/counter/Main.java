package work3.counter;

import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {
        Counter counter = new Counter();
        for (int t = 0; t < 10; t++) {
            new Thread(() -> {
                for (int i = 0; i < 10; i++){
                    counter.lock();
                    System.out.println(Thread.currentThread().getName() + " counter = " + counter.incAndGet());
                    counter.unlock();
                }
            }, "THREAD_" + t).start();
        }
    }
    static class Counter extends ReentrantLock{
        private int value = 0;

        public int incAndGet() {
            return ++value;
        }
    }
}
