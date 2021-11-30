package org.levelup.university.threads.pc;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RequiredArgsConstructor
public class StatisticRequestConsumer implements Runnable{
    private final StatisticRequestQueue queue;

    @Override
    @SneakyThrows
    public void run() {


        boolean isInterrupted = false;
        while (!isInterrupted || queue.isEmpty()) {
            try {
                String accountNumber = queue.take();
                System.out.println("Consumer " + Thread.currentThread().getName() + " start collect statistics for the account " + accountNumber);
                Thread.sleep(1500);
                System.err.println("Consumer " + Thread.currentThread().getName() + "collected statistics for the account " + accountNumber);
                if(!isInterrupted) {
                    isInterrupted = Thread.interrupted();
                }
            } catch (InterruptedException exc) {
                System.out.println("Thread has been interrupted");
                isInterrupted = true;
            }

        }

    }
}
