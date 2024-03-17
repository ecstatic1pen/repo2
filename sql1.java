import java.sql.*;

public class SocialMediaDB {
    // JDBC URL, username, and password of SQLite database
    private static final String url = "jdbc:sqlite:social_media.db";
    
    // Function to display records from the database
    public static void displayRecords(String tableName) {
        String query = "SELECT * FROM " + tableName;

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            // Iterate over the result set
            while (rs.next()) {
                // Example: printing user_id and username
                System.out.println("User ID: " + rs.getInt("user_id") + ", Username: " + rs.getString("username"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Function to add new records to the database
    public static void addRecord(String tableName, String values) {
        String query = "INSERT INTO " + tableName + " VALUES (" + values + ")";
        
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            
            stmt.executeUpdate(query);
            System.out.println("Record added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Function to update existing records in the database
    public static void updateRecord(String tableName, String setValues, String condition) {
        String query = "UPDATE " + tableName + " SET " + setValues + " WHERE " + condition;
        
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            
            stmt.executeUpdate(query);
            System.out.println("Record updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Function to delete records from the database
    public static void deleteRecord(String tableName, String condition) {
        String query = "DELETE FROM " + tableName + " WHERE " + condition;
        
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            
            stmt.executeUpdate(query);
            System.out.println("Record deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Register the JDBC driver
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }

        // Example usage
        // Display records
        displayRecords("Users");

        // Add a new user
        addRecord("Users", "'1', 'example_user', 'example@example.com', 'password123', 'example_profile.jpg', 'Hello, I am a new user!', CURRENT_TIMESTAMP");

        // Update user bio
        updateRecord("Users", "bio = 'Updated bio!'", "user_id = 1");

        // Delete user
        deleteRecord("Users", "user_id = 1");

        // Display records after operations
        displayRecords("Users");
    }
}
