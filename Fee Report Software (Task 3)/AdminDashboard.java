

        package fee_software;

import java.util.Scanner;

public class AdminDashboard {
    private AdminOperations adminOps = new AdminOperations();
    private Scanner inputScanner = new Scanner(System.in);

    public void launchPanel() {
        boolean continueSession = true;

        while (continueSession) {
            System.out.println("\n### Administration Console ###");
            System.out.println("1. Sign In");
            System.out.println("2. Create Account");
            System.out.println("3. Exit Panel");
            System.out.print("Select an option: ");
            int choice = inputScanner.nextInt();
            inputScanner.nextLine();

            switch (choice) {
                case 1:
                    authenticateAdmin();
                    break;
                case 2:
                    registerAdmin();
                    break;
                case 3:
                    continueSession = false;
                    System.out.println("Exiting Admin Panel...");
                    break;
                default:
                    System.out.println("Invalid selection! Please choose a valid option.");
            }
        }
    }

    private void authenticateAdmin() {
        System.out.print("Enter Username: ");
        String username = inputScanner.next();
        System.out.print("Enter Secret Code: ");
        String password = inputScanner.next();
        System.out.println();

        if (adminOps.processLogin(username, password)) {
            System.out.println("Welcome! Access Granted.");
            accessAdminFunctions();
        } else {
            System.out.println("Invalid credentials! Please try again.");
        }
    }

    private void registerAdmin() {
        System.out.print("Create Username: ");
        String newUser = inputScanner.next();
        System.out.print("Set Secret Code: ");
        String newPass = inputScanner.next();
        System.out.println();

        if (adminOps.processRegistration(newUser, newPass)) {
            System.out.println("Account Created Successfully!");
        } else {
            System.out.println("Registration Failed! Try a different username.");
        }
    }

    private void accessAdminFunctions() {
        boolean sessionActive = true;

        while (sessionActive) {
            System.out.println("\n--- Admin Control Center ---");
            System.out.println("1. Register Accountant");
            System.out.println("2. Display Accountants");
            System.out.println("3. Modify Accountant Details");
            System.out.println("4. Remove Accountant");
            System.out.println("5. Sign Out");
            System.out.print("Enter your selection: ");
            int selection = inputScanner.nextInt();
            inputScanner.nextLine();
            System.out.println();

            switch (selection) {
                case 1:
                    addNewAccountant();
                    break;
                case 2:
                    listAllAccountants();
                    break;
                case 3:
                    updateAccountantInfo();
                    break;
                case 4:
                    removeAccountant();
                    break;
                case 5:
                    sessionActive = false;
                    System.out.println("Logging Out...");
                    break;
                default:
                    System.out.println("Invalid option! Please choose again.");
            }
        }
    }

    private void addNewAccountant() {
        System.out.println("\n--- Register New Accountant ---");
        System.out.print("Enter Full Name: ");
        String accName = inputScanner.nextLine();
        System.out.print("Enter Contact Email: ");
        String accEmail = inputScanner.nextLine();
        System.out.print("Enter Contact Number: ");
        String accPhone = inputScanner.nextLine();
        System.out.print("Set Password: ");
        String accPass = inputScanner.nextLine();

        FinancialManager newAccountant = new FinancialManager();
        newAccountant.setFullName(accName);
        newAccountant.setContactEmail(accEmail);
        newAccountant.setContactNumber(accPhone);
        newAccountant.setSecretKey(accPass);

        if (adminOps.registerAccountant(newAccountant)) {
            System.out.println("New Accountant Successfully Added!");
        } else {
            System.out.println("Failed to add Accountant! Email might be in use.");
        }
    }

    private void listAllAccountants() {
        adminOps.displayAccountants();
    }

    private void updateAccountantInfo() {
        System.out.print("\nEnter Accountant ID: ");
        int accId = inputScanner.nextInt();
        inputScanner.nextLine();
        System.out.println();

        System.out.print("Update Full Name: ");
        String updatedName = inputScanner.nextLine();
        System.out.print("Update Email Address: ");
        String updatedEmail = inputScanner.nextLine();
        System.out.print("Update Contact Number: ");
        String updatedPhone = inputScanner.nextLine();
        System.out.print("Update Password: ");
        String updatedPass = inputScanner.nextLine();

        adminOps.modifyAccountant(accId, updatedName, updatedEmail, updatedPhone, updatedPass);
    }

    private void removeAccountant() {
        System.out.print("\nEnter Accountant ID for Removal: ");
        int accId = inputScanner.nextInt();
        inputScanner.nextLine();

        adminOps.deleteAccountant(accId);
    }
}
