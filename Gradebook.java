package student.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

public class Gradebook extends JFrame implements ActionListener {

    String pinnumber;
    JTextField idTextField, examTextField;
    JComboBox grades;
    JButton update, back;
    JTable table;

    Gradebook(String pinnumber) {
        this.pinnumber = pinnumber;

        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/calculus.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900,900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0,0,900,900);
        add(image);

        JLabel title = new JLabel("Gradebook");
        title.setFont(new Font("Raleway", Font.BOLD, 30));
        title.setBounds(360, 50, 450, 50);
        title.setForeground(Color.WHITE);
        image.add(title);

        JLabel id = new JLabel("Student ID:");
        id.setFont(new Font("Raleway",Font.BOLD,20));
        id.setForeground(Color.WHITE);
        id.setBounds(130,120,200,50);
        image.add(id);

        idTextField = new JTextField();
        idTextField.setFont(new Font("Raleway", Font.BOLD, 20));
        idTextField.setBounds(300, 130, 300, 30);
        image.add(idTextField);

        JLabel exam = new JLabel("Examination:");
        exam.setFont(new Font("Raleway",Font.BOLD,20));
        exam.setForeground(Color.WHITE);
        exam.setBounds(130,250,200,50);
        image.add(exam);

        examTextField = new JTextField();
        examTextField.setFont(new Font("Raleway", Font.BOLD, 20));
        examTextField.setBounds(300, 260, 300, 30);
        image.add(examTextField);

        JLabel grade = new JLabel("Grade:");
        grade.setFont(new Font("Raleway",Font.BOLD,20));
        grade.setForeground(Color.WHITE);
        grade.setBounds(130,370,200,50);
        image.add(grade);

        String[] valGrades = {"A*", "A", "B", "C", "D", "E", "U"};
        grades = new JComboBox(valGrades);
        grades.setBounds(265,377,300,40);
        image.add(grades);

        update = new JButton("Update Grade");
        update.setBounds(100,732,200,50);
        update.addActionListener(this);
        image.add(update);

        back = new JButton("Back");
        back.setBounds(600,730,200,50);
        back.addActionListener(this);
        image.add(back);

        String[] columnNames = {"Student ID", "Subject", "Grade", "Date"};
        table = new JTable(new Object[0][0],columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(235,450,400,200);
        image.add(scrollPane);

        setSize(900,900);
        setLocation(300,0);
        setUndecorated(true);
        setVisible(true);

        loadGrades();
    }

    private void loadGrades() {
        Conn conn =  new Conn();

        try {
          String query = "select * from grades where pinnumber  = '" + pinnumber + "'";

            ResultSet rs = conn.s.executeQuery(query);

            String[] columnNames = {"Student ID", "Subject","Grade","Date"};
            Object[][] data = new Object[10][4];

            int row = 0;
            while (rs.next()) {
                data[row][0] = rs.getString("id");
                data[row][1] = rs.getString("exam");
                data[row][2] = rs.getString("grade");
                data[row][3] = rs.getDate("date");
                row++;
            }
           table.setModel(new DefaultTableModel(data,columnNames));


        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == back) {
            setVisible(false);
            new Homepage(pinnumber).setVisible(true);
        } else if (ae.getSource() == update) {
            updateGrades();
        }
    }

    private void updateGrades() {
        String id = idTextField.getText();
        String exam = examTextField.getText();
        String sgrade = (String) grades.getSelectedItem();
        Date date = new Date(System.currentTimeMillis());

        Conn conn = new Conn();

        if (id.equals("")) {
            JOptionPane.showMessageDialog(null,"Please enter the student ID");
            return;
        } if (exam.equals("")) {
            JOptionPane.showMessageDialog(null,"Please enter the examination name");
            return;
        } if (sgrade.equals("")) {
            JOptionPane.showMessageDialog(null,"Please select a grade awarded for the exam");
            return;
        } else {

            try {
                String query = "insert into grades values ('" + id + "', '" + exam + "', '" + sgrade + "', '" + date + "', '" + pinnumber + "')";

                conn.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Grade updated successfully");

                loadGrades();

            } catch (Exception e) {
                System.out.println(e);
            }
        }

    }

    public static void main(String[] args) {
        new Gradebook("");
    }
}
