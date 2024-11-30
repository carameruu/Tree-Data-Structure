import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Scanner;

public class PasswordManager {

    public static void main(String[] args) {
        // HashMap to store usernames and their corresponding hashed passwords
        HashMap<String, String> passwordStore = new HashMap<>();
        
        // Scanner to read user input
        Scanner scanner = new Scanner(System.in);
        int choice;

        // Main loop for user interaction
        do {
            // Display menu to user
            System.out.println("\nPassword Manager:");
            System.out.println("1. Add User");
            System.out.println("2. Retrieve Password (Hashed)");
            System.out.println("3. Delete User");
            System.out.println("4. View All Users");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    // Add a new user with a hashed password
                    addUser(passwordStore, scanner);
                    break;
                case 2:
                    // Retrieve and display a hashed password for a given username
                    retrievePassword(passwordStore, scanner);
                    break;
                case 3:
                    // Delete a user record from the store
                    deleteUser(passwordStore, scanner);
                    break;
                case 4:
                    // View all usernames (without passwords)
                    viewAllUsers(passwordStore);
                    break;
                case 5:
                    // Exit the application
                    System.out.println("Exiting Password Manager. Stay secure!");
                    break;
                default:
                    // Handle invalid input
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5); // Continue until the user chooses to exit

        scanner.close(); // Close the scanner
    }

    // Method to add a new user with a hashed password
    private static void addUser(HashMap<String, String> passwordStore, Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // Check if the username already exists
        if (passwordStore.containsKey(username)) {
            System.out.println("Username already exists! Please choose another username.");
        } else {
            // Hash the password before storing it
            String hashedPassword = hashPassword(password);
            // Store the hashed password in the HashMap
            passwordStore.put(username, hashedPassword);
            System.out.println("User added successfully.");
        }
    }

    // Method to retrieve and display the hashed password for a given username
    private static void retrievePassword(HashMap<String, String> passwordStore, Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        // Check if the user exists in the store
        if (passwordStore.containsKey(username)) {
            // Display the hashed password
            System.out.println("Hashed Password: " + passwordStore.get(username));
        } else {
            // Inform the user if the username doesn't exist
            System.out.println("User not found.");
        }
    }

    // Method to delete a user from the password store
    private static void deleteUser(HashMap<String, String> passwordStore, Scanner scanner) {
        System.out.print("Enter username to delete: ");
        String username = scanner.nextLine();

        // Remove the user from the store if it exists
        if (passwordStore.remove(username) != null) {
            System.out.println("User deleted successfully.");
        } else {
            // Inform the user if the username doesn't exist
            System.out.println("User not found.");
        }
    }

    // Method to view all registered users (only usernames, not passwords)
    private static void viewAllUsers(HashMap<String, String> passwordStore) {
        // Check if there are any users in the store
        if (passwordStore.isEmpty()) {
            System.out.println("No users found.");
        } else {
            System.out.println("Registered Users:");
            // Display all usernames
            for (String username : passwordStore.keySet()) {
                System.out.println("Username: " + username);
            }
        }
    }

    // Method to hash a password using the SHA-256 algorithm
    private static String hashPassword(String password) {
        try {
            // Get SHA-256 message digest instance
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            // Hash the password
            byte[] hash = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            // Convert each byte to a hexadecimal string
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0'); // Ensure two characters for each byte
                hexString.append(hex);
            }
            return hexString.toString(); // Return the hashed password as a hex string
        } catch (NoSuchAlgorithmException e) {
            // Handle case where SHA-256 algorithm is not available
            System.out.println("Error hashing password.");
            return null;
        }
    }
}
