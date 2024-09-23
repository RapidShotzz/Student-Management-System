package student.management.system;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import com.toedter.calendar.JDateChooser;
import java.awt.event.*;

public class SignupTwo extends JFrame implements ActionListener {

    JTextField idNumber,niNumber;
    JButton next;
    JRadioButton syes, sno;
    JComboBox teachtype;
    JCheckBox c1, c2, c3 ,c4, c5;
    String email, pinnumber;

    SignupTwo(String email, String pinnumber) {
        this.email = email;
        this.pinnumber = pinnumber;

        setLayout(null);

        JLabel register = new JLabel("SYSTEM REGISTRATION");
        register.setFont(new Font("Raleway",Font.BOLD,38));
        register.setBounds(190,20,600,40);
        add(register);

        JLabel additionalDetails = new JLabel("Page 2: Additional Details");
        additionalDetails.setFont(new Font("Raleway",Font.BOLD,22));
        additionalDetails.setBounds(290,80,400,30);
        add(additionalDetails);

        JLabel id = new JLabel("Teacher ID Number:");
        id.setFont(new Font("Raleway",Font.BOLD,20));
        id.setBounds(100,140,500,30);
        add(id);

        idNumber = new JTextField();
        idNumber.setFont(new Font("Raleway",Font.BOLD,14));
        idNumber.setBounds(315,140,350,30);
        add(idNumber);

        JLabel years = new JLabel("Year Groups Taught:");
        years.setFont(new Font("Raleway",Font.BOLD,20));
        years.setBounds(100,200,400,30);
        add(years);

        c1 = new JCheckBox("Year 7");
        c1.setBackground(Color.WHITE);
        c1.setFont(new Font("Raleway",Font.BOLD,16));
        c1.setBounds(100,240,200,30);
        add(c1);

        c2 = new JCheckBox("Year 8");
        c2.setBackground(Color.WHITE);
        c2.setFont(new Font("Raleway",Font.BOLD,16));
        c2.setBounds(350,240,200,30);
        add(c2);

        c3 = new JCheckBox("Year 9");
        c3.setBackground(Color.WHITE);
        c3.setFont(new Font("Raleway",Font.BOLD,16));
        c3.setBounds(100,290,200,30);
        add(c3);

        c4 = new JCheckBox("Year 10");
        c4.setBackground(Color.WHITE);
        c4.setFont(new Font("Raleway",Font.BOLD,16));
        c4.setBounds(350,290,200,30);
        add(c4);

        c5 = new JCheckBox("Year 11");
        c5.setBackground(Color.WHITE);
        c5.setFont(new Font("Raleway",Font.BOLD,16));
        c5.setBounds(100,340,200,30);
        add(c5);

        JLabel dbs = new JLabel("Valid DBS:");
        dbs.setFont(new Font("Raleway",Font.BOLD,20));
        dbs.setBounds(100,400,500,30);
        add(dbs);

        syes = new JRadioButton("Yes");
        syes.setBounds(280,400,100,30);
        syes.setBackground(Color.WHITE);
        add(syes);

        sno = new JRadioButton("No");
        sno.setBounds(430,400,100,30);
        sno.setBackground(Color.WHITE);
        add(sno);

        ButtonGroup dbsGroup = new ButtonGroup();
        dbsGroup.add(syes);
        dbsGroup.add(sno);

        JLabel ni = new JLabel("NI Number:");
        ni.setFont(new Font("Raleway",Font.BOLD,20));
        ni.setBounds(100,475,500,30);
        add(ni);

        niNumber = new JTextField();
        niNumber.setFont(new Font("Raleway",Font.BOLD,14));
        niNumber.setBounds(300,475,350,30);
        add(niNumber);

        JLabel type = new JLabel("Teaching Type:");
        type.setFont(new Font("Raleway",Font.BOLD,20));
        type.setBounds(100,550,500,30);
        add(type);

        String valType[] = {"Permanent","Supply","Other"};
        teachtype = new JComboBox(valType);
        teachtype.setBounds(300,550,400,30);
        teachtype.setBackground(Color.WHITE);
        add(teachtype);


        next = new JButton("Next");
        next.setBackground(Color.WHITE);
        next.setForeground(Color.BLACK);
        next.setFont(new Font("Raleway",Font.BOLD,14));
        next.setBounds(620,660,80,30);
        next.addActionListener(this);
        add(next);

        getContentPane().setBackground(Color.WHITE);

        setSize(850,800);
        setLocation(350,10);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        String id = idNumber.getText();
        String years = "";
        if (c1.isSelected()) {
            years = years + "Year 7";
        } else if (c2.isSelected()) {
            years = years + " Year 8";
        } else if (c3.isSelected()) {
            years = years + " Year 9";
        } else if (c4.isSelected()) {
            years = years + " Year 10";
        } else if (c5.isSelected()) {
            years = years + " Year 11";
        }

        String dbs = null;
        if (syes.isSelected()) {
            dbs = "Yes";
        } else if (sno.isSelected()) {
            dbs = "No";
        }
        String ni = niNumber.getText();
        String type = (String) teachtype.getSelectedItem();

        try {
            if (id.equals("")) {
                JOptionPane.showMessageDialog(null, "First Name is required");
            } else if (years.equals("")) {
                JOptionPane.showMessageDialog(null, "Last Name is required");
            } else if (dbs.equals("")) {
                JOptionPane.showMessageDialog(null, "Date Of Birth is required");
            } else if (ni.equals("")) {
                JOptionPane.showMessageDialog(null, "Gender is required");
            } else if (type.equals("")) {
                JOptionPane.showMessageDialog(null, "Email is required");
            } else {
                Conn c = new Conn();
                String query = "insert into signuptwo values('"+id+"', '"+years+"', '"+dbs+"', '"+ni+"', '"+type+"', '"+pinnumber+"' )";
                c.s.executeUpdate(query);
                setVisible(false);
                new SignupThree(this.email,this.pinnumber).setVisible(true);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        new SignupTwo("","");
    }
}
