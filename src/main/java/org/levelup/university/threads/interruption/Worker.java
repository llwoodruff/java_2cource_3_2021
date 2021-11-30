package org.levelup.university.threads.interruption;

public class Worker implements Runnable{

    @Override
    public void run() {
        int interruptCallCount = 0;
        boolean isInterrupted = false;

        while (true) {
            try {
                if(isInterrupted) { //currentThread().isInterrupted()
                    interruptCallCount++;
                    System.out.println("Count of interruption calls: " + interruptCallCount);
                    if(interruptCallCount >= 3) {
                        System.out.println("Thread finishes its work");
                        return;
                    }
                }
                isInterrupted = Thread.interrupted();

                System.err.println("Thread is working...");
                Thread.sleep(700);
            } catch (InterruptedException exc) {
                System.out.println("Thread has been interrupted...");
                isInterrupted =true;
            }
        }
    }

}
