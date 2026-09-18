import java.util.ArrayList;
import java.util.Scanner;

class Account {
    private int accountNumber;
    private String name;
    private int pin;
    private double balance;
    private ArrayList<String> transactions;

    public Account(int accountNumber, String name, int pin, double balance) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.pin = pin;
        this.balance = balance;
        this.transactions = new ArrayList<>();
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getName() {
        return name;
    }

    public boolean checkPin(int enteredPin) {
        return pin == enteredPin;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
        transactions.add("Deposited: Rs. " + amount);
    }

    public boolean withdraw(double amount) {
        if (amount > balance) {
            return false;
        }

        balance -= amount;
        transactions.add("Withdrawn: Rs. " + amount);
        return true;
    }

    public void changePin(int newPin) {
        pin = newPin;
    }

    public void showTransactions() {
        System.out.println("\n========== MINI STATEMENT ==========");

        if (transactions.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            for (String transaction : transactions) {
                System.out.println(transaction);
            }
        }

        System.out.println("=====================================");
    }
}

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        Account account = new Account(
            1001,
            "Student",
            1234,
            10000.00
        );

        System.out.println("================================");
        System.out.println("      ATM MANAGEMENT SYSTEM");
        System.out.println("================================");

        // Login
        System.out.print("Enter Account Number: ");
        int accountNumber = scanner.nextInt();

        if (accountNumber != account.getAccountNumber()) {
            System.out.println("Invalid account number.");
            return;
        }

        boolean loggedIn = false;

        for (int attempt = 1; attempt <= 3; attempt++) {

            System.out.print("Enter PIN: ");
            int pin = scanner.nextInt();

            if (account.checkPin(pin)) {
                loggedIn = true;
                break;
            } else {
                System.out.println("Incorrect PIN.");

                if (attempt < 3) {
                    System.out.println(
                        "Attempts remaining: " + (3 - attempt)
                    );
                }
            }
        }

        if (!loggedIn) {
            System.out.println("Too many failed attempts.");
            System.out.println("Account access denied.");
            return;
        }

        System.out.println("\nLogin successful!");
        System.out.println("Welcome, " + account.getName());

        // ATM Menu
        while (true) {

            System.out.println("\n========== ATM MENU ==========");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Mini Statement");
            System.out.println("5. Change PIN");
            System.out.println("6. Exit");
            System.out.println("==============================");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    // Check balance
                    System.out.println("\n========== BALANCE ==========");
                    System.out.println(
                        "Account Number: "
                        + account.getAccountNumber()
                    );

                    System.out.println(
                        "Available Balance: Rs. "
                        + account.getBalance()
                    );

                    System.out.println("=============================");
                    break;

                case 2:
                    // Deposit
                    System.out.print(
                        "\nEnter amount to deposit: Rs. "
                    );

                    double depositAmount = scanner.nextDouble();

                    if (depositAmount <= 0) {
                        System.out.println(
                            "Amount must be greater than zero."
                        );
                    } else {
                        account.deposit(depositAmount);

                        System.out.println(
                            "Deposit successful!"
                        );

                        System.out.println(
                            "New Balance: Rs. "
                            + account.getBalance()
                        );
                    }

                    break;

                case 3:
                    // Withdraw
                    System.out.print(
                        "\nEnter amount to withdraw: Rs. "
                    );

                    double withdrawAmount = scanner.nextDouble();

                    if (withdrawAmount <= 0) {
                        System.out.println(
                            "Amount must be greater than zero."
                        );
                    } else if (account.withdraw(withdrawAmount)) {

                        System.out.println(
                            "Withdrawal successful!"
                        );

                        System.out.println(
                            "Remaining Balance: Rs. "
                            + account.getBalance()
                        );

                    } else {

                        System.out.println(
                            "Insufficient balance!"
                        );

                        System.out.println(
                            "Available Balance: Rs. "
                            + account.getBalance()
                        );
                    }

                    break;

                case 4:
                    // Mini statement
                    account.showTransactions();
                    break;

                case 5:
                    // Change PIN
                    System.out.print(
                        "\nEnter current PIN: "
                    );

                    int currentPin = scanner.nextInt();

                    if (!account.checkPin(currentPin)) {
                        System.out.println(
                            "Incorrect current PIN."
                        );
                        break;
                    }

                    System.out.print(
                        "Enter new 4-digit PIN: "
                    );

                    int newPin = scanner.nextInt();

                    if (newPin < 1000 || newPin > 9999) {
                        System.out.println(
                            "PIN must contain exactly 4 digits."
                        );
                    } else {
                        account.changePin(newPin);

                        System.out.println(
                            "PIN changed successfully!"
                        );
                    }

                    break;

                case 6:
                    // Exit
                    System.out.println(
                        "\nThank you for using ATM Management System."
                    );

                    System.out.println(
                        "Have a nice day!"
                    );

                    scanner.close();
                    return;

                default:
                    System.out.println(
                        "Invalid choice. Please try again."
                    );
            }
        }
    }
}