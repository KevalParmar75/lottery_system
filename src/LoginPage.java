import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class LoginPage extends JFrame implements ActionListener {

    // UI components
    private JLabel usernameLabel, passwordLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, cancelButton, registerButton;

    // Database connection details
    private static final String DATABASE_URL = "your database url";
    private static final String USERNAME = "your database name";
    private static final String PASSWORD = "your password";


    public LoginPage() {
        super("Login");

        // Create UI components
        usernameLabel = new JLabel("Username:");
        usernameField = new JTextField(20);

        passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(20);

        loginButton = new JButton("Login");

        cancelButton = new JButton("Cancel");

        registerButton = new JButton("Register");


        // Create the main panel
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // Username label
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        panel.add(usernameLabel, c);

        // Username field
        c.gridx = 1;
        c.gridy = 0;
        panel.add(usernameField, c);

        // Password label
        c.gridx = 0;
        c.gridy = 1;
        panel.add(passwordLabel, c);

        // Password field
        c.gridx = 1;
        c.gridy = 1;
        panel.add(passwordField, c);

        // Login button
        c.gridy = 2;
        panel.add(loginButton, c);

        // registration button
        c.gridy = 3;
        panel.add(registerButton, c);

        // Cancel button
        c.gridx = 1;
        c.gridy = 4;
        panel.add(cancelButton, c);

        // Add action listener (after components are created)
        loginButton.addActionListener(this);
        cancelButton.addActionListener(this);
        registerButton.addActionListener(this);

        // Add panel to frame
        add(panel);

        // Set frame properties
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    private void handleRegistration(String username, String password) {
        if (isValidUsername(username) && isValidPassword(password)) { // Perform validation
            Connection connection = null;
            PreparedStatement statement = null;
            try {
                // Connect to database (already implemented)
                connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);

                // Prepare SQL query to insert user
                String sql = "INSERT INTO users (username, password) VALUES ('?',? )";
                statement = connection.prepareStatement(sql);
                statement.setString(1, username);
                statement.setString(2, Password(password)); // Hash password

                // Execute query to insert user
                statement.executeUpdate();

                JOptionPane.showMessageDialog(this, "Registration successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Registration failed!", "Error", JOptionPane.ERROR_MESSAGE);
            } finally {
                // Close resources
                try {
                    if (statement != null) {
                        statement.close();
                    }
                    if (connection != null) {
                        connection.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        } else {
            // Display an error message if validation fails (optional)
            JOptionPane.showMessageDialog(this, "Invalid username or password!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private String Password(String password) {
        // Hash password using Bcrypt
        String Password = password;
        return Password;
    }


    private boolean isValidUsername(String username) {
        return username.length() >= 15; // Placeholder for username validation
    }

    private boolean isValidPassword(String password) {
        return password.length() >= 4;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            // Validate login credentials using database
            if (validateLogin(username, password)) {
                // Login successful
                JOptionPane.showMessageDialog(this, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                // Replace this with your next window/action after successful login
//                 dispose(); (optional)
                JFrame lotteryFrame = new JFrame("Lottery System");
                lotteryFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                lotteryFrame.add(new Lottery()); // Add the Lottery panel
                lotteryFrame.pack();
                lotteryFrame.setLocationRelativeTo(null);
                lotteryFrame.setVisible(true);

            } else {
                // Login failed
                JOptionPane.showMessageDialog(this, "Invalid username or password.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == cancelButton) {
            System.exit(0);
        }
    };

    private boolean validateLogin(String username, String password) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // Connect to database
            connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);

            // Prepare SQL query to check credentials
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);

            // Execute query and get results
            resultSet = statement.executeQuery();

            // Check if a user record is found
            return resultSet.next();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources (connection, statement, result set)
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }
}
