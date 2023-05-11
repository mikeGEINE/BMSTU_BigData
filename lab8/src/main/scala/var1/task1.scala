package var1.task1

import scala.util.Random

object Bank:
  def run(): Unit = 
    val account = new BankAccount()
    val threads = Seq(
      new Thread(new Depositor(account)),
      new Thread(new Withdrawer(account)),
      new Thread(new Depositor(account)),
      new Thread(new Withdrawer(account)),
      new Thread(new Depositor(account)),
      new Thread(new Withdrawer(account))
    )
    threads.foreach(_.start())
    threads.foreach(_.join())

  class BankAccount(var balance: Int = 0):
    def deposit(amount: Int): Unit = synchronized {
      balance += amount
      println(s"Deposited $amount, balance is now $balance")
    }

    def withdraw(amount: Int): Unit = synchronized {
      if (balance < amount) 
        println("Insufficient funds")
      else 
        balance -= amount
        println(s"Withdrew $amount, balance is now $balance")
    }

  class Depositor(account: BankAccount) extends Runnable:
    override def run(): Unit = 
      for (_ <- 1 to 5) {
        val amount = Random.nextInt(100)
        account.deposit(amount)
        Thread.sleep(1000)
      }

  class Withdrawer(account: BankAccount) extends Runnable:
    override def run(): Unit = 
      for (_ <- 1 to 5) {
        val amount = Random.nextInt(100)
        account.withdraw(amount)
        Thread.sleep(1000)
      }

def test(): Unit =
  Bank.run()