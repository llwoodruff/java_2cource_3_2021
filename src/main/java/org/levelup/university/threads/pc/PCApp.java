package org.levelup.university.threads.pc;

import lombok.SneakyThrows;

public class PCApp {
    @SneakyThrows
    public static void main(String[] args) {
        StatisticRequestQueue queue = new ReentrantLockStatisticRequestQueue(5);
        Thread consumer1 = new Thread(new StatisticRequestConsumer(queue), "consumer-1");
        Thread consumer2 = new Thread(new StatisticRequestConsumer(queue), "consumer-2");
        consumer1.start();
        consumer2.start();

        Thread producer1 = new Thread(new StatisticRequestProducer(queue), "producer-1");
        Thread producer2 = new Thread(new StatisticRequestProducer(queue), "producer-2");
        producer1.start();
        producer2.start();

        Thread.sleep(15_000);
        System.err.println("Send interruption signals to consumers");
        consumer1.interrupt();
        consumer2.interrupt();
    }
}
