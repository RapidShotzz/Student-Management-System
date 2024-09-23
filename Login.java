package student.management.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.*;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {

    JButton login, signup, clear;
    JTextField emailTextField;
    JPasswordField pinTextField;

    Login() {
        setTitle("Admin");

        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/teach.png"));
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel label = new JLabel(i3);
        label.setBounds(70, 10, 100, 100);
        add(label);

        JLabel text = new JLabel("Welcome to the Student Management System");
        text.setFont(new Font("Osward", Font.BOLD, 25));
        text.setBounds(200, 40, 650, 40);
        add(text);

        JLabel email = new JLabel("Email:");
        email.setFont(new Font("Raleway", Font.BOLD, 28));
        email.setBounds(120, 150, 150, 30);
        add(email);

        emailTextField = new JTextField();
        emailTextField.setBounds(300, 150, 250, 30);
        emailTextField.setFont(new Font("Arial", Font.BOLD, 14));
        add(emailTextField);

        JLabel pin = new JLabel("PIN:");
        pin.setFont(new Font("Raleway", Font.BOLD, 28));
        pin.setBounds(120, 220, 250, 30);
        add(pin);

        pinTextField = new JPasswordField();
        pinTextField.setBounds(300, 220, 250, 30);
        pinTextField.setFont(new Font("Arial", Font.BOLD, 14));
        add(pinTextField);

        login = new JButton("SIGN IN");
        login.setBounds(300, 300, 100, 30);
        login.setBackground(Color.WHITE);
        login.setForeground(Color.BLACK);
        login.addActionListener(this);
        add(login);

        clear = new JButton("CLEAR");
        clear.setBounds(430, 300, 100, 30);
        clear.setBackground(Color.WHITE);
        clear.setForeground(Color.BLACK);
        clear.addActionListener(this);
        add(clear);

        signup = new JButton("SIGN UP");
        signup.setBounds(300, 350, 230, 30);
        signup.setBackground(Color.WHITE);
        signup.setForeground(Color.BLACK);
        signup.addActionListener(this);
        add(signup);


        getContentPane().setBackground(Color.WHITE);

        setSize(800, 480);
        setVisible(true);
        setLocation(350, 200); // moves the frame
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == clear) {
            emailTextField.setText("");
            pinTextField.setText("");

        }
        else if (ae.getSource() == signup) {
            setVisible(false);
            new Signup().setVisible(true);
        }
        else if (ae.getSource() == login) {
            Conn conn = new Conn();
            String email = emailTextField.getText();
            String pinnumber = pinTextField.getText();
            String query = "select * from login where email = '"+email+"' and pinnumber = '"+pinnumber+"'";

            try {
                ResultSet rs = conn.s.executeQuery(query);
                if (rs.next()) {
                    setVisible(false);
                    new Homepage(pinnumber);
                } else {
                    JOptionPane.showMessageDialog(null,"Incorrect Email or Pin");
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }

    }

    public static void main(String[] args) {
        new Login();
    }
}
