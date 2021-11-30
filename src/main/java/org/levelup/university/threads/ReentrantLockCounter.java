package org.levelup.university.threads;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockCounter implements Counter{

    private final ReentrantLock reentrantLock = new ReentrantLock();
    private int value;

    @Override
    public void increment() {
        reentrantLock.lock(); //это границы критической секции
        try {
            value++;
        } finally {
          reentrantLock.unlock(); // обязательно надо делать try... finally unlock!!!
        }

    }

    @Override
    public int getValue() {
        return value;
    }
}
