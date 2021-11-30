package org.levelup.university.threads.interruption;

import lombok.SneakyThrows;

public class ThreadInterruption {
    @SneakyThrows
    public static void main(String[] args) {
        Thread thread = new Thread(new Worker());
        thread.start();

        System.out.println("Send interruption signal: 1");
        thread.interrupt();
        Thread.sleep(1500);

        System.out.println("Send interruption signal: 2");
        thread.interrupt();
        Thread.sleep(1500);

        System.out.println("Send interruption signal: 3");
        thread.interrupt();
        Thread.sleep(1500);

    }
}
