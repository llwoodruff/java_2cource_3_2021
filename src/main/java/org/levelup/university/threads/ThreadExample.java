package org.levelup.university.threads;

import lombok.SneakyThrows;

import java.time.LocalTime;

public class ThreadExample {

    @SneakyThrows
    public static void main(String[] args) {

        Thread timePrinter = new Thread(new TimerPrinter());
        timePrinter.setDaemon(true); //теперь это демон поток
        timePrinter.start();

        String mainThreadName = Thread.currentThread().getName();
        System.out.println(mainThreadName + " start method name");
        Thread thread = new Printer();
        //thread.run(); не надо, если выполнить run(), то поток запуститься в методе ain

        thread.start(); // запуститься в новом потоке
        //метод маин  не ждет окончания потоков

        Thread factorialThread = new Thread(new FactorialCalc(), "factorial-thread"); //"factorial-thread" название потока, которое мы дали
        factorialThread.start();
        Thread factorialThread2 = new Thread(new FactorialCalc(), "factorial-thread-2");
        factorialThread2.start();
        Thread factorialThread3 = new Thread(new FactorialCalc(), "factorial-thread-3");
        factorialThread3.start(); //из одного класса можно сделать несколько потоков

        thread.join(); //один поток будет одидать, когда завершит свое выполнение другой поток. join() относится к методу main. То есть поток main будет ожидать пока не завершиться thread

        Thread.sleep(2500);
        System.out.println(mainThreadName + ": at the end of method main");

    }
    //параллельно отрабатывают main и printer
    static class Printer extends Thread{

        public Printer(){
            super("printer-thread"); //Дали название потоку, инче идет по умолчанию
        }
        @Override
        @SneakyThrows //аннотация из библиотек lombok, что не было checked исключений. Иначе Проблема с  sleep
        public void run() {
            String threadName = getName();
            for(int i = 0; i <10; i++){
                System.out.println(threadName + " In custom thread");
                Thread.sleep(400);

            }
            System.out.println(threadName + " Method run finished invocation");
        }
        //метод run() некоторые аналог метода main. То есть как только run завершится поток умрет
    }

    static class FactorialCalc implements Runnable{

        @Override
        @SneakyThrows
        public void run() {
            int factorial = 1;
            for(int i = 1; i < 9; i++) {
                factorial *= i;
            }

            Thread.sleep(600);
            System.out.println(Thread.currentThread().getName() + ": factorial = " + factorial);
        }
    }

    static class TimerPrinter implements Runnable {

        @Override
        @SneakyThrows
        public void run() {
            while (true) {
                System.out.println(LocalTime.now());
                Thread.sleep(1000);

            }
        }
        }
    }

