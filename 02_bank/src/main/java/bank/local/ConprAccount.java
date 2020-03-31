package bank.local;

import bank.Account;
import bank.InactiveException;
import bank.OverdrawException;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ConprAccount implements Account {

    // Atomicity and visibility problems
    // --> using AtomicInteger, 'id' will be globally unique.
    // private static int id = 0;
    private static final AtomicInteger id = new AtomicInteger(0);


    private final String number;
    private final String owner;

    private final ReentrantLock lock;

    // 'volatile': Guarantees writing to balance happens-before reading on balance
    private volatile double balance;

    private volatile boolean active = true;

    ConprAccount(String owner) {
        this.lock = new ReentrantLock();
        this.owner = owner;

        // this.number = "CONPR_ACC_" + id++;
        this.number = "CONPR_ACC_" + id.getAndIncrement();
    }

    @Override
    public double getBalance() {

        // 'doubles': 64 bit reading is not atomic.
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

    synchronized void passivate() {
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
