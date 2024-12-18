import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class secondPage extends JFrame implements ActionListener {
    JButton b1,b2,b3,b4;
    secondPage(){
        // JFrame f1=new JFrame();
        setLayout(null);

        JPanel p1 = new JPanel();
        p1.setBounds(0,0,1600,250);
        p1.setBackground(new Color(66,125,157));

        b1 = new JButton("LIST");//LIST Button to display the list of components
        b2 = new JButton("ADD");//ADD Button to add the components to the list
        b3 = new JButton("UPDATE");//UPDATE Button to make changes in the component list
        b4 = new JButton("DISPLAY");//DISPLAY Button to show the records


        b1.setBounds(750, 200, 90, 40);
        b1.addActionListener(this);
        b2.setBounds(900, 200, 90, 40);
        b2.addActionListener(this);
        b3.setBounds(1050, 200, 90, 40);
        b4.setBounds(1200, 200, 90, 40);
        ImageIcon m=new ImageIcon("src/ritlogo.png");//ADD Image
        JLabel l1=new JLabel(m);
        l1.setBounds(10,10,360,140);
        add(b1);
        add(b2);
        add(b3);
        add(b4);
        add(l1);
        add(p1);

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==b1)
        {
            ComponentRecords componentRecords=new ComponentRecords();
            dispose();
        }

        if(e.getSource()==b2)
        {
            dataEntryForm obj2=new dataEntryForm();
            dispose();
        }
    }
}
