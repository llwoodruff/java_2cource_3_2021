package org.levelup.university.threads.pc;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockStatisticRequestQueue  implements StatisticRequestQueue{

    private final LinkedList<String> queue = new LinkedList<>();
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition emptyCondition = lock.newCondition();
    private final Condition fullConfition = lock.newCondition();
    private final int queueSize;

    public ReentrantLockStatisticRequestQueue(int queueSize) {
        this.queueSize = queueSize;
    }

    @Override
    public void offer(String accountNumber) throws InterruptedException {
        lock.lock();
        try {
            while (queue.size() >= queueSize) {
                fullConfition.await();
            }
            queue.addLast(accountNumber);
            emptyCondition.signalAll();
        } finally {
            lock.unlock();
        }

    }

    @Override
    public String take() throws InterruptedException {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                emptyCondition.await();
            }
            String accountNumber = queue.poll();
            fullConfition.signalAll();
            return accountNumber;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean isEmpty() {
        lock.lock();
        try {
            return queue.isEmpty();
        } finally {
            lock.unlock();
        }

    }
}
