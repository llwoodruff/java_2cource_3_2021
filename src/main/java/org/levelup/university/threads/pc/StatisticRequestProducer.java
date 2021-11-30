package org.levelup.university.threads.pc;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.util.Random;

@RequiredArgsConstructor
public class StatisticRequestProducer implements Runnable{
    private final StatisticRequestQueue queue;

    @Override
    @SneakyThrows
    public void run() {
        Random random = new Random();
        for(int i = 0; i < 10; i++) {
            int accountNumber = random.nextInt(10_000_000) + 1_000_000;
            System.err.println("Producer " + Thread.currentThread().getName() + " put request with account " + accountNumber);
            queue.offer(String.valueOf((accountNumber)));
            Thread.sleep(1000);
        }

    }
}
