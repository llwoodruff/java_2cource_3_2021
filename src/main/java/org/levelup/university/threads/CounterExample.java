package org.levelup.university.threads;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

public class CounterExample {

    @SneakyThrows
    public static void main(String[] args) {
        // Counter counter = new SynchronizedCounter();
        Counter counter = new NonBlockingCounter();

        Thread t1 = new Thread(new Incrementor(counter));
        Thread t2 = new Thread(new Incrementor(counter));
        Thread t3 = new Thread(new Incrementor(counter));

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        System.out.println("Counter value: " + counter.getValue());
    }

    static class SynchronizedCounter implements Counter {

        private final Object mutex = new Object();

        private int value;

        // t1.c.increment()
        // t2.c.increment()

        // t1: read value -> 80
        // t2: read value -> 80
        // t1: increment -> 81
        // t2: increment -> 81
        // t1: write -> 81
        // t2: write -> 81

        // t1: read value -> 80
        // t1: increment -> 81
        // t2: read value -> 80
        // t1: write value -> 81
        // t2: increment -> 81
        // t2: write value -> 81

        public void increment() {
            // monitor: threadId - 1

            // monitor: t1 - 1
            // t2/t3 wait

            // t1.c.increment()
            // t3.c.increment()
            // t1 acquires lock
            // t3 is waiting
            // t1: read
            // t1: increment
            // t1: write
            // t1 releases lock
            // t3 wakes up
            // t3 acquires lock

            // t1 acquires lock
            // t2 is waiting
            // t3 is waiting
            // t1 releases lock
            // t2 wakes up
            // t3 wakes up
            // t3 acquires lock
            // t2 is waiting
            synchronized (mutex) {
                value++;
            }
        }

        public void synchronizedBlockThisIncrement() {
            // ...
            synchronized (this) {
                value++;
            }
            // ...
        }

        public synchronized void synchronizedIncrement() {
            value++;
        }

        public int getValue() {
            return value;
        }

    }

    @RequiredArgsConstructor
    static class Incrementor implements Runnable {

        private final Counter counter;

        @Override
        @SneakyThrows
        public void run() {
            for (int i = 0; i < 40; i++) {
                counter.increment();
                Thread.sleep(100);
            }
        }

    }

}