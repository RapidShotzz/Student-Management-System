package student.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Homepage extends JFrame implements ActionListener {

    JButton vprofile, eprofile, manage, timetable, attendance, grades, logout;
    String pinnumber;

    Homepage(String pinnumber) {

        this.pinnumber = pinnumber;
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/calculus.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900,900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0,0,900,900);
        add(image);

        JLabel homepage = new JLabel("Student Management Homepage");
        homepage.setFont(new Font("Raleway",Font.BOLD,30));
        homepage.setBounds(200,50,600,60);
        homepage.setForeground(Color.WHITE);
        image.add(homepage);

        vprofile = new JButton("View Profile");
        vprofile.setBounds(110,150,200,50);
        vprofile.addActionListener(this);
        image.add(vprofile);

        eprofile = new JButton("Edit Profile");
        eprofile.setBounds(550,150,200,50);
        eprofile.addActionListener(this);
        image.add(eprofile);

        manage = new JButton("Manage Students");
        manage.setBounds(110,300,200,50);
        manage.addActionListener(this);
        image.add(manage);

        timetable = new JButton("Class Timetable");
        timetable.setBounds(550,300,200,50);
        timetable.addActionListener(this);
        image.add(timetable);

        attendance = new JButton("Attendance Tracker");
        attendance.setBounds(110,450,200,50);
        attendance.addActionListener(this);
        image.add(attendance);

        grades = new JButton("Gradebook");
        grades.setBounds(550,450,200,50);
        grades.addActionListener(this);
        image.add(grades);

        logout = new JButton("Logout");
        logout.setBounds(330,650,200,50);
        logout.addActionListener(this);
        image.add(logout);

        setSize(900,900);
        setLocation(300,0);
        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == logout) {
            setVisible(false);
            new Login().setVisible(true);
        } else if (ae.getSource() == vprofile) {
            setVisible(false);
            new ViewProfile(pinnumber).setVisible(true);
        } else if (ae.getSource() == eprofile) {
            setVisible(false);
            new EditProfile(pinnumber).setVisible(true);
        } else if (ae.getSource() == manage) {
            setVisible(false);
            new ManageStudents(pinnumber).setVisible(true);
        } else if (ae.getSource() == timetable) {
            setVisible(false);
            new ClassTimetable(pinnumber).setVisible(true);
        } else if (ae.getSource() == attendance) {
            setVisible(false);
            new AttendanceTracker(pinnumber).setVisible(true);
        } else if (ae.getSource() == grades) {
            setVisible(false);
            new Gradebook(pinnumber).setVisible(true);
        }
    }

    public static void main(String[] args) {
        new Homepage("");
    }
}
