import java.io.*;
import java.util.*;

class User {
    private String name;
    private String email;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    @Override
    public String toString() {
        return name + "," + email;
    }

    public static User fromString(String data) {
        String[] parts = data.split(",");
        if (parts.length == 2) {
            return new User(parts[0], parts[1]);
        }
        return null;
    }
}

class UserManager {
    private List<User> users;

    public UserManager() {
        this.users = new ArrayList<>();
    }

    public void addUser(String name, String email) {
        users.add(new User(name, email));
    }

    public void saveUsersToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (User user : users) {
                writer.write(user.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving users: " + e.getMessage());
        }
    }

    public void loadUsersFromFile(String filename) {
        users.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                User user = User.fromString(line);
                if (user != null) {
                    users.add(user);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading users: " + e.getMessage());
        }
    }

    public void displayUsers() {
        for (User user : users) {
            System.out.println(user);
        }
    }
}

public class T4 {
    public static void main(String[] args) {
        UserManager manager = new UserManager();
        manager.addUser("Alice", "alice@example.com");
        manager.addUser("Bob", "bob@example.com");

        String filename = "users.txt";
        manager.saveUsersToFile(filename);

        manager.loadUsersFromFile(filename);
        System.out.println("Loaded Users:");
        manager.displayUsers();
    }
}
