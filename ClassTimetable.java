package student.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClassTimetable extends JFrame implements ActionListener {

    String pinnumber;
    JButton back;

    ClassTimetable(String pinnumber) {
        this.pinnumber = pinnumber;
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/calculus.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900,900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0,0,900,900);
        add(image);

        JLabel title = new JLabel("Class Timetable");
        title.setFont(new Font("Raleway", Font.BOLD, 30));
        title.setBounds(315, 50, 300, 50);
        title.setForeground(Color.WHITE);
        image.add(title);

        String[] columns = {"Time","Monday","Tuesday","Wednesday","Thursday","Friday"};
        String[][] data = {
                {"08:00 - 09:00", "Marking", "Teaching", "Teaching", "Teaching", "Responsibilities"},
                {"09:00 - 10:00", "Teaching", "Teaching", "Teaching", "Marking", "Responsibilities"},
                {"10:00 - 11:00", "Break", "Break", "Break", "Break", "Break"},
                {"11:00 - 12:00", "Responsibilities", "Marking", "Teaching", "Teaching", "Teaching"},
                {"12:00 - 01:00", "Teaching", "Responsibilities", "Marking", "Teaching", "Teaching"},
                {"01:00 - 02:00", "Lunch", "Lunch", "Lunch", "Lunch", "Lunch"},
                {"02:00 - 03:00", "Marking", "Marking", "Responsibilities", "Teaching", "Teaching"},
                {"03:00 - 04:00", "Teaching", "Teaching", "Teaching", "Teaching", "Meeting"}
        };

        JTable timetable = new JTable(data,columns);
        timetable.setFont(new Font("Raleway",Font.BOLD,15));
        timetable.setRowHeight(30);
        timetable.getTableHeader().setFont(new Font("Raleway",Font.BOLD,15));

        JScrollPane pane = new JScrollPane(timetable);
        pane.setBounds(0,220,900,330);
        image.add(pane);

        back = new JButton("Back");
        back.setBounds(340,620,200,50);
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
        }
    }

    public static void main(String[] args) {
        new ClassTimetable("");
    }

}
