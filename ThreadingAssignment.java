import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Account {
    String holderName;
    String accountNumber;
    int maxTransactionLimit;
    int balance;

    public Account(String holderName, String accountNumber, int maxTransactionLimit) {
        this.holderName = holderName;
        this.accountNumber = accountNumber;
        this.maxTransactionLimit = maxTransactionLimit;
        this.balance = 0;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public synchronized void deposit(int amount) throws Exception {
        if (amount > maxTransactionLimit) {
            throw new Exception("Maximum Deposit Transaction Limit Violated");
        }
        balance += amount;
    }

    public synchronized void withdraw(int amount) throws Exception {
        if (amount > maxTransactionLimit) {
            throw new Exception("Maximum Withdraw Transaction Limit Violated");
        }
        if (amount > balance) {
            throw new Exception("Insufficient Balance");
        }
        balance -= amount;
    }

    public synchronized int getBalance() {
        return balance;
    }
}

class AccountGenerationThread extends Thread {
    static final Random RANDOM = new Random();
    static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    static final int ACCOUNT_NUMBER_LENGTH = 12;

    @Override
    public void run() {
        for (int i = 0; i < 30; i++) {
            String holderName = generateRandomString(8);
            String accountNumber = generateAccountNumber();
            int maxTransactionLimit = RANDOM.nextInt(1000) + 1;
            Account account = new Account(holderName, accountNumber, maxTransactionLimit);
            Bank.addAccount(account);
            System.out.println("Account generated: " + accountNumber);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = RANDOM.nextInt(ALPHABET.length());
            sb.append(ALPHABET.charAt(index));
        }
        return sb.toString();
    }

    String generateAccountNumber() {
        StringBuilder sb = new StringBuilder(ACCOUNT_NUMBER_LENGTH);
        for (int i = 0; i < ACCOUNT_NUMBER_LENGTH; i++) {
            if (i < 2) {
                sb.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
            } else {
                sb.append(RANDOM.nextInt(10));
            }
        }
        return sb.toString();
    }
}

class TransactionGenerationThread extends Thread {
    String transactionType;
    public Random RANDOM;
    public TransactionGenerationThread(String transactionType) {
        if (!transactionType.equals("deposit") && !transactionType.equals("withdraw")) {
            throw new IllegalArgumentException("Invalid transaction type: " + transactionType);
        }
        this.transactionType = transactionType;
        this.RANDOM = new Random();
    }

    @Override
    public void run() {
        for (int i = 0; i < 500; i++) {
            Account account = Bank.getRandomAccount();
            if(account == null) continue;
            int amount = RANDOM.nextInt((transactionType.equals("deposit") ? 50000 : 100000)) + 1;
            Bank.addTransaction(account, amount, transactionType);
            System.out.println(transactionType.substring(0, 1).toUpperCase() + transactionType.substring(1) +
                    " transaction generated for account " + account.getAccountNumber());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class TransactionProcessingThread extends Thread {
    List<String[]> transactions;
    int processingDelay;

    public TransactionProcessingThread(List<String[]> transactions, int processingDelay) {
        this.transactions = transactions;
        this.processingDelay = processingDelay;
    }

    @Override
    public void run() {
        while (!transactions.isEmpty()) {
            String[] transaction = transactions.remove(0);
            Account account = Bank.getAccount(transaction[0]);
            int amount = Integer.parseInt(transaction[1]);
            String transactionType = transaction[2];
            try {
                if (transactionType.equals("deposit")) {
                    account.deposit(amount);
                } else {
                    account.withdraw(amount);
                }
                System.out.println(transactionType.substring(0, 1).toUpperCase() + transactionType.substring(1) +
                        " transaction processed for account " + account.getAccountNumber());
            } catch (Exception e) {
                System.out.println("Error processing transaction for account " + account.getAccountNumber() +
                        ": " + e.getMessage());
            }
            try {
                Thread.sleep(processingDelay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Bank {
    static List<Account> accounts = new ArrayList<>();
    static List<String[]> depositTransactions = new ArrayList<>();
    static List<String[]> withdrawTransactions = new ArrayList<>();

    public static synchronized void addAccount(Account account) {
        accounts.add(account);
    }

    public static synchronized Account getRandomAccount() {
        Random random = new Random();

        int n = accounts.size();
        if(n<1) return null;
        // System.out.println("Dekhoto error kina: " + n);
        return accounts.get(random.nextInt(n));
    }

    public static synchronized void addTransaction(Account account, int amount, String transactionType) {
        if (transactionType.equals("deposit")) {
            depositTransactions.add(new String[]{account.getAccountNumber(), String.valueOf(amount), transactionType});
        } else {
            withdrawTransactions.add(new String[]{account.getAccountNumber(), String.valueOf(amount), transactionType});
        }
    }

    public static synchronized Account getAccount(String accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

}

public class ThreadingAssignment{
    public static void main(String[] args) {
        AccountGenerationThread accountThread = new AccountGenerationThread();
        TransactionGenerationThread depositThread = new TransactionGenerationThread("deposit");
        TransactionGenerationThread withdrawThread = new TransactionGenerationThread("withdraw");
        TransactionProcessingThread depositProcessor1 = new TransactionProcessingThread(Bank.depositTransactions, 1000);
        TransactionProcessingThread depositProcessor2 = new TransactionProcessingThread(Bank.depositTransactions, 800);
        TransactionProcessingThread withdrawProcessor1 = new TransactionProcessingThread(Bank.withdrawTransactions, 1000);
        TransactionProcessingThread withdrawProcessor2 = new TransactionProcessingThread(Bank.withdrawTransactions, 800);

        accountThread.start();
        depositThread.start();
        withdrawThread.start();
        depositProcessor1.start();
        depositProcessor2.start();
        withdrawProcessor1.start();
        withdrawProcessor2.start();

        try {
            accountThread.join();
            depositThread.join();
            withdrawThread.join();
            depositProcessor1.join();
            depositProcessor2.join();
            withdrawProcessor1.join();
            withdrawProcessor2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
