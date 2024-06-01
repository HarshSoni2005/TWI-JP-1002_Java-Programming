import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Account {
    private int accountNumber;
    private int pin;
    private double balance;

    public Account(int accountNumber, int pin, double balance) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public boolean validatePin(int enteredPin) {
        return pin == enteredPin;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
        } else {
            System.out.println("Insufficient balance!");
        }
    }
}

class ATMInterface {
    private static Map<Integer, Account> accounts = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean quit = false;
        while (!quit) {
            System.out.println("Welcome to the ATM!");
            System.out.print("Enter your account number (or 'q' to quit): ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("q")) {
                quit = true;
                continue;
            }

            int accountNumber = Integer.parseInt(input);

            System.out.print("Enter your PIN: ");
            int pin = scanner.nextInt();
            scanner.nextLine(); // Consume the remaining newline character

            Account account = accounts.get(accountNumber);
            if (account == null) {
                System.out.print("Enter initial balance: ");
                double initialBalance = scanner.nextDouble();
                account = new Account(accountNumber, pin, initialBalance);
                accounts.put(accountNumber, account);
                System.out.println("Account created successfully!");
            } else if (!account.validatePin(pin)) {
                System.out.println("Invalid PIN!");
                continue;
            }

            performTransactions(account);
        }
    }

    private static void performTransactions(Account account) {
        boolean quit = false;
        while (!quit) {
            System.out.println("\nChoose a transaction:");
            System.out.println("1. Check balance");
            System.out.println("2. Withdraw cash");
            System.out.println("3. Deposit cash");
            System.out.println("4. Quit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the remaining newline character

            switch (choice) {
                case 1:
                    System.out.println("Current balance: $" + account.getBalance());
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    account.withdraw(withdrawAmount);
                    break;
                case 3:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    break;
                case 4:
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}