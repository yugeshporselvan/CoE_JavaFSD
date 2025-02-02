import java.util.Scanner;

public class T3 {

    public static void processInput() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter a number: ");
            double number = scanner.nextDouble();

            if (number == 0) {
                throw new ArithmeticException("Division by zero is not allowed.");
            }

            double reciprocal = 1 / number;
            System.out.println("Reciprocal: " + reciprocal);

        } catch (ArithmeticException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a valid number.");
        } finally {
            scanner.close();
        }
    }

    public static void main(String[] args) {
        processInput();
    }
}
