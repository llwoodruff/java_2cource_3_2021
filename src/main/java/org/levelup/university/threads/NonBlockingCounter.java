package org.levelup.university.threads;

import java.util.concurrent.atomic.AtomicInteger;

//CAS - compare and set/swap
public class NonBlockingCounter  implements Counter{

    private final AtomicInteger value = new AtomicInteger(0);

    @Override
    public void increment() {
        value.incrementAndGet();
    }

    @Override
    public int getValue() {
        return value.get();
    }
}
