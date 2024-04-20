import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

class BankAccount {
    private String name;
    private String accountNumber;
    private String pin;
    private double balance;
    private ArrayList<Transaction> transactions;

    public BankAccount(String name, String accountNumber, String pin, double balance) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = balance;
        this.transactions = new ArrayList<>();
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    // Method to add transaction
    public void addTransaction(String type, double amount) {
        Transaction transaction = new Transaction(type, amount);
        transactions.add(transaction);
    }
}

class Transaction {
    private String type;
    private double amount;
    private Date timestamp;

    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
        this.timestamp = new Date();
    }

    @Override
    public String toString() {
        return timestamp + " - " + type + ": $" + amount;
    }
}

public class BankSystem extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private CreateAccountPage createAccountPage;
    private BankMenuPage bankMenuPage;
    private CreditPage creditPage;
    private DebitPage debitPage;
    private CheckBalancePage checkBalancePage;
    private ChangePasswordPage changePasswordPage;
    private PrintPassbookPage printPassbookPage;

    private ArrayList<BankAccount> accounts;

    public BankSystem() {
        setTitle("Bank System");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        accounts = new ArrayList<>();

        createAccountPage = new CreateAccountPage(this);
        bankMenuPage = new BankMenuPage(this);
        creditPage = new CreditPage(this);
        debitPage = new DebitPage(this);
        checkBalancePage = new CheckBalancePage(this);
        changePasswordPage = new ChangePasswordPage(this);
        printPassbookPage = new PrintPassbookPage(this);

        cardPanel.add(createAccountPage, "CREATE_ACCOUNT");
        cardPanel.add(bankMenuPage, "BANK_MENU");
        cardPanel.add(creditPage, "CREDIT");
        cardPanel.add(debitPage, "DEBIT");
        cardPanel.add(checkBalancePage, "CHECK_BALANCE");
        cardPanel.add(changePasswordPage, "CHANGE_PASSWORD");
        cardPanel.add(printPassbookPage, "PRINT_PASSBOOK");

        add(cardPanel);

        showCreateAccountPage();
    }

    public void showCreateAccountPage() {
        cardLayout.show(cardPanel, "CREATE_ACCOUNT");
    }

    public void showBankMenuPage() {
        cardLayout.show(cardPanel, "BANK_MENU");
    }

    public void showCreditPage() {
        cardLayout.show(cardPanel, "CREDIT");
    }

    public void showDebitPage() {
        cardLayout.show(cardPanel, "DEBIT");
    }

    public void showCheckBalancePage() {
        cardLayout.show(cardPanel, "CHECK_BALANCE");
    }

    public void showChangePasswordPage() {
        cardLayout.show(cardPanel, "CHANGE_PASSWORD");
    }

    public void showPrintPassbookPage() {
        cardLayout.show(cardPanel, "PRINT_PASSBOOK");
    }

    public ArrayList<BankAccount> getAccounts() {
        return accounts;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                BankSystem bankSystem = new BankSystem();
                bankSystem.setVisible(true);
            }
        });
    }
}

class CreateAccountPage extends JPanel {
    private JLabel nameLabel, accountLabel, pinLabel, balanceLabel;
    private JTextField nameField, accountField, pinField, balanceField;
    private JButton createButton;
    private BankSystem bankSystem;

    public CreateAccountPage(BankSystem bankSystem) {
        this.bankSystem = bankSystem;

        // Initialize components
        nameLabel = new JLabel("Name:");
        nameField = new JTextField(20);
        accountLabel = new JLabel("Account Number:");
        accountField = new JTextField(20);
        pinLabel = new JLabel("PIN:");
        pinField = new JTextField(20);
        balanceLabel = new JLabel("Initial Balance:");
        balanceField = new JTextField(20);
        createButton = new JButton("Create Account");

        // Layout setup
        setLayout(new GridLayout(5, 2, 5, 10));
        add(nameLabel);
        add(nameField);
        add(accountLabel);
        add(accountField);
        add(pinLabel);
        add(pinField);
        add(balanceLabel);
        add(balanceField);
        add(new JLabel());
        add(createButton);
        // Action listener for createButton
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retrieve input values
                String name = nameField.getText();
                String accountNumber = accountField.getText();
                String pin = pinField.getText();
                double balance = Double.parseDouble(balanceField.getText());

                // Create a new bank account
                BankAccount newAccount = new BankAccount(name, accountNumber, pin, balance);
                bankSystem.getAccounts().add(newAccount);

                // Display a confirmation message
                JOptionPane.showMessageDialog(null, "Account created successfully!");

                // Show the bank menu page
                bankSystem.showBankMenuPage();
            }
        });
    }
}

