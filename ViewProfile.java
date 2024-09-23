package student.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class ViewProfile extends JFrame implements ActionListener {

    String pinnumber;
    JLabel fnameLabel, lnameLabel, dobLabel, genderLabel, emailLabel, subjectLabel, addressLabel, cityLabel, countyLabel, postcodeLabel;
    JButton back;


    ViewProfile(String pinnumber) {
        this.pinnumber = pinnumber;

        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/calculus.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900,900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0,0,900,900);
        add(image);

        JLabel title = new JLabel("Profile Information");
        title.setFont(new Font("Raleway", Font.BOLD, 30));
        title.setBounds(300, 50, 300, 50);
        title.setForeground(Color.WHITE);
        image.add(title);

        JLabel fname = new JLabel("First Name:");
        fname.setFont(new Font("Raleway", Font.BOLD, 20));
        fname.setBounds(100, 150, 200, 30);
        fname.setForeground(Color.WHITE);
        image.add(fname);

        fnameLabel = new JLabel();
        fnameLabel.setFont(new Font("Raleway", Font.PLAIN, 20));
        fnameLabel.setBounds(300, 150, 400, 30);
        fnameLabel.setForeground(Color.WHITE);
        image.add(fnameLabel);

        JLabel lname = new JLabel("Last Name:");
        lname.setFont(new Font("Raleway", Font.BOLD, 20));
        lname.setBounds(100, 200, 200, 30);
        lname.setForeground(Color.WHITE);
        image.add(lname);

        lnameLabel = new JLabel();
        lnameLabel.setFont(new Font("Raleway", Font.PLAIN, 20));
        lnameLabel.setBounds(300, 200, 400, 30);
        lnameLabel.setForeground(Color.WHITE);
        image.add(lnameLabel);

        JLabel dob = new JLabel("Date of Birth:");
        dob.setFont(new Font("Raleway", Font.BOLD, 20));
        dob.setBounds(100, 250, 200, 30);
        dob.setForeground(Color.WHITE);
        image.add(dob);

        dobLabel = new JLabel();
        dobLabel.setFont(new Font("Raleway", Font.PLAIN, 20));
        dobLabel.setBounds(300, 250, 400, 30);
        dobLabel.setForeground(Color.WHITE);
        image.add(dobLabel);

        JLabel gender = new JLabel("Gender:");
        gender.setFont(new Font("Raleway", Font.BOLD, 20));
        gender.setBounds(100, 300, 200, 30);
        gender.setForeground(Color.WHITE);
        image.add(gender);

        genderLabel = new JLabel();
        genderLabel.setFont(new Font("Raleway", Font.PLAIN, 20));
        genderLabel.setBounds(300, 300, 400, 30);
        genderLabel.setForeground(Color.WHITE);
        image.add(genderLabel);

        JLabel email = new JLabel("Email:");
        email.setFont(new Font("Raleway", Font.BOLD, 20));
        email.setBounds(100, 350, 200, 30);
        email.setForeground(Color.WHITE);
        image.add(email);

        emailLabel = new JLabel();
        emailLabel.setFont(new Font("Raleway", Font.PLAIN, 20));
        emailLabel.setBounds(300, 350, 400, 30);
        emailLabel.setForeground(Color.WHITE);
        image.add(emailLabel);

        JLabel subject = new JLabel("Subject Taught:");
        subject.setFont(new Font("Raleway", Font.BOLD, 20));
        subject.setBounds(100, 400, 200, 30);
        subject.setForeground(Color.WHITE);
        image.add(subject);

        subjectLabel = new JLabel();
        subjectLabel.setFont(new Font("Raleway", Font.PLAIN, 20));
        subjectLabel.setBounds(300, 400, 400, 30);
        subjectLabel.setForeground(Color.WHITE);
        image.add(subjectLabel);

        JLabel address = new JLabel("Address:");
        address.setFont(new Font("Raleway", Font.BOLD, 20));
        address.setBounds(100, 450, 200, 30);
        address.setForeground(Color.WHITE);
        image.add(address);

        addressLabel = new JLabel();
        addressLabel.setFont(new Font("Raleway", Font.PLAIN, 20));
        addressLabel.setBounds(300, 450, 400, 30);
        addressLabel.setForeground(Color.WHITE);
        image.add(addressLabel);

        JLabel city = new JLabel("City:");
        city.setFont(new Font("Raleway", Font.BOLD, 20));
        city.setBounds(100, 500, 200, 30);
        city.setForeground(Color.WHITE);
        image.add(city);

        cityLabel = new JLabel();
        cityLabel.setFont(new Font("Raleway", Font.PLAIN, 20));
        cityLabel.setBounds(300, 500, 400, 30);
        cityLabel.setForeground(Color.WHITE);
        image.add(cityLabel);

        JLabel county = new JLabel("County:");
        county.setFont(new Font("Raleway", Font.BOLD, 20));
        county.setBounds(100, 550, 200, 30);
        county.setForeground(Color.WHITE);
        image.add(county);

        countyLabel = new JLabel();
        countyLabel.setFont(new Font("Raleway", Font.PLAIN, 20));
        countyLabel.setBounds(300, 550, 400, 30);
        countyLabel.setForeground(Color.WHITE);
        image.add(countyLabel);

        JLabel postcode = new JLabel("Postcode:");
        postcode.setFont(new Font("Raleway", Font.BOLD, 20));
        postcode.setBounds(100, 600, 200, 30);
        postcode.setForeground(Color.WHITE);
        image.add(postcode);

        postcodeLabel = new JLabel();
        postcodeLabel.setFont(new Font("Raleway", Font.PLAIN, 20));
        postcodeLabel.setBounds(300, 600, 400, 30);
        postcodeLabel.setForeground(Color.WHITE);
        image.add(postcodeLabel);

        back = new JButton("Back");
        back.setBounds(350,700,200,50);
        back.addActionListener((ActionListener) this);
        image.add(back);

        getProfileDetails();

        setSize(900,900);
        setLocation(300,0);
        setUndecorated(true);
        setVisible(true);
    }

    private void getProfileDetails() {
        Conn conn = new Conn();
        try {
            String query = "select * from signup where pinnumber = '" + pinnumber + "'";
            ResultSet rs = conn.s.executeQuery(query);
            if (rs.next()) {
                fnameLabel.setText(rs.getString("fname"));
                lnameLabel.setText(rs.getString("lname"));
                dobLabel.setText(rs.getString("dob"));
                genderLabel.setText(rs.getString("gender"));
                emailLabel.setText(rs.getString("email"));
                subjectLabel.setText(rs.getString("subjects"));
                addressLabel.setText(rs.getString("address"));
                cityLabel.setText(rs.getString("city"));
                countyLabel.setText(rs.getString("county"));
                postcodeLabel.setText(rs.getString("postcode"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == back) {
            setVisible(false);
            new Homepage(pinnumber).setVisible(true);
        }
    }

    public static void main(String[] args) {
        new ViewProfile("");
    }
}
