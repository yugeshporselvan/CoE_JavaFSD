package fee_software;

import java.util.Scanner;

public class Main_class {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("===== Payment Management System =====");
            System.out.println("1. Administrator Access");
            System.out.println("2. Finance Officer Access");
            System.out.println("3. Exit");
            System.out.print("\nSelect an option: ");

            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1: {
                    AdminPanel adminPanel = new AdminPanel();
                    adminPanel.launch();
                    break;
                }
                case 2: {
                    FinancePortal financePortal = new FinancePortal();
                    financePortal.launch();
                    break;
                }
                case 3: {
                    isRunning = false;
                    break;
                }
                default: {
                    System.out.println("Invalid Selection. Please try again.");
                }
            }
        }
        System.out.println();
        System.out.println(" Thank you for using the system! ");
    }
}


