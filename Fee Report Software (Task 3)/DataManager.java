package fee_software;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataManager {
    private Connection databaseConnection;

    public DataManager() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/payment_management","root","password");
        }
        catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver missing: " + e.getMessage());
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Database connection failed");
        }
    }

    public boolean verifyLogin(String username, String passcode) {
        String sqlQuery = "SELECT * FROM admin WHERE username = ? AND password = ?";
        try (PreparedStatement preparedStatement = databaseConnection.prepareStatement(sqlQuery)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, passcode);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean registerAdmin(String username, String passcode) {
        String sqlQuery = "INSERT INTO admin (username,password) VALUES(?,?)";

        try (PreparedStatement preparedStatement = databaseConnection.prepareStatement(sqlQuery)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, passcode);
            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addFinanceOfficer(FinanceOfficer officer) {
        String sqlQuery = "INSERT INTO finance_officer (name,email,contact,password) VALUES(?,?,?,?)";

        try (PreparedStatement preparedStatement = databaseConnection.prepareStatement(sqlQuery)) {
            preparedStatement.setString(1, officer.getName());
            preparedStatement.setString(2, officer.getEmail());
            preparedStatement.setString(3, officer.getContact());
            preparedStatement.setString(4, officer.getPassword());
            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void displayFinanceOfficers() {
        String sqlQuery = "SELECT * FROM finance_officer";

        try (Statement statement = databaseConnection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String email = resultSet.getString(3);
                String contact = resultSet.getString(4);
                String password = resultSet.getString(5);

                System.out.println("ID: " + id + ", Name: " + name + ", Email: " + email + ", Contact: " + contact + ", Password: " + password + ".");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void modifyFinanceOfficer(int id, String newName, String newEmail, String newContact, String newPassword) {
        String sqlQuery = "UPDATE finance_officer SET name = ?, email = ?, contact = ?, password = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = databaseConnection.prepareStatement(sqlQuery)) {
            preparedStatement.setString(1, newName);
            preparedStatement.setString(2, newEmail);
            preparedStatement.setString(3, newContact);
            preparedStatement.setString(4, newPassword);
            preparedStatement.setInt(5, id);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("\nSuccessfully Updated");
            }
            else {
                System.out.println("\nInvalid ID");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeFinanceOfficer(int id) {
        String sqlQuery = "DELETE FROM finance_officer WHERE id = ?";

        try (PreparedStatement preparedStatement = databaseConnection.prepareStatement(sqlQuery)) {
            preparedStatement.setInt(1, id);
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("\nRecord deleted successfully");
            }
            else {
                System.out.println("\nInvalid ID");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
