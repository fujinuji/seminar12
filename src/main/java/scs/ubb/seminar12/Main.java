package scs.ubb.seminar12;

import scs.ubb.seminar12.domain.BankAccount;
import scs.ubb.seminar12.domain.Pizza;

import java.time.LocalDate;
import java.util.stream.IntStream;

public class Main {
    private static final Integer LONG_TIME = 100000000;

    public static void main(String[] args) throws InterruptedException {
        int counter = 0;
        /**
         * Targets:
         *         -> Thread and Runnable (ok)
         *         -> Synchronization
         *         -> Mutex and Semaphores
         *         -> wait() and notify()
         *         -> Executors
         *         -> Callable and Future objects
         *         -> Streams on callable
         */


        /**
         *  Introduction in Java Concurrency with Thread class and Runnable interface
         */

        /**
         * Runnable interface is a FUNCTIONAL INTERFACE (run method)
         * ? Ask for Functional Interface
         */
        Runnable runnable = () -> {
            try {
                String threadName = Thread.currentThread().getName();
                System.out.println("I'm a runnable running on thread " + threadName);
                Thread.sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        runnable.run();

        //Compare order of prints and use LONG_TIME to prove this :)))
        Thread thread = new Thread(runnable);
        thread.start();
        System.out.println("Am terminat");

        BankAccount bankAccount = new BankAccount(3);
        Thread thread1 = new Thread(() -> bankAccount.deposit(50));
        Thread thread2 = new Thread(() -> bankAccount.deposit(50));

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(bankAccount.getBalance());

        /**
         * We're getting pizza, boys
         */
        Pizza pizza=new Pizza(15.5F,"Campulara");
        Thread thread3 = new Thread(()->pizza.cookAndDeliver());
        Thread thread4 = new Thread(()->pizza.oderPizza());

        thread3.start();
        thread4.start();

        thread3.join();
    }


}
