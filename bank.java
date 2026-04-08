import java.io.*;
import java.util.*;

// Custom Exceptions
class AmountTooLowException extends Exception {
    AmountTooLowException(String message) {
        super(message);
    }
}

class CustomerIdException extends Exception {
    CustomerIdException(String message) {
        super(message);
    }
}

class SimpleBankApp {

    static final String DATA_FILE = "accounts.txt";

    // Method to add new account
    static void addAccount(Scanner input) {
        try {
            System.out.print("Enter Customer ID (1-20): ");
            int customerId = input.nextInt();

            if (customerId < 1 || customerId > 20) {
                throw new CustomerIdException("Customer ID must be between 1 and 20");
            }

            System.out.print("Enter Name: ");
            String customerName = input.next();

            System.out.print("Enter Initial Balance: ");
            double balance = input.nextDouble();

            if (balance < 1000) {
                throw new AmountTooLowException("Minimum balance should be Rs.1000");
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_FILE, true));
            writer.write(customerId + " | " + customerName + " | " + balance);
            writer.newLine();
            writer.close();

            System.out.println("Account successfully added.");

        } catch (CustomerIdException | AmountTooLowException e) {
            System.out.println("Validation Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected Error occurred.");
        }
    }

    // Method to show all accounts
    static void showAccounts() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE));
            String record;

            System.out.println("\n--- Account Records ---");

            while ((record = reader.readLine()) != null) {
                System.out.println(record);
            }

            reader.close();

        } catch (FileNotFoundException e) {
            System.out.println("No records found yet.");
        } catch (IOException e) {
            System.out.println("Error reading file.");
        }
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Add Account");
            System.out.println("2. View Accounts");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");

            choice = input.nextInt();

            switch (choice) {
                case 1:
                    addAccount(input);
                    break;
                case 2:
                    showAccounts();
                    break;
                case 3:
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid option.");
            }

        } while (choice != 3);

        input.close();
    }
}