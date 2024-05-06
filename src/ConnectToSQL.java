import java.sql.*;

public class ConnectToSQL {

    public static void main(String[] args) {
        // Replace these with your own database information
        String databaseURL = "jdbc:mysql://localhost:3306/lottery";
        String username = "root";
        String password = "Hrpd@411";

        Connection connection = null;

        try {
            // Load the JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // Get a connection to the database
            connection = DriverManager.getConnection(databaseURL, username, password);

            System.out.println("Successfully connected to the database!");

        } catch (SQLException e) {
            System.out.println("Failed to connect to the database: " + e.getMessage());

        } catch (ClassNotFoundException e) {
            System.out.println("JDBC driver not found: " + e.getMessage());

        } finally {
            // Close the connection if it was opened
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Failed to close connection: " + e.getMessage());
                }
            }
        }
    }
}
