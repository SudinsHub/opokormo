import java.util.ArrayList;
import java.util.List;

class Bank {
    private String name;
    private List<Customer> customers;
    
    public Bank(String name) {
        this.name = name;
        customers = new ArrayList<>();
    }
    
    public void addCustomer(Customer customer) {
        customers.add(customer);
    }
    
    public void removeCustomer(Customer customer) {
        customers.remove(customer);
    }
}

class Client {
    protected String name;
    protected String email;
    protected String accountNumber;
    protected String phoneNumber;
    private List<Account> accounts;
    
    public Client(String name, String email, String accountNumber, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.accountNumber = accountNumber;
        this.phoneNumber = phoneNumber;
        this.accounts = new ArrayList<>();
    }
    public void addAccount(Account newAccount){
        this.accounts.add(newAccount);
    }
}

class Employee extends Client {
    private String employeeType;
    
    public Employee(String name, String email, String accountNumber, String phoneNumber, String employeeType) {
        super(name, email, accountNumber, phoneNumber);
        this.employeeType = employeeType;
    }
}

class Customer extends Client {
    private String clientType;
    
    public Customer(String name, String email, String accountNumber, String phoneNumber, String clientType) {
        super(name, email, accountNumber, phoneNumber);
        this.clientType = clientType;
    }
}

class SinglePerson extends Customer {
    private String binNumber;
    
    public SinglePerson(String name, String email, String accountNumber, String phoneNumber, String binNumber) {
        super(name, email, accountNumber, phoneNumber, "SinglePerson");
        this.binNumber = binNumber;
    }
}

class Organization extends Customer {
    private String tinNumber;
    
    public Organization(String name, String email, String accountNumber, String phoneNumber, String tinNumber) {
        super(name, email, accountNumber, phoneNumber, "Organization");
        this.tinNumber = tinNumber;
    }
}

class Account {
    private String accountType;
    private double balance;
    protected String bankName;
    
    public Account(String bankName, String accountType, double balance) {
        this.accountType = accountType;
        this.balance = balance;
        this.bankName = bankName;
    }
    
    public void deposit(double amount) {
        balance += amount;
    }
    
    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        } else {
            System.out.println("Insufficient funds");
            return false;
        }
    }

    public double calculateInterest(int years) {
        if (accountType.equals("Savings")) {
            return balance * 0.025 * years;
        } else if (accountType.equals("Salary")) {
            return balance * 0.02 * years;
        } else {
            return 0;
        }
    }

    public double getBalance(){
        return this.balance;
    }
}

class MoneySendToAccount {
    public static void bkashSend(Account account, double amount) {
        account.deposit(amount);
    }

    public static void eftSend(Account senderAccount, Account receiverAccount, double amount) {
        if (senderAccount.withdraw(amount)) {
            receiverAccount.deposit(amount);
        }
    }

    public static void bankReceiptSend(Account account, double amount) {
        account.deposit(amount);
    }
}

class Withdrawal {
    public static boolean directCheque(Account account, double amount) {
        return account.withdraw(amount);
    }

    public static boolean saveToBkashWallet(Account account, double amount) {
        return account.withdraw(amount);
    }

    public static boolean creditCard(Account account, double amount) {
        return account.withdraw(amount);
    }
}

public class BankMain {
    public static void main(String[] args) {
        // Creating a Bank
        Bank bank = new Bank("Rupali Bank");
        Bank bank2 = new Bank("Sonali Bank");


        // Adding Customers
        SinglePerson customer1 = new SinglePerson("Naimul Islam", "naimul@gmail.com", "123456", "123456789", "12345");
        Organization customer2 = new Organization("XYZ Inc.", "info@xyz.com", "654321", "987654321", "54321");
        bank.addCustomer(customer1);
        bank.addCustomer(customer2);

        // Creating Accounts
        Account account1 = new Account("Rupali Bank", "Savings", 1000);
        Account account2 = new Account("Sonali Bank", "Salary", 2000);
        customer1.addAccount(account1);
        customer1.addAccount(account2);
        customer2.addAccount(new Account("Rupali Bank", "Savings", 5000));

        // Sending Money
        MoneySendToAccount.eftSend(account2, account1, 500);

        // Withdrawal
        Withdrawal.directCheque(account1, 200);
        Withdrawal.creditCard(account2, 300);

        // Printing Account Balances
        System.out.println("Account 1 Balance: " + account1.getBalance());
        System.out.println("Account 2 Balance: " + account2.getBalance());
    }
}
