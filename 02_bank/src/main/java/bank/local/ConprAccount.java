package bank.local;

import bank.Account;
import bank.InactiveException;
import bank.OverdrawException;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ConprAccount implements Account {
    private static int id = 0;

    private Lock lock;

    private String number;
    private String owner;
    private double balance;
    private boolean active = true;

    ConprAccount(String owner) {
        this.lock = new ReentrantLock();
        this.owner = owner;
        this.number = "CONPR_ACC_" + id++;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public String getOwner() {
        return owner;
    }

    @Override
    public String getNumber() {
        return number;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    void passivate() {
        active = false;
    }

    @Override
    public void deposit(double amount) throws InactiveException {
        if (!active)
            throw new InactiveException("account not active");
        if (amount < 0)
            throw new IllegalArgumentException("negative amount");
        lock.lock();
        try {
            balance += amount;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void withdraw(double amount) throws InactiveException, OverdrawException {
        if (!active)
            throw new InactiveException("account not active");
        if (amount < 0)
            throw new IllegalArgumentException("negative amount");
        if (balance - amount < 0)
            throw new OverdrawException("account cannot be overdrawn");
        lock.lock();
        try {
            balance -= amount;
        } finally {
            lock.unlock();
        }
    }





}
