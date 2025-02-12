package fee_software;

import java.util.Scanner;

public class FinancialOfficerUI {
    private FinancialOfficerService financialOfficerService = new FinancialOfficerService();
    private Scanner scanner = new Scanner(System.in);
    private boolean isLoggedIn = false;
    private int officerId;

    public void initiate() {
        boolean isActive = true;
        while (isActive) {
            System.out.println("\n Financial Officer Login Portal ");
            System.out.print("Would you like to exit? (y/n): ");
            char option = scanner.next().charAt(0);
            scanner.nextLine();
            if (option == 'y' || option == 'Y') {
                isActive = false;
            } else {
                System.out.print("Enter Officer Name: ");
                String username = scanner.nextLine();
                System.out.print("Enter Password: ");
                String userPassword = scanner.nextLine();

                officerId = financialOfficerService.authenticate(username, userPassword);

                if (officerId != 0) {
                    System.out.println("\nAuthentication successful! Welcome, " + username);
                    showMenu();
                } else {
                    System.out.println("\nInvalid credentials. Try again.");
                }
            }
        }
    }

    private void showMenu() {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("\n----- Financial Officer Dashboard -----");
            System.out.println("1. Register Student");
            System.out.println("2. View All Enrolled Students");
            System.out.println("3. Modify Student Details");
            System.out.println("4. Remove Student");
            System.out.println("5. View Outstanding Fees");
            System.out.println("6. Sign Out");

            System.out.print("Select an option: ");
            int selection = scanner.nextInt();
            scanner.nextLine();

            switch (selection) {
                case 1:
                    registerStudent();
                    break;
                case 2:
                    displayAllStudents();
                    break;
                case 3:
                    modifyStudent();
                    break;
                case 4:
                    removeStudent();
                    break;
                case 5:
                    showPendingFees();
                    break;
                case 6:
                    System.out.println("Signing out...");
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid selection. Please try again.");
            }
        }
    }

    private void registerStudent() {
        System.out.println("\n--- Register Student ---");
        System.out.print("Enter Full Name: ");
        String fullName = scanner.nextLine();
        System.out.print("Enter Email Address: ");
        String emailAddress = scanner.nextLine();
        System.out.print("Enter Course Name: ");
        String courseName = scanner.nextLine();
        System.out.print("Enter Total Fee Amount: ");
        double totalFee = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Enter Amount Paid: ");
        double amountPaid = scanner.nextDouble();
        scanner.nextLine();
        double balanceDue = totalFee - amountPaid;
        System.out.print("Enter Residential Address: ");
        String residence = scanner.nextLine();
        System.out.print("Enter Contact Number: ");
        String contactNumber = scanner.nextLine();

        Student student = new Student();

        student.setName(fullName);
        student.setEmail(emailAddress);
        student.setCourse(courseName);
        student.setFee(totalFee);
        student.setPaid(amountPaid);
        student.setDue(balanceDue);
        student.setAddress(residence);
        student.setPhone(contactNumber);

        if (financialOfficerService.addStudent(student, officerId)) {
            System.out.println("\nStudent registration successful!");
        } else {
            System.out.println("\nFailed to register student.");
        }
    }

    private void displayAllStudents() {
        financialOfficerService.viewAllStudents();
    }

    private void modifyStudent() {
        System.out.println("\n--- Modify Student Details ---");
        System.out.print("\nEnter Student ID: ");
        int studentId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\n--- Update Student Information ---");
        System.out.print("Enter New Name: ");
        String updatedName = scanner.nextLine();
        System.out.print("Enter New Email: ");
        String updatedEmail = scanner.nextLine();
        System.out.print("Enter New Course: ");
        String updatedCourse = scanner.nextLine();
        System.out.print("Enter New Fee Amount: ");
        double updatedFee = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Enter New Amount Paid: ");
        double updatedPaid = scanner.nextDouble();
        scanner.nextLine();
        double updatedDue = updatedFee - updatedPaid;
        System.out.print("Enter New Address: ");
        String updatedAddress = scanner.nextLine();
        System.out.print("Enter New Contact Number: ");
        String updatedPhone = scanner.nextLine();

        financialOfficerService.updateStudent(studentId, updatedName, updatedEmail, updatedCourse, updatedFee, updatedPaid, updatedDue, updatedAddress, updatedPhone);
    }

    private void removeStudent() {
        System.out.println("\n--- Remove Student ---");
        System.out.print("\nEnter Student ID to Delete: ");
        int studentId = scanner.nextInt();
        scanner.nextLine();

        financialOfficerService.deleteStudent(studentId);
    }

    private void showPendingFees() {
        System.out.println("\n--- List of Students with Outstanding Fees ---");
        financialOfficerService.listPendingFees();
    }
}
