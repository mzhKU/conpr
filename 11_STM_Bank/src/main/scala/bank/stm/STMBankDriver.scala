package bank.stm

import scala.concurrent.stm._
import scala.collection.JavaConverters._
import bank.Bank
import bank.BankDriver
import bank.Account
import bank.OverdrawException
import bank.InactiveException


class STMBankDriver extends BankDriver {
    def connect(args: Array[String]) {}
    def disconnect() {}
    def getBank(): Bank = STMBank
}


object STMBank extends Bank {
    private val id = Ref(0L)
    private val numberToAccount = Ref(Map[String, STMAccount]())
    
    def createAccount(owner: String): String = atomic { implicit txn =>
        val number = owner + id()
        val acc = new STMAccount(number, owner)
        numberToAccount.transform( _ + (number -> acc))
        id.transform(_ + 1)
        number
    }
    
    def closeAccount(number: String): Boolean =  atomic { implicit txn =>
        numberToAccount().get(number).map(_.deactivate()).getOrElse(false)
    }
    
    def getAccount(number: String): Account = atomic { implicit txn =>
        numberToAccount().get(number).getOrElse(null)
    }
    
    def getAccountNumbers(): java.util.Set[String] = atomic { implicit txn =>
        numberToAccount().filter(_._2.isActive()).map(_._1).toSet.asJava
    }
    
    def transfer(from: Account, to: Account, amount: Double): Unit = atomic { implicit txn =>
        if (amount < 0) { throw new IllegalArgumentException("Amount must be positive.") }
        from.withdraw(amount)
        to.deposit(amount)
    }
}


class STMAccount(number: String, owner: String) extends Account {
    private val balance = Ref(0d)
    private val active = Ref(true)
    
    def getNumber(): String = number
    def getOwner(): String = owner
    def isActive(): Boolean = active.single()
    def deactivate(): Boolean = atomic { implicit txn =>
        if(balance() != 0 || active() == false) false
        else { active() = false; true }
    }
    
    def getBalance(): Double = balance.single.get /* Single operation transaction. */
    
    def deposit(amount: Double): Unit = {
        if (!isActive()) { throw new InactiveException("Account must be active.") }
        if (amount < 0) { throw new IllegalArgumentException("Amount must be positive.") }
        atomic { implicit txt =>
            balance.set(balance.get + amount)
        }
    }
    def withdraw(amount: Double) = {
        if (!isActive()) { throw new InactiveException("Account must be active.") }
        if (amount < 0) { throw new IllegalArgumentException("Amount must be positive.") }
        atomic { implicit txt =>
            if (balance.get-amount < 0) { throw new IllegalArgumentException("Overdraw.") }
            balance.set(balance.get - amount)
        }
    }
}