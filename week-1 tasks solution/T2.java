class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public synchronized void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println(Thread.currentThread().getName() + " deposited " + amount + ". New balance: " + balance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public synchronized void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println(Thread.currentThread().getName() + " withdrew " + amount + ". New balance: " + balance);
        } else {
            System.out.println(Thread.currentThread().getName() + " attempted to withdraw " + amount + " but insufficient funds.");
        }
    }

    public double getBalance(){
        return balance;
    }
}

class BankUser extends Thread {
    private BankAccount account;
    private boolean isDeposit;
    private double amount;

    public BankUser(BankAccount account, boolean isDeposit, double amount) {
        this.account = account;
        this.isDeposit = isDeposit;
        this.amount = amount;
    }

    @Override
    public void run() {
        if (isDeposit) {
            account.deposit(amount);
        } else {
            account.withdraw(amount);
        }
    }
}

public class T2 {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000);

        Thread user1 = new BankUser(account, true, 500);
        Thread user2 = new BankUser(account, false, 700);
        Thread user3 = new BankUser(account, false, 1200);
        Thread user4 = new BankUser(account, true, 300);

        user1.start();
        user2.start();
        user3.start();
        user4.start();

        try {
            user1.join();
            user2.join();
            user3.join();
            user4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final balance: " + account.getBalance());
    }
}