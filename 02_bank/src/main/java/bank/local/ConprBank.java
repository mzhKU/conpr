package bank.local;

import bank.Account;
import bank.Bank;
import bank.InactiveException;
import bank.OverdrawException;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

class ConprBank implements Bank {

    /*
    Variante 1: Hold lock on 'to' account in transfer
     */

    // private Map<String, ConprAccount> accounts = new HashMap<String, ConprAccount>();
    // 'final': So all threads see the completely initialized map (addr = malloc, init, instance = addr)
    private final Map<String, ConprAccount> accounts = Collections.synchronizedMap(new HashMap<String, ConprAccount>());

    @Override
    public Set<String> getAccountNumbers() {

        // Can have concurrent modification exception.
        // Set<String> activeAccountNumbers = new HashSet<>();

        Set<String> activeAccountNumbers = new ConcurrentSkipListSet<>( );
        for (ConprAccount acc : accounts.values()) {
            if (acc.isActive()) {
                activeAccountNumbers.add(acc.getNumber());
            }
        }
        return activeAccountNumbers;
    }

    @Override
    public String createAccount(String owner) {
        final ConprAccount a = new ConprAccount(owner);
        accounts.put(a.getNumber(), a);
        return a.getNumber();
    }

    @Override
    public boolean closeAccount(String number) {
        final ConprAccount a = accounts.get(number);
        if (a != null) {
            synchronized(a) {
                if (a.getBalance() != 0 || !a.isActive()) {
                    return false;
                }
                a.passivate();
                return true;
            }
        }
        return false;
    }

    @Override
    public Account getAccount(String number) {
        return accounts.get(number);
    }

    @Override
    public void transfer(Account from, Account to, double amount)
            throws IOException, InactiveException, OverdrawException {

        Account first, second;

        if (from.getNumber().compareTo(to.getNumber()) < 0) {
            first = from; second = to;
        } else {
            first = to; second = from;
        }

        synchronized (first) {
            synchronized (second) {
                if (!to.isActive()) {
                    throw new InactiveException("Is inactive.");
                }
                from.withdraw(amount);
                to.deposit(amount);
            }
        }

        /*
        // Problem could be that 'from' account might close while being
        // redeposited to.
        from.withdraw(amount);
        try {
            to.deposit(amount);
        } catch (Exception e) {
            from.deposit(amount);
            throw e;
        }
        */
    }
}
