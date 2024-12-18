
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class dataEntryForm extends JFrame implements ActionListener {

    JLabel l1, l2, l3, l4, l5, l6, l7, l8, l9, l10;
    JPanel p1, p2;
    JTextField t1, t2, t3, t4, t5, t6, t7, t8, t9, t10;
    JComboBox jb;
    JButton b1;
    JLabel lable;

    dataEntryForm() {
        p1 = new JPanel();
        p1.setLayout(null);
        p1.setBackground(new Color(221, 242, 253));
        p1.setBounds(140, 80, 600, 650);

        p2 = new JPanel();
        p2.setLayout(null);
        p2.setBounds(0, 0, 1000, 60);
        p2.setBackground(new Color(66, 125, 157));
        lable = new JLabel();
        lable.setText("Data Entry Form");
        lable.setForeground(Color.white);
        lable.setFont(new Font("Vardana", Font.BOLD, 30));
        lable.setBounds(330, 15, 300, 40);


        setTitle("Data Entry Form");
        l1 = new JLabel();
        l1.setText("Group No ");
        l1.setBounds(70, 50, 200, 30);
        l1.setFont(new Font("Vardana", Font.BOLD, 22));
        t1 = new JTextField();
        t1.setBounds(300, 50, 250, 30);


        l2 = new JLabel();
        l2.setText("First Name");
        l2.setBounds(70, 100, 200, 30);
        l2.setFont(new Font("Vardana", Font.BOLD, 22));
        t2 = new JTextField();
        t2.setBounds(300, 100, 250, 30);

        l3 = new JLabel();
        l3.setText("Last Name");
        l3.setBounds(70, 150, 200, 30);
        l3.setFont(new Font("Vardana", Font.BOLD, 22));
        t3 = new JTextField();
        t3.setBounds(300, 150, 250, 30);

        l4 = new JLabel();
        l4.setText("PRN No ");
        l4.setBounds(70, 200, 200, 30);
        l4.setFont(new Font("Vardana", Font.BOLD, 22));
        t4 = new JTextField();
        t4.setBounds(300, 200, 250, 30);

        l5 = new JLabel();
        l5.setText("Branch ");
        l5.setBounds(70, 250, 200, 30);
        l5.setFont(new Font("Vardana", Font.BOLD, 22));
        String[] branch = {"CSE", "IT", "ETC", "EE", "MECH", "AIML", "ROBOTICS", "METRX", "CIVIL", "Auto", "Other"};
        jb = new JComboBox(branch);
        jb.setBounds(300, 250, 250, 30);


        l6 = new JLabel();
        l6.setText("Mobile No ");
        l6.setBounds(70, 300, 200, 30);
        l6.setFont(new Font("Vardana", Font.BOLD, 22));
        t6 = new JTextField();
        t6.setBounds(300, 300, 250, 30);

        l7 = new JLabel();
        l7.setText("Email Id ");
        l7.setBounds(70, 350, 200, 30);
        l7.setFont(new Font("Vardana", Font.BOLD, 22));
        t7 = new JTextField();
        t7.setBounds(300, 350, 250, 30);

        l8 = new JLabel();
        l8.setText("Component Name ");
        l8.setBounds(70, 400, 200, 30);
        l8.setFont(new Font("Vardana", Font.BOLD, 22));
        t8 = new JTextField();
        t8.setBounds(300, 400, 250, 30);

        l9 = new JLabel();
        l9.setText("Date of Issue ");
        l9.setBounds(70, 450, 200, 30);
        l9.setFont(new Font("Vardana", Font.BOLD, 22));
        t9 = new JTextField();
        t9.setBounds(300, 450, 250, 30);

        l10 = new JLabel();
        l10.setText("Return Date ");
        l10.setBounds(70, 500, 200, 22);
        l10.setFont(new Font("Vardana", Font.BOLD, 22));
        t10 = new JTextField();
        t10.setBounds(300, 500, 250, 30);


        b1 = new JButton();
        b1.setText("Submit");
        b1.setFont(new Font("Vardana", Font.BOLD, 15));
        b1.setBounds(250, 580, 100, 30);
        b1.setForeground(Color.white);
        b1.setBackground(new Color(66, 125, 157));
        b1.addActionListener(this);

        p2.add(lable);
        p1.add(l1);
        p1.add(t1);
        p1.add(l2);
        p1.add(t2);
        p1.add(l3);
        p1.add(t3);
        p1.add(l4);
        p1.add(t4);
        p1.add(l5);
        p1.add(jb);
        p1.add(l6);
        p1.add(t6);
        p1.add(l7);
        p1.add(t7);
        p1.add(l8);
        p1.add(t8);
        p1.add(l9);
        p1.add(t9);
        p1.add(l10);
        p1.add(t10);
        p1.add(b1);
        add(p2);
        add(p1);

        setSize(900, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);

    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            registerUser();
        }
    }

    public void registerUser() {
        String GroupNo = t1.getText();
        String firstname = t2.getText();
        String lastname = t3.getText();
        String prn = t4.getText();
        String branch = jb.getSelectedItem().toString(); // Get selected branch from JComboBox
        String mobileNo = t6.getText();
        String emailId = t7.getText();
        String componentName = t8.getText();
        String issueDate = t9.getText();
        String returnDate = t10.getText();

        try {
            // Establish a connection to the database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/demo", "root", "");

            // Create a table if not exists
            createTableIfNotExists(connection);

            // Insert data into the table
            String query = "INSERT INTO your_table_name (GroupNo, firstname, lastname, prn, branch, mobileNo, emailId, componentName, issueDate, returnDate) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, GroupNo);
                preparedStatement.setString(2, firstname);
                preparedStatement.setString(3, lastname);
                preparedStatement.setString(4, prn);
                preparedStatement.setString(5, branch);
                preparedStatement.setString(6, mobileNo);
                preparedStatement.setString(7, emailId);
                preparedStatement.setString(8, componentName);
                preparedStatement.setString(9, issueDate);
                preparedStatement.setString(10, returnDate);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Data submitted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                    clearForm();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to submit data", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void createTableIfNotExists(Connection connection) throws SQLException {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS your_table_name (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "GroupNo VARCHAR(255)," +
                "firstname VARCHAR(255)," +
                "lastname VARCHAR(255)," +
                "prn VARCHAR(255)," +
                "branch VARCHAR(255)," +
                "mobileNo VARCHAR(255)," +
                "emailId VARCHAR(255)," +
                "componentName VARCHAR(255)," +
                "issueDate VARCHAR(255)," +
                "returnDate VARCHAR(255)" +
                ")";
        try (PreparedStatement preparedStatement = connection.prepareStatement(createTableQuery)) {
            preparedStatement.executeUpdate();
        }
    }

    private void clearForm() {
        // Clear all text fields after successful submission
        t1.setText("");
        t2.setText("");
        t3.setText("");
        t4.setText("");
        jb.setSelectedIndex(0);
        t6.setText("");
        t7.setText("");
        t8.setText("");
        t9.setText("");
        t10.setText("");
    }
}