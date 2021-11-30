package org.levelup.university.threads.pc;

public interface StatisticRequestQueue {
    // add to queue
    void offer(String account) throws InterruptedException;

    // get the next account?Numbaer from queue
    String take() throws InterruptedException;

    boolean isEmpty();
}
