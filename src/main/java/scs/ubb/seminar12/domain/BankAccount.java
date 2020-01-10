package scs.ubb.seminar12.domain;

import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
    private double balance;
    private ReentrantLock lock = new ReentrantLock();

    public BankAccount(double amount) {
        this.balance = amount;
    }

    public double getBalance() {
        return this.balance;
    }

    public void deposit(double amount) {
        lock.lock();
        try {
            double temp = getBalance();
            temp = temp + amount;
            try {
                Thread.sleep(300);
            } catch (InterruptedException ie) {
                System.err.println(ie.getMessage());
            }
            balance = temp;
        } finally {
            lock.unlock();
        }

    }

}
