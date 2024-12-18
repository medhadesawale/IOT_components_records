import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ComponentRecords extends JFrame implements ActionListener{
    JTable table;
    JButton b1=new JButton("Add");
    JButton b2 = new JButton("Go Back");

    JTextField t1=new JTextField();

    JTextField t2=new JTextField();

    JTableHeader header;
    DefaultTableModel model;
    JScrollPane scrollPane;
    String column;
    ComponentRecords()
    {
        JPanel p1=new JPanel();
        p1.setLayout(null);
        p1.setBackground(new Color(155, 190, 200));
        p1.setBounds(0,0,400,650);

        JPanel p2=new JPanel();
        p2.setBackground(Color.white);
        p2.setBounds(400,0,600,650);

        JPanel p3=new JPanel();
        p3.setLayout(null);
        p3.setBackground(new Color(66, 125, 157));
        p3.setBounds(0,0,400,100);

        JLabel l3=new JLabel();
        l3.setText("Component Records");
        l3.setForeground(Color.white);
        l3.setFont(new Font("Poppins",Font.BOLD,28));
        l3.setBounds(40,30,300,30);

        JLabel l1=new JLabel();
        l1.setText("Name");
        l1.setForeground(Color.BLACK);
        l1.setFont(new Font("Poppins",Font.BOLD,22));
        l1.setBounds(40,180,200,30);

        JLabel l2=new JLabel();
        l2.setText("Quantity");
        l2.setForeground(Color.BLACK);
        l2.setFont(new Font("Poppins",Font.BOLD,22));
        l2.setBounds(40,280,200,30);

        t1.setBounds(180,180,150,30);

        t2.setBounds(180,280,150,30);

        b1.setForeground(Color.white);
        b1.setFont(new Font("Verdana",Font.PLAIN,20));
        b1.setBounds(150,400,100,30);
        b1.setBackground(new Color(66, 125, 157));
        b2.setForeground(Color.white);
        b2.setFont(new Font("Verdana",Font.PLAIN,20));
        b2.setBounds(100,450,200,30);
        b2.setBackground(new Color(66, 125, 157));

        p1.add(p3);
        p1.add(l1);
        p1.add(l2);
        p1.add(t1);
        p1.add(t2);
        p1.add(b1);
        p1.add(b2);
        p3.add(l3);
        add(p1);
        add(p2);
        String[] column = new String[]{"Sr no","Name of component","Quantity"};

        model = new DefaultTableModel(column, 0);
        table = new JTable(model);
        table.setBounds(50,75,500,575);
        table.setFont(new Font("Poppins",Font.PLAIN,12));
        header = table.getTableHeader();
        header.setBackground(new Color(221, 242, 253));
        header.setFont(new Font("Poppins",Font.BOLD,14));
        ((DefaultTableCellRenderer)header.getDefaultRenderer()).setHorizontalAlignment(JLabel.LEFT);

        fetchDataFromDatabase();
        scrollPane = new JScrollPane(table);

        p2.setLayout(new BorderLayout());
        p2.add(scrollPane, BorderLayout.CENTER);
        setSize(1000,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        b1.addActionListener(this);
        b2.addActionListener(this);
        setLayout(null);
        setVisible(true);
    }
    private void insertData() {
        String name = t1.getText(); // Get text from the first text field
        String quantity = t2.getText(); // Get text from the second text field

        if (!name.isEmpty() && !quantity.isEmpty()) {
            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/demo", "root", "");
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO componenetrecords (Name_of_component, Quantity) VALUES (?, ?)");

                // Set values for the PreparedStatement parameters
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, quantity);

                int rowsInserted = preparedStatement.executeUpdate();

                if (rowsInserted > 0) {
                    JOptionPane.showMessageDialog(null, "Data inserted successfully");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to insert data");
                }

                preparedStatement.close();
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please fill in all fields");
        }
    }
    private void fetchDataFromDatabase() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/demo", "root", "");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM componenetrecords");

            while (resultSet.next()) {
                int srNo = resultSet.getInt("Sr_no");
                String name = resultSet.getString("Name_of_component");
                String quantity = resultSet.getString("Quantity");

                // Add a new row to the table model
                model.addRow(new Object[]{srNo,name, quantity});
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        ComponentRecords componentRecords=new ComponentRecords();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==b1)
        {
            insertData();
            model.setRowCount(0);
            fetchDataFromDatabase();
        } else if (e.getSource()==b2) {
            secondPage secondPage =new secondPage();
        }
    }
}