class BankMenuPage extends JPanel {
    private JButton creditButton, debitButton, checkBalanceButton, printPassbookButton, changePinButton, exitButton;
    private BankSystem bankSystem;

    public BankMenuPage(BankSystem bankSystem) {
        this.bankSystem = bankSystem;

        // Initialize components
        creditButton = new JButton("Credit");
        debitButton = new JButton("Debit");
        checkBalanceButton = new JButton("Check Balance");
        printPassbookButton = new JButton("Print Passbook");
        changePinButton = new JButton("Change Password");
        exitButton = new JButton("Exit");

        // Layout setup
        setLayout(new GridLayout(6, 1, 10, 10));
        add(creditButton);
        add(debitButton);
        add(checkBalanceButton);
        add(printPassbookButton);
        add(changePinButton);
        add(exitButton);

        // Action listeners for buttons
        creditButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bankSystem.showCreditPage();
            }
        });

        debitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bankSystem.showDebitPage();
            }
        });

        checkBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bankSystem.showCheckBalancePage();
            }
        });

        printPassbookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bankSystem.showPrintPassbookPage();
            }
        });

        changePinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bankSystem.showChangePasswordPage();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
               // bankSystem.showCreateAccountPage();
            }
        });
    }
}

class CreditPage extends JPanel {
    private JLabel accountLabel, amountLabel, pinLabel;
    private JTextField accountField, amountField, pinField;
    private JButton creditButton;
    private BankSystem bankSystem;

    public CreditPage(BankSystem bankSystem) {
        this.bankSystem = bankSystem;

        // Initialize components
        accountLabel = new JLabel("Account Number:");
        accountField = new JTextField(20);
        amountLabel = new JLabel("Amount:");
        amountField = new JTextField(20);
        pinLabel = new JLabel("PIN:");
        pinField = new JTextField(20);
        creditButton = new JButton("Credit");

        // Layout setup
        setLayout(new GridLayout(4, 2, 5, 10));
        add(accountLabel);
        add(accountField);
        add(amountLabel);
        add(amountField);
        add(pinLabel);
        add(pinField);
        add(new JLabel());
        add(creditButton);

        // Action listener for creditButton
        creditButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retrieve input values
                String accountNumber = accountField.getText();
                double amount = Double.parseDouble(amountField.getText());
                String pin = pinField.getText();

                // Find the account
                BankAccount account = findAccount(accountNumber);

                // Validate PIN
                if (account != null && account.getPin().equals(pin)) {
                    // Add amount to account balance
                    double newBalance = account.getBalance() + amount;
                    account.setBalance(newBalance);

                    // Add transaction
                    account.addTransaction("Credit", amount);

                    // Display a confirmation message
                    JOptionPane.showMessageDialog(null, "Amount credited successfully!");

                    // Show the bank menu page
                    bankSystem.showBankMenuPage();
                } else {
                    // Display an error message
                    JOptionPane.showMessageDialog(null, "Invalid account number or PIN. Please try again.");
                }
            }
        });
    }

    // Method to find account by account number
    private BankAccount findAccount(String accountNumber) {
        for (BankAccount acc : bankSystem.getAccounts()) {
            if (acc.getAccountNumber().equals(accountNumber)) {
                return acc;
            }
        }
        return null;
    }
}

class DebitPage extends JPanel {
    private JLabel accountLabel, amountLabel, pinLabel;
    private JTextField accountField, amountField, pinField;
    private JButton debitButton;
    private BankSystem bankSystem;

    public DebitPage(BankSystem bankSystem) {
        this.bankSystem = bankSystem;

        // Initialize components
        accountLabel = new JLabel("Account Number:");
        accountField = new JTextField(20);
        amountLabel = new JLabel("Amount:");
        amountField = new JTextField(20);
        pinLabel = new JLabel("PIN:");
        pinField = new JTextField(20);
        debitButton = new JButton("Debit");

        // Layout setup
        setLayout(new GridLayout(4, 2, 5, 10));
        add(accountLabel);
        add(accountField);
        add(amountLabel);
        add(amountField);
        add(pinLabel);
        add(pinField);
        add(new JLabel());
        add(debitButton);

        // Action listener for debitButton
        debitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retrieve input values
                String accountNumber = accountField.getText();
                double amount = Double.parseDouble(amountField.getText());
                String pin = pinField.getText();

                // Find the account
                BankAccount account = findAccount(accountNumber);

                // Validate PIN
                if (account != null && account.getPin().equals(pin)) {
                    // Check if the account has sufficient balance
                    if (account.getBalance() >= amount) {
                        // Subtract amount from account balance
                        double newBalance = account.getBalance() - amount;
                        account.setBalance(newBalance);

                        // Add transaction
                        account.addTransaction("Debit", amount);

                        // Display a confirmation message
                        JOptionPane.showMessageDialog(null, "Amount debited successfully!");

                        // Show the bank menu page
                        bankSystem.showBankMenuPage();
                    } else {
                        // Display an error message if insufficient balance
                        JOptionPane.showMessageDialog(null, "Insufficient balance.");
                    }
                } else {
                    // Display an error message for invalid account number or PIN
                    JOptionPane.showMessageDialog(null, "Invalid account number or PIN. Please try again.");
                }
            }
        });
    }

    // Method to find account by account number
    private BankAccount findAccount(String accountNumber) {
        for (BankAccount acc : bankSystem.getAccounts()) {
            if (acc.getAccountNumber().equals(accountNumber)) {
                return acc;
            }
        }
        return null;
    }
}

class CheckBalancePage extends JPanel {
    private JLabel accountLabel, pinLabel, balanceLabel;
    private JTextField accountField, pinField;
    private JButton checkBalanceButton, backButton;
    private BankSystem bankSystem;

    public CheckBalancePage(BankSystem bankSystem) {
        this.bankSystem = bankSystem;

        // Initialize components
        accountLabel = new JLabel("Account Number:");
        accountField = new JTextField(20);
        pinLabel = new JLabel("PIN:");
        pinField = new JTextField(20);
        balanceLabel = new JLabel();
        checkBalanceButton = new JButton("Check Balance");
        backButton = new JButton("Back to Menu");

        // Layout setup
        setLayout(new GridLayout(5, 2, 5, 10));
        add(accountLabel);
        add(accountField);
        add(pinLabel);
        add(pinField);
        add(new JLabel());
        add(checkBalanceButton);
        add(new JLabel());
        add(balanceLabel);
        add(new JLabel());
        add(backButton);

        // Action listener for checkBalanceButton
        checkBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retrieve input values
                String accountNumber = accountField.getText();
                String pin = pinField.getText();

                // Find the account
                BankAccount account = findAccount(accountNumber);

                // Validate PIN
                if (account != null && account.getPin().equals(pin)) {
                    // Display account balance
                    balanceLabel.setText("Balance: $" + account.getBalance());

                    // Clear input fields
                    accountField.setText("");
                    pinField.setText("");
                } else {
                    // Display an error message
                    JOptionPane.showMessageDialog(null, "Invalid account number or PIN. Please try again.");
                }
            }
        });

        // Action listener for backButton
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bankSystem.showBankMenuPage();
            }
        });
    }

    // Method to find account by account number
    private BankAccount findAccount(String accountNumber) {
        for (BankAccount acc : bankSystem.getAccounts()) {
            if (acc.getAccountNumber().equals(accountNumber)) {
                return acc;
            }
        }
        return null;
    }
}

class ChangePasswordPage extends JPanel {
    private JLabel accountLabel, currentPinLabel, newPinLabel;
    private JTextField accountField, currentPinField, newPinField;
    private JButton changePinButton;
    private BankSystem bankSystem;

    public ChangePasswordPage(BankSystem bankSystem) {
        this.bankSystem = bankSystem;

        // Initialize components
        accountLabel = new JLabel("Account Number:");
        accountField = new JTextField(20);
        currentPinLabel = new JLabel("Current PIN:");
        currentPinField = new JTextField(20);
        newPinLabel = new JLabel("New PIN:");
        newPinField = new JTextField(20);
        changePinButton = new JButton("Change PIN");

        // Layout setup
        setLayout(new GridLayout(4, 2, 5, 10));
        add(accountLabel);
        add(accountField);
        add(currentPinLabel);
        add(currentPinField);
        add(newPinLabel);
        add(newPinField);
        add(new JLabel());
        add(changePinButton);

        // Action listener for changePinButton
        changePinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retrieve input values
                String accountNumber = accountField.getText();
                String currentPin = currentPinField.getText();
                String newPin = newPinField.getText();

                // Find the account
                BankAccount account = findAccount(accountNumber);

                // Validate current PIN
                if (account != null && account.getPin().equals(currentPin)) {
                    // Change PIN
                    account.setPin(newPin);

                    // Display a confirmation message
                    JOptionPane.showMessageDialog(null, "PIN changed successfully!");

                    // Clear input fields
                    accountField.setText("");
                    currentPinField.setText("");
                    newPinField.setText("");

                    // Show the bank menu page
                    bankSystem.showBankMenuPage();
                } else {
                    // Display an error message for invalid current PIN
                    JOptionPane.showMessageDialog(null, "Invalid account number or current PIN. Please try again.");
                }
            }
        });
    }

    // Method to find account by account number
    private BankAccount findAccount(String accountNumber) {
        for (BankAccount acc : bankSystem.getAccounts()) {
            if (acc.getAccountNumber().equals(accountNumber)) {
                return acc;
            }
        }
        return null;
    }
}

class PrintPassbookPage extends JPanel {
    private JTextField accountField, pinField;
    private JButton printButton, backButton;
    private JTextArea passbookTextArea;
    private BankSystem bankSystem;

    public PrintPassbookPage(BankSystem bankSystem) {
        this.bankSystem = bankSystem;

        // Initialize components
        accountField = new JTextField(20);
        pinField = new JTextField(20);
        printButton = new JButton("Print");
        backButton = new JButton("Back to Menu");
        passbookTextArea = new JTextArea(10, 30);
        passbookTextArea.setEditable(false);

        // Layout setup
        setLayout(new BorderLayout());
        JPanel topPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        topPanel.add(new JLabel("Account Number:"));
        topPanel.add(accountField);
        topPanel.add(new JLabel("PIN:"));
        topPanel.add(pinField);
        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(passbookTextArea), BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(printButton);
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Action listeners
        printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String accountNumber = accountField.getText();
                String pin = pinField.getText();
                printPassbook(accountNumber, pin);
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bankSystem.showBankMenuPage();
            }
        });
    }

    private void printPassbook(String accountNumber, String pin) {
        BankAccount account = findAccount(accountNumber);
        if (account != null && account.getPin().equals(pin)) {
            passbookTextArea.setText(""); // Clear previous content
            passbookTextArea.append("Account Number: " + account.getAccountNumber() + "\n");
            passbookTextArea.append("Name: " + account.getName() + "\n");
            passbookTextArea.append("Initial Balance: $" + account.getBalance() + "\n\n");
            passbookTextArea.append("Transactions:\n");
            for (Transaction transaction : account.getTransactions()) {
                passbookTextArea.append(transaction.toString() + "\n");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Invalid account number or PIN. Please try again.");
        }
    }

    private BankAccount findAccount(String accountNumber) {
        for (BankAccount acc : bankSystem.getAccounts()) {
            if (acc.getAccountNumber().equals(accountNumber)) {
                return acc;
            }
        }
        return null;
    }
}
