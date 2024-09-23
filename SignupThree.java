package student.management.system;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class SignupThree extends JFrame implements ActionListener {


    JRadioButton r1, r2, r3, r4;
    JCheckBox c1, c2, c3, c4, c5, c6, c7;
    JButton submit, cancel;
    JComboBox texperience;
    String email, pinnumber;

    SignupThree(String email, String pinnumber) {
        this.email = email;
        this.pinnumber = pinnumber;

        setLayout(null);

        JLabel register = new JLabel("SYSTEM REGISTRATION");
        register.setFont(new Font("Raleway",Font.BOLD,38));
        register.setBounds(190,20,600,40);
        add(register);

        JLabel l1 = new JLabel("Page 3: Account Details");
        l1.setFont(new Font("Raleway",Font.BOLD,22));
        l1.setBounds(280,80,400,40);
        add(l1);

        JLabel experience = new JLabel("Years Of Experience:");
        experience.setFont(new Font("Raleway",Font.BOLD,22));
        experience.setBounds(100,145,500,30);
        add(experience);

        String valExperience[] = {"Less than 1 year", "1-5 years", "5-10 years","Over 10 years"};
        texperience = new JComboBox(valExperience);
        texperience.setBounds(350,147,400,30);
        texperience.setBackground(Color.WHITE);
        add(texperience);


        JLabel eemail = new JLabel("Email Address:");
        eemail.setFont(new Font("Raleway",Font.BOLD,22));
        eemail.setBounds(100,250,200,30);
        add(eemail);

        JLabel emaildetails = new JLabel(email);
        emaildetails.setFont(new Font("Raleway",Font.BOLD,22));
        emaildetails.setBounds(330,250,300,30);
        add(emaildetails);

        JLabel carddetails = new JLabel("Your Sign Up Email");
        carddetails.setFont(new Font("Raleway",Font.BOLD,12));
        carddetails.setBounds(100,280,300,20);
        add(carddetails);

        JLabel pin = new JLabel("PIN:");
        pin.setFont(new Font("Raleway",Font.BOLD,22));
        pin.setBounds(100,350,200,30);
        add(pin);

        JLabel pnumber = new JLabel("XXXX");
        pnumber.setFont(new Font("Raleway",Font.BOLD,22));
        pnumber.setBounds(330,350,300,30);
        add(pnumber);

        JLabel pindetails = new JLabel("Your 4 PIN Number");
        pindetails.setFont(new Font("Raleway",Font.BOLD,12));
        pindetails.setBounds(100,380,300,20);
        add(pindetails);

        JLabel responsibilities = new JLabel("Your Additional Responsibilities:");
        responsibilities.setFont(new Font("Raleway",Font.BOLD,22));
        responsibilities.setBounds(100,450,400,30);
        add(responsibilities);

        c1 = new JCheckBox("Student Examiner");
        c1.setBackground(Color.WHITE);
        c1.setFont(new Font("Raleway",Font.BOLD,16));
        c1.setBounds(100,500,200,30);
        add(c1);

        c2 = new JCheckBox("Recreational Monitor");
        c2.setBackground(Color.WHITE);
        c2.setFont(new Font("Raleway",Font.BOLD,16));
        c2.setBounds(350,500,400,30);
        add(c2);

        c3 = new JCheckBox("Leadership Team");
        c3.setBackground(Color.WHITE);
        c3.setFont(new Font("Raleway",Font.BOLD,16));
        c3.setBounds(100,550,200,30);
        add(c3);

        c4 = new JCheckBox("Activities Coordinator");
        c4.setBackground(Color.WHITE);
        c4.setFont(new Font("Raleway",Font.BOLD,16));
        c4.setBounds(350,550,400,30);
        add(c4);

        c5 = new JCheckBox("Wellbeing Leader");
        c5.setBackground(Color.WHITE);
        c5.setFont(new Font("Raleway",Font.BOLD,16));
        c5.setBounds(100,600,200,30);
        add(c5);

        c6 = new JCheckBox("None");
        c6.setBackground(Color.WHITE);
        c6.setFont(new Font("Raleway",Font.BOLD,16));
        c6.setBounds(350,600,200,30);
        add(c6);

        c7 = new JCheckBox("I hereby declare that the above details are correct to the best of my knowledge.");
        c7.setBackground(Color.WHITE);
        c7.setFont(new Font("Raleway",Font.BOLD,12));
        c7.setBounds(100,680,600,30);
        add(c7);

        submit = new JButton("Submit");
        submit.setBackground(Color.WHITE);
        submit.setForeground(Color.BLACK);
        submit.setFont(new Font("Raleway",Font.BOLD,14));
        submit.setBounds(250,720,100,30);
        submit.addActionListener(this);
        add(submit);

        cancel = new JButton("Cancel");
        cancel.setBackground(Color.WHITE);
        cancel.setForeground(Color.BLACK);
        cancel.setFont(new Font("Raleway",Font.BOLD,14));
        cancel.setBounds(420,720,100,30);
        cancel.addActionListener(this);
        add(cancel);


        getContentPane().setBackground(Color.WHITE);

        setSize(850,820);
        setLocation(350,0);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == cancel) {
            setVisible(false);
            new Login().setVisible(true);
        }
        if (ae.getSource() == submit) {
            String experience = (String) texperience.getSelectedItem();
            String responsibilities = "";
            if (c1.isSelected()) {
                responsibilities = responsibilities + "Student Examiner";
            } else if (c2.isSelected()) {
                responsibilities = responsibilities + " Recreational Monitor";
            } else if (c3.isSelected()) {
                responsibilities = responsibilities + " Leadership Team";
            } else if (c4.isSelected()) {
                responsibilities = responsibilities + " Activities Coordinator";
            } else if (c5.isSelected()) {
                responsibilities = responsibilities + " Wellbeing Leader";
            } else if (c6.isSelected()) {
                responsibilities = responsibilities + " None";
            }


            try {
                if (experience.equals("")) {
                    JOptionPane.showMessageDialog(null,"Years of experience are required.");
                } else {
                    Conn conn = new Conn();
                    String query1 = "insert into signupthree values('"+experience+"','"+responsibilities+"','"+pinnumber+"')";
                    String query2 = "insert into login values('"+email+"','"+pinnumber+"')";
                    conn.s.executeUpdate(query1);
                    conn.s.executeUpdate(query2);

                    JOptionPane.showMessageDialog(null, "Email: " + email + "\n Pin: " + pinnumber);

                    setVisible(false);
                    new Homepage(pinnumber).setVisible(true);
                }
            } catch (Exception e) {
                System.out.println(e);
            }

        }

    }

    public static void main(String[] args) {
        new SignupThree("", "");
    }
}
