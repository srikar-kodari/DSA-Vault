package locks;

public class SynchronizedLockExample {

    private static final class BankAccount {
        private int balance;

        BankAccount(int openingBalance) {
            this.balance = openingBalance;
        }

        public synchronized void deposit(int amount) {
            int before = balance;
            sleep(80);
            balance = before + amount;
            System.out.println(Thread.currentThread().getName() + " deposited " + amount + ", balance=" + balance);
        }

        public synchronized void withdraw(int amount) {
            if (balance >= amount) {
                int before = balance;
                sleep(80);
                balance = before - amount;
                System.out.println(Thread.currentThread().getName() + " withdrew " + amount + ", balance=" + balance);
            } else {
                System.out.println(Thread.currentThread().getName() + " insufficient funds for " + amount + ", balance=" + balance);
            }
        }

        public synchronized int getBalance() {
            return balance;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BankAccount account = new BankAccount(500);

        Thread depositor = new Thread(() -> {
            for (int i = 0; i < 4; i++) {
                account.deposit(100);
            }
        }, "depositor");

        Thread withdrawer1 = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                account.withdraw(150);
            }
        }, "withdrawer-1");

        Thread withdrawer2 = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                account.withdraw(120);
            }
        }, "withdrawer-2");

        depositor.start();
        withdrawer1.start();
        withdrawer2.start();

        depositor.join();
        withdrawer1.join();
        withdrawer2.join();

        System.out.println("Final balance=" + account.getBalance());
    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
