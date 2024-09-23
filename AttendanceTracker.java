package student.management.system;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class AttendanceTracker extends JFrame implements ActionListener {

    String pinnumber;
    JDateChooser dateChooser;
    JTextField nameTextField, idTextField;
    JCheckBox presentCheckBox;
    JButton markAttendance, viewAttendance, back;

    AttendanceTracker(String pinnumber) {
        this.pinnumber = pinnumber;

        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/calculus.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900,900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0,0,900,900);
        add(image);

        JLabel title = new JLabel("Attendance Tracker");
        title.setFont(new Font("Raleway", Font.BOLD, 30));
        title.setBounds(300, 50, 450, 50);
        title.setForeground(Color.WHITE);
        image.add(title);

        JLabel date = new JLabel("Date");
        date.setFont(new Font("Raleway",Font.BOLD,20));
        date.setForeground(Color.WHITE);
        date.setBounds(130,120,200,50);
        image.add(date);

        dateChooser = new JDateChooser();
        dateChooser.setBounds(260,130,300,30);
        dateChooser.setForeground(new Color(105,105,105));
        image.add(dateChooser);

        JLabel studentName = new JLabel("Student Name:");
        studentName.setFont(new Font("Raleway",Font.BOLD,20));
        studentName.setForeground(Color.WHITE);
        studentName.setBounds(130,230,200,50);
        image.add(studentName);

        nameTextField = new JTextField();
        nameTextField.setFont(new Font("Raleway", Font.BOLD, 20));
        nameTextField.setBounds(320, 240, 300, 30);
        image.add(nameTextField);

        JLabel id = new JLabel("Student ID:");
        id.setFont(new Font("Raleway",Font.BOLD,20));
        id.setForeground(Color.WHITE);
        id.setBounds(130,340,200,50);
        image.add(id);

        idTextField = new JTextField();
        idTextField.setFont(new Font("Raleway", Font.BOLD, 20));
        idTextField.setBounds(300, 350, 300, 30);
        image.add(idTextField);

        JLabel present = new JLabel("Present:");
        present.setFont(new Font("Raleway",Font.BOLD,20));
        present.setForeground(Color.WHITE);
        present.setBounds(130,450,200,50);
        image.add(present);

        presentCheckBox = new JCheckBox("Present");
        presentCheckBox.setFont(new Font("Raleway",Font.BOLD,14));
        presentCheckBox.setForeground(Color.WHITE);
        presentCheckBox.setBounds(300,450,200,50);
        image.add(presentCheckBox);

        markAttendance = new JButton("Mark Attendance");
        markAttendance.setBounds(40,600,200,50);
        markAttendance.addActionListener(this);
        image.add(markAttendance);

        viewAttendance = new JButton("View Attendance");
        viewAttendance.setBounds(300,600,200,50);
        viewAttendance.addActionListener(this);
        image.add(viewAttendance);

        back = new JButton("Back");
        back.setBounds(560,600,200,50);
        back.addActionListener(this);
        image.add(back);

        setSize(900,900);
        setLocation(300,0);
        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == back) {
            setVisible(false);
            new Homepage(pinnumber).setVisible(true);
        } else if (ae.getSource() == markAttendance) {
          String date = ((JTextField)dateChooser.getDateEditor().getUiComponent()).getText();
          String name = nameTextField.getText();
          String id = idTextField.getText();
          boolean isPresent = presentCheckBox.isSelected();

          if (date.equals("")) {
              JOptionPane.showMessageDialog(null,"Please select a date to mark the attendance of a student");
              return;
          } if (name.equals("")) {
              JOptionPane.showMessageDialog(null,"Please enter the name of the student");
              return;
            } if (id.equals("")) {
              JOptionPane.showMessageDialog(null,"Please enter the id of the student");
              return;
            }

          Conn conn = new Conn();
          try {
              String query = "insert into attendance values ('"+date+"', '"+name+"', '"+id+"', '"+isPresent+"', '"+pinnumber+"')";
              int rowsChanged = conn.s.executeUpdate(query);

              if (rowsChanged > 0) {
                  JOptionPane.showMessageDialog(null,"Attendance marked for " + name + " on " + date);
              } else {
                  JOptionPane.showMessageDialog(null,"Failed to mark attendance");
              }

          } catch (Exception e) {
              System.out.println(e);
          }

        } else if (ae.getSource() == viewAttendance) {

            String date = ((JTextField)dateChooser.getDateEditor().getUiComponent()).getText();
            String name = nameTextField.getText();
            String id = idTextField.getText();
            boolean isPresent = presentCheckBox.isSelected();
            Conn conn = new Conn();

            try {
                String query = "select * from attendance where date = '" + date + "'";
                ResultSet rs = conn.s.executeQuery(query);

                if (!rs.isBeforeFirst()) {
                    JOptionPane.showMessageDialog(null,"No attendance records found for " + date);
                    return;
                } else if (date.equals("")) {
                    JOptionPane.showMessageDialog(null,"Please select a date to view the attendance of students");
                    return;
                }

                StringBuilder record = new StringBuilder("Attendance for " + date + ":\n");
                while (rs.next()) {
                    String studentName = rs.getString("name");
                    String studentId = rs.getString("id");
                    boolean is_present = rs.getBoolean("is_present");

                    record.append("Student ").append(studentId).append(" (").append(studentName).append("): ")
                            .append(is_present ? "Present" : "Absent").append("\n");


                }

                JOptionPane.showMessageDialog(null,record.toString());
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public static void main(String[] args) {
        new AttendanceTracker("");
    }
}
