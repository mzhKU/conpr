/*
 * Copyright (c) 2019 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved.
 */

package bank.local;

/* Simple Server -- not thread safe */

import java.io.IOException;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import bank.Account;
import bank.Bank;
import bank.InactiveException;
import bank.OverdrawException;

public class ConprBankDriver implements bank.BankDriver {
    private volatile ConprBank bank = null;

    @Override
    public void connect(String[] args) {
        bank = new ConprBank();
    }

    @Override
    public void disconnect() {
        bank = null;
    }

    @Override
    public Bank getBank() {
        return bank;
    }
}









