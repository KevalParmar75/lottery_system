import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class Lottery extends JPanel implements ActionListener {

    private JLabel titleLabel, ticketLabel, winningTicketLabel1, winningTicketLabel2, winningTicketLabel3, resultLabel;
    private JButton generateTicketButton, checkButton;
    private JTextField ticketField, winningTicketField;
    private final Random random = new Random();

    private int generateRandomTicket() {
        return random.nextInt(90000) + 10000; // Range: 10000 to 99999
//        return 0;
    }


    public Lottery() {
        super(); // Call JPanel's constructor

        // Create UI components
        titleLabel = new JLabel("Welcome to the Lottery!", JLabel.CENTER);
        ticketLabel = new JLabel("Your Ticket:");
        winningTicketLabel1 = new JLabel("1st Place:");
        winningTicketLabel2 = new JLabel("2nd Place:");
        winningTicketLabel3 = new JLabel("3rd Place:");
        resultLabel = new JLabel(""); // Initially empty

        generateTicketButton = new JButton("Generate Ticket");
        checkButton = new JButton("Check Results");

        ticketField = new JTextField(20); // Set text field size
        winningTicketField = new JTextField(20); // Set text field size

        // Layout components using GridBagLayout
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // Title
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 0;
        add(titleLabel, c);

        // Ticket Label
        c.gridwidth = 3;
        c.gridy = 1;
        add(ticketLabel, c);

        // Ticket Field
        c.gridx = 1;
        c.gridy = 2;
        add(ticketField, c);

        // Generate Ticket Button
        c.gridy = 4;
        add(generateTicketButton, c);

        // Winning Ticket Label
        c.gridy = 8;
        add(winningTicketLabel1, c);
        add(winningTicketLabel2, c);
        add(winningTicketLabel3, c);



        // Winning Ticket Field (pre-filled for demonstration)
        winningTicketLabel1.setText(String.format("%05d", generateRandomTicket()));
        winningTicketLabel2.setText(String.format("%05d", generateRandomTicket()));
        winningTicketLabel3.setText(String.format("%05d", generateRandomTicket()));
        winningTicketLabel1.setForeground(Color.RED); // Highlight 1st place
        winningTicketLabel2.setForeground(Color.BLUE); // Highlight 2nd place
        winningTicketLabel3.setForeground(Color.GREEN); // Highlight 3rd place
        winningTicketLabel1.setEnabled(false);
        winningTicketLabel2.setEnabled(false);
        winningTicketLabel3.setEnabled(false);
        c.gridy = 3;
        add(winningTicketLabel1, c);
        c.gridy = 5;
        add(winningTicketLabel2, c);
        c.gridy = 6;
        add(winningTicketLabel3, c);

        // Check Button
        c.gridy = 9;
        add(checkButton, c);

        // Result Label
        c.gridy = 7;
        c.gridwidth = 3;
        add(resultLabel, c);

        // Add action listeners
        generateTicketButton.addActionListener(this);
        checkButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == generateTicketButton) {
            // Generate random ticket
            int ticket = generateRandomTicket();
            ticketField.setText(String.format("%05d", ticket)); // Format ticket with leading zeros
        } else if (e.getSource() == checkButton) {
            // Check results
            int userTicket = Integer.parseInt(ticketField.getText());
            ArrayList<Integer> winningTickets = new ArrayList<>();
            winningTickets.add(Integer.parseInt(winningTicketLabel1.getText()));
            winningTickets.add(Integer.parseInt(winningTicketLabel2.getText()));
            winningTickets.add(Integer.parseInt(winningTicketLabel3.getText()));
            int matchBonus = 2; // Example: Match any of the 3 winning numbers with a 2x chance

            int matches = 0;
            String matchResult = "";
            for (int winningTicket : winningTickets) {
                if (userTicket == winningTicket) {
                    matches++;
                    matchResult += winningTicket + " "; // Add matched winning number
                    break;
                }
            }
                String result = matches > 0 ? "Congratulations! You matched " + matches + " winning number(s): " + matchResult.trim() : "Sorry, you didn't win this time.";
                resultLabel.setText(result);
            }
        }

//        public static void main (String[]args){
//            JFrame frame = new JFrame("Lottery System");
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//            // Set full screen mode
//            frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Open in full screen
//            frame.add(new Lottery());
//            frame.pack();
//            frame.setLocationRelativeTo(null); // Center the window
//            frame.setVisible(true);
        }
//    }

