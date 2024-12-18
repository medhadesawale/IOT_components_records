import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class userloginpage extends JFrame implements ActionListener{
    JButton b1,b2;
    JTextField t1,t2;
    userloginpage(){

        JPanel panel1=new JPanel();
        panel1.setLayout(null);
        panel1.setBounds(0,0,1600,110);
        panel1.setBackground(new Color(66, 125, 157));

        ImageIcon I2=new ImageIcon("src/rit.png");
        JLabel l4=new JLabel(I2);
        l4.setBounds(10,5,222,100);

        JPanel panel3=new JPanel();
        panel3.setLayout(null);
        panel3.setBounds(0,750,1600,20);
        panel3.setBackground(new Color(66, 125, 157));

        JLabel l1=new JLabel();
        l1.setText("Admin Login");
        l1.setHorizontalAlignment(JLabel.CENTER);
        l1.setVerticalAlignment(JLabel.CENTER);
        l1.setForeground(Color.WHITE);

        JPanel panel2=new JPanel();
        panel2.setLayout(null);
        panel2.setBackground(new Color(221, 242, 253));
        panel2.setBounds(350,150,800,550);

        ImageIcon I1=new ImageIcon("src/back3.png");
        JLabel label=new JLabel(I1);
        label.setBounds(30,50,300,450);

        JLabel l2=new JLabel();
        l2.setText("Username");
        l2.setForeground(Color.BLACK);
        l2.setFont(new Font("Poppins",Font.BOLD,22));
        l2.setBounds(380,150,150,30);

        JLabel l3=new JLabel();
        l3.setText("Password");
        l3.setForeground(Color.BLACK);
        l3.setFont(new Font("Poppins",Font.BOLD,22));
        l3.setBounds(380,250,150,30);

        t1=new JTextField();
        t1.setBounds(550,150,200,30);

        t2=new JTextField();
        t2.setBounds(550,250,200,30);

        b1=new JButton("Reset");
        b1.setForeground(Color.white);
        b1.setFont(new Font("Verdana",Font.PLAIN,20));
        b1.setBounds(450,350,100,30);
        b1.setBackground(new Color(66, 125, 157));
        b1.addActionListener(this);

        b2=new JButton("Login");
        b2.setForeground(Color.white);
        b2.setFont(new Font("Verdana",Font.PLAIN,20));
        b2.setBounds(600,350,100,30);
        b2.setBackground(new Color(66, 125, 157));
        b2.addActionListener(this);

        JLabel l5=new JLabel();
        l5.setLayout(null);
        l5.setText("ADMIN LOGIN");
        l5.setFont(new Font("Poppins",Font.BOLD,18));
        l5.setForeground(Color.WHITE);
        l5.setBounds(200,751,50,18);


        panel2.add(label);
        panel2.add(l2);
        panel2.add(l3);
        panel2.add(t1);
        panel2.add(t2);
        panel2.add(b1);
        panel2.add(b2);
        panel1.add(l4);
        panel3.add(l5);
        add(panel1);
        add(panel2);
        add(panel3);
        //setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            t1.setText(null);
            t2.setText(null);
        }
        if (e.getSource() == b2) {
            String username = t1.getText().toString();
            String password = t2.getText().toString();

            if (validateLogin(username, password)) {
                // Successful login
                secondPage obj1 = new secondPage();
                dispose();
            } else {
                // Invalid credentials
                JOptionPane.showMessageDialog(this, "Invalid username or password", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private boolean validateLogin(String username, String password) {
        try {
            // Establish a connection
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/demo", "root", "");

            // Execute a query
            String query = "SELECT * FROM login WHERE Username = ? AND Password = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);

                // Check the result
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    return resultSet.next(); // If there is a result, the login is valid
                }
            }
        } catch (SQLException ex) {
            //ex.printStackTrace();
            return false; // Handle exceptions appropriately
        }
    }

    public static void main(String[] args) throws SQLException {
        Database d = new Database();
        userloginpage obj=new userloginpage();
    }
}