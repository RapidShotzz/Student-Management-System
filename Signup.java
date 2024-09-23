package student.management.system;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import com.toedter.calendar.JDateChooser;
import java.awt.event.*;

public class Signup extends JFrame implements ActionListener {

    Random random = new Random();
    String pinnumber = "" + Math.abs((random.nextLong() % 9000L) + 1000L);


    JTextField fnameTextField, lnameTextField, dobTextField, emailTextField, addressTextField, cityTextField, countyTextField, postcodeTextField;
    JButton next;
    JRadioButton male, female;
    JDateChooser dateChooser;
    JComboBox subjects;

    Signup() {

        setLayout(null);

        JLabel register = new JLabel("SYSTEM REGISTRATION");
        register.setFont(new Font("Raleway",Font.BOLD,38));
        register.setBounds(190,20,600,40);
        add(register);

        JLabel personalDetails = new JLabel("Page 1: Personal Details");
        personalDetails.setFont(new Font("Raleway",Font.BOLD,22));
        personalDetails.setBounds(290,80,400,30);
        add(personalDetails);

        JLabel fname = new JLabel("First Name:");
        fname.setFont(new Font("Raleway",Font.BOLD,20));
        fname.setBounds(100,140,200,30);
        add(fname);

        fnameTextField = new JTextField();
        fnameTextField.setFont(new Font("Raleway",Font.BOLD,14));
        fnameTextField.setBounds(300,140,400,30);
        add(fnameTextField);

        JLabel lname = new JLabel("Last Name:");
        lname.setFont(new Font("Raleway",Font.BOLD,20));
        lname.setBounds(100,190,200,30);
        add(lname);

        lnameTextField = new JTextField();
        lnameTextField.setFont(new Font("Raleway",Font.BOLD,14));
        lnameTextField.setBounds(300,190,400,30);
        add(lnameTextField);

        JLabel dob = new JLabel("Date of Birth");
        dob.setFont(new Font("Raleway",Font.BOLD,20));
        dob.setBounds(100,240,200,30);
        add(dob);

        dateChooser = new JDateChooser();
        dateChooser.setBounds(300,240,400,30);
        dateChooser.setForeground(new Color(105,105,105));
        add(dateChooser);


        JLabel gender = new JLabel("Gender:");
        gender.setFont(new Font("Raleway",Font.BOLD,20));
        gender.setBounds(100,290,200,30);
        add(gender);

        male = new JRadioButton("Male");
        male.setBounds(300,290,120,30);
        male.setBackground(Color.WHITE);
        add(male);

        female = new JRadioButton("Female");
        female.setBounds(450,290,120,30);
        female.setBackground(Color.WHITE);
        add(female);

        ButtonGroup gendergroup = new ButtonGroup();
        gendergroup.add(male);
        gendergroup.add(female);


        JLabel email = new JLabel("Email Address:");
        email.setFont(new Font("Raleway",Font.BOLD,20));
        email.setBounds(100,340,200,30);
        add(email);

        emailTextField = new JTextField();
        emailTextField.setFont(new Font("Raleway",Font.BOLD,14));
        emailTextField.setBounds(300,340,400,30);
        add(emailTextField);

        JLabel subject = new JLabel("Subject Taught:");
        subject.setFont(new Font("Raleway",Font.BOLD,20));
        subject.setBounds(100,390,200,30);
        add(subject);

        String valSubject[] = {"Mathematics","English","Biology","Chemistry","Physics","Economics","Computer Science","Psychology","Geography","History"};
        subjects = new JComboBox(valSubject);
        subjects.setBounds(300,390,400,30);
        subjects.setBackground(Color.WHITE);
        add(subjects);

        JLabel address = new JLabel("Address:");
        address.setFont(new Font("Raleway",Font.BOLD,20));
        address.setBounds(100,440,200,30);
        add(address);

        addressTextField = new JTextField();
        addressTextField.setFont(new Font("Raleway",Font.BOLD,14));
        addressTextField.setBounds(300,440,400,30);
        add(addressTextField);

        JLabel city = new JLabel("City:");
        city.setFont(new Font("Raleway",Font.BOLD,20));
        city.setBounds(100,490,200,30);
        add(city);

        cityTextField = new JTextField();
        cityTextField.setFont(new Font("Raleway",Font.BOLD,14));
        cityTextField.setBounds(300,490,400,30);
        add(cityTextField);

        JLabel county = new JLabel("County:");
        county.setFont(new Font("Raleway",Font.BOLD,20));
        county.setBounds(100,540,200,30);
        add(county);

        countyTextField = new JTextField();
        countyTextField.setFont(new Font("Raleway",Font.BOLD,14));
        countyTextField.setBounds(300,540,400,30);
        add(countyTextField);

        JLabel postcode = new JLabel("Postcode:");
        postcode.setFont(new Font("Raleway",Font.BOLD,20));
        postcode.setBounds(100,590,200,30);
        add(postcode);

        postcodeTextField = new JTextField();
        postcodeTextField.setFont(new Font("Raleway",Font.BOLD,14));
        postcodeTextField.setBounds(300,590,400,30);
        add(postcodeTextField);


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
       String fname = fnameTextField.getText();
       String lname = lnameTextField.getText();
       String dob = ((JTextField)dateChooser.getDateEditor().getUiComponent()).getText();
       String gender = null;
       if (male.isSelected()) {
           gender = "Male";
       }
       else if (female.isSelected()) {
           gender = "Female";
       }

       String email = emailTextField.getText();
       String ssubjects = (String) subjects.getSelectedItem();
       String address = addressTextField.getText();
       String city = cityTextField.getText();
       String county = countyTextField.getText();
       String postcode = postcodeTextField.getText();


       try {
           if (fname.equals("")) {
               JOptionPane.showMessageDialog(null,"First Name is required");
           } else if (lname.equals("")) {
               JOptionPane.showMessageDialog(null, "Last Name is required");
           } else if (dob.equals("")) {
               JOptionPane.showMessageDialog(null, "Date Of Birth is required");
           } else if (gender.equals("")) {
               JOptionPane.showMessageDialog(null, "Gender is required");
           } else if (email.equals("")) {
               JOptionPane.showMessageDialog(null, "Email is required");
           } else if (ssubjects.equals("")) {
               JOptionPane.showMessageDialog(null, "Please enter the subject that you are currently expected to teach.");
           } else if (address.equals("")) {
               JOptionPane.showMessageDialog(null, "Address is required");
           } else if (city.equals("")) {
               JOptionPane.showMessageDialog(null, "City is required");
           } else if (county.equals("")) {
               JOptionPane.showMessageDialog(null, "County is required");
           } else if (postcode.equals("")) {
               JOptionPane.showMessageDialog(null, "Postcode is required");
           } else {
               Conn c = new Conn();
               String query = "insert into signup values('"+fname+"', '"+lname+"', '"+dob+"', '"+gender+"', '"+email+"', '"+ssubjects+"', '"+address+"', '"+city+"', '"+county+"', '"+postcode+"', '"+pinnumber+"')";
               c.s.executeUpdate(query);

               setVisible(false);
               new SignupTwo(email, pinnumber).setVisible(true);
           }
       } catch (Exception e) {
           System.out.println(e);
       }
    }

    public static void main(String[] args) {
        new Signup();
    }
}
