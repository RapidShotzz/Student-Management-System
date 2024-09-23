package student.management.system;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditProfile extends JFrame implements ActionListener {

    String pinnumber;
    JTextField fnameTextField, lnameTextField, emailTextField, addressTextField, cityTextField, countyTextField, postcodeTextField;
    JButton back, submit;
    JDateChooser dateChooser;
    JRadioButton male, female;
    JComboBox subjects;

    EditProfile(String pinnumber) {
        this.pinnumber = pinnumber;

        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/calculus.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900,900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0,0,900,900);
        add(image);

        JLabel title = new JLabel("Edit Profile");
        title.setFont(new Font("Raleway", Font.BOLD, 30));
        title.setBounds(360, 50, 300, 50);
        title.setForeground(Color.WHITE);
        image.add(title);

        JLabel fname = new JLabel("First Name:");
        fname.setFont(new Font("Raleway", Font.BOLD, 20));
        fname.setBounds(100, 150, 200, 30);
        fname.setForeground(Color.WHITE);
        image.add(fname);

        fnameTextField = new JTextField();
        fnameTextField.setFont(new Font("Raleway", Font.BOLD, 20));
        fnameTextField.setBounds(300, 150, 300, 30);
        image.add(fnameTextField);

        JLabel lname = new JLabel("Last Name:");
        lname.setFont(new Font("Raleway", Font.BOLD, 20));
        lname.setBounds(100, 200, 200, 30);
        lname.setForeground(Color.WHITE);
        image.add(lname);

        lnameTextField = new JTextField();
        lnameTextField.setFont(new Font("Raleway", Font.BOLD, 20));
        lnameTextField.setBounds(300, 200, 300, 30);
        image.add(lnameTextField);


        JLabel dob = new JLabel("Date of Birth:");
        dob.setFont(new Font("Raleway", Font.BOLD, 20));
        dob.setBounds(100, 250, 200, 30);
        dob.setForeground(Color.WHITE);
        image.add(dob);

        dateChooser = new JDateChooser();
        dateChooser.setBounds(300,250,300,30);
        dateChooser.setForeground(new Color(105,105,105));
        image.add(dateChooser);


        JLabel gender = new JLabel("Gender:");
        gender.setFont(new Font("Raleway", Font.BOLD, 20));
        gender.setBounds(100, 300, 200, 30);
        gender.setForeground(Color.WHITE);
        image.add(gender);

        male = new JRadioButton("Male");
        male.setBounds(300,300,120,30);
        male.setBackground(Color.BLACK);
        male.setForeground(Color.WHITE);
        image.add(male);

        female = new JRadioButton("Female");
        female.setBounds(450,300,120,30);
        female.setBackground(Color.BLACK);
        female.setForeground(Color.WHITE);
        image.add(female);

        ButtonGroup gendergroup = new ButtonGroup();
        gendergroup.add(male);
        gendergroup.add(female);

        JLabel email = new JLabel("Email:");
        email.setFont(new Font("Raleway", Font.BOLD, 20));
        email.setBounds(100, 350, 200, 30);
        email.setForeground(Color.WHITE);
        image.add(email);

        emailTextField = new JTextField();
        emailTextField.setFont(new Font("Raleway", Font.BOLD, 20));
        emailTextField.setBounds(300, 350, 300, 30);
        image.add(emailTextField);

        JLabel subject = new JLabel("Subject Taught:");
        subject.setFont(new Font("Raleway", Font.BOLD, 20));
        subject.setBounds(100, 400, 200, 30);
        subject.setForeground(Color.WHITE);
        image.add(subject);

        String valSubject[] = {"Mathematics","English","Biology","Chemistry","Physics","Economics","Computer Science","Psychology","Geography","History"};
        subjects = new JComboBox(valSubject);
        subjects.setBounds(300,400,400,30);
        subjects.setBackground(Color.WHITE);
        subject.setForeground(Color.WHITE);
        image.add(subjects);

        JLabel address = new JLabel("Address:");
        address.setFont(new Font("Raleway", Font.BOLD, 20));
        address.setBounds(100, 450, 200, 30);
        address.setForeground(Color.WHITE);
        image.add(address);

        addressTextField = new JTextField();
        addressTextField.setFont(new Font("Raleway", Font.BOLD, 20));
        addressTextField.setBounds(300, 450, 300, 30);
        image.add(addressTextField);

        JLabel city = new JLabel("City:");
        city.setFont(new Font("Raleway", Font.BOLD, 20));
        city.setBounds(100, 500, 200, 30);
        city.setForeground(Color.WHITE);
        image.add(city);

        cityTextField = new JTextField();
        cityTextField.setFont(new Font("Raleway", Font.BOLD, 20));
        cityTextField.setBounds(300, 500, 300, 30);
        image.add(cityTextField);

        JLabel county = new JLabel("County:");
        county.setFont(new Font("Raleway", Font.BOLD, 20));
        county.setBounds(100, 550, 200, 30);
        county.setForeground(Color.WHITE);
        image.add(county);

        countyTextField = new JTextField();
        countyTextField.setFont(new Font("Raleway", Font.BOLD, 20));
        countyTextField.setBounds(300, 550, 300, 30);
        image.add(countyTextField);

        JLabel postcode = new JLabel("Postcode:");
        postcode.setFont(new Font("Raleway", Font.BOLD, 20));
        postcode.setBounds(100, 600, 200, 30);
        postcode.setForeground(Color.WHITE);
        image.add(postcode);

        postcodeTextField = new JTextField();
        postcodeTextField.setFont(new Font("Raleway", Font.BOLD, 20));
        postcodeTextField.setBounds(300, 600, 300, 30);
        image.add(postcodeTextField);

        back = new JButton("Back");
        back.setBounds(90,700,200,50);
        back.addActionListener((ActionListener) this);
        image.add(back);

        submit = new JButton("Submit");
        submit.setBounds(600,700,200,50);
        submit.addActionListener((ActionListener) this);
        image.add(submit);

        setSize(900,900);
        setLocation(300,0);
        setUndecorated(true);
        setVisible(true);
    }

    public static void main(String[] args) {
         new EditProfile("");
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
       if (ae.getSource() == back) {
           setVisible(false);
           new Homepage(pinnumber).setVisible(true);
       } else if (ae.getSource() == submit) {
           try {
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


                   Conn conn = new Conn();
                   String query1 = "update signup set fname = '" + fname + "' where pinnumber = '" + pinnumber + "'";
                   String query2 = "update signup set lname = '" + lname + "' where pinnumber = '" + pinnumber + "'";
                   String query3 = "update signup set dob = '" + dob + "' where pinnumber = '" + pinnumber + "'";
                   String query4 = "update signup set gender = '" + gender + "' where pinnumber = '" + pinnumber + "'";
                   String query5 = "update signup set email = '" + email + "' where pinnumber = '" + pinnumber + "'";
                   String query6 = "update signup set subjects = '" + ssubjects + "' where pinnumber = '" + pinnumber + "'";
                   String query7 = "update signup set address = '" + address + "' where pinnumber = '" + pinnumber + "'";
                   String query8 = "update signup set city = '" + city + "' where pinnumber = '" + pinnumber + "'";
                   String query9 = "update signup set county = '" + county + "' where pinnumber = '" + pinnumber + "'";
                   String query10 = "update signup set postcode = '" + postcode + "' where pinnumber = '" + pinnumber + "'";

                   String query11 = "update login set email = '" + email + "' where pinnumber = '" + pinnumber + "'";


                   conn.s.executeUpdate(query1);
                   conn.s.executeUpdate(query2);
                   conn.s.executeUpdate(query3);
                   conn.s.executeUpdate(query4);
                   conn.s.executeUpdate(query5);
                   conn.s.executeUpdate(query6);
                   conn.s.executeUpdate(query7);
                   conn.s.executeUpdate(query8);
                   conn.s.executeUpdate(query9);
                   conn.s.executeUpdate(query10);
                   conn.s.executeUpdate(query11);

                   JOptionPane.showMessageDialog(null, "Details Changed Successfully");

                   setVisible(false);
                   new Homepage(pinnumber).setVisible(true);

               }


           } catch (Exception e) {
               System.out.println(e);
           }
       } else {
           setVisible(false);
           new Homepage(pinnumber).setVisible(true);
       }
    }
}
