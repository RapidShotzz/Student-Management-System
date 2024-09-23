package student.management.system;

import com.toedter.plaf.JCalendarTheme;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManageStudents extends JFrame implements ActionListener {

    String pinnumber;
    JTextField searchTextField;
    JTable studentTable;
    JButton addStudentButton, editStudentButton, deleteStudentButton, viewProfileButton, searchButton, back;

    ManageStudents(String pinnumber) {
        this.pinnumber = pinnumber;
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/calculus.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900,900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0,0,900,900);
        add(image);;

        JLabel title = new JLabel("Manage Students");
        title.setFont(new Font("Raleway", Font.BOLD, 30));
        title.setBounds(300, 50, 300, 50);
        title.setForeground(Color.WHITE);
        image.add(title);

        searchTextField = new JTextField();
        searchTextField.setBounds(230, 150, 300, 30);
        image.add(searchTextField);

        searchButton = new JButton("Search");
        searchButton.setBounds(540, 150, 100, 30);
        searchButton.addActionListener(this);
        image.add(searchButton);

        String[] columns = {"Student ID", "Name", "Grade", "Status"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        studentTable = new JTable(model);
        JScrollPane sp = new JScrollPane(studentTable);
        sp.setBounds(82, 210, 700, 300);
        image.add(sp);

        addStudentButton = new JButton("Add Student");
        addStudentButton.setBounds(150, 550, 120, 30);
        addStudentButton.addActionListener(this);
        image.add(addStudentButton);

        editStudentButton = new JButton("Edit Student");
        editStudentButton.setBounds(280, 550, 120, 30);
        editStudentButton.addActionListener(this);
        image.add(editStudentButton);

        deleteStudentButton = new JButton("Delete Student");
        deleteStudentButton.setBounds(410, 550, 120, 30);
        deleteStudentButton.addActionListener(this);
        image.add(deleteStudentButton);

        viewProfileButton = new JButton("View Profile");
        viewProfileButton.setBounds(540, 550, 120, 30);
        viewProfileButton.addActionListener(this);
        image.add(viewProfileButton);

        back = new JButton("Back");
        back.setBounds(320,700,200,50);
        back.addActionListener(this);
        image.add(back);

        setSize(900,900);
        setLocation(300,0);
        setUndecorated(true);
        setVisible(true);

        refreshStudentTable();
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == searchButton) {
            String searchQuery = searchTextField.getText();
            Conn conn =  new Conn();
            DefaultTableModel model = (DefaultTableModel) studentTable.getModel();
            model.setRowCount(0);
            try {
                ResultSet rs = conn.s.executeQuery("select * from students where pinnumber = '" + pinnumber + "' and name Like '%" + searchQuery + "%'");
                while (rs.next()) {
                    String id = rs.getString("id");
                    String name = rs.getString("name");
                    String grade = rs.getString("grade");
                    String status = rs.getString("status");
                    model.addRow(new Object[]{id, name, grade, status});
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } else if (ae.getSource() == addStudentButton) {
           showAddStudentDialog();
        } else if (ae.getSource() == editStudentButton) {
          int selectedRow = studentTable.getSelectedRow();
          if (selectedRow >= 0) {
              String id = studentTable.getValueAt(selectedRow,0).toString();
              showEditStudentDialog(id);
          } else {
              JOptionPane.showMessageDialog(this,"Please select a student to edit");
          }
        } else if (ae.getSource() == deleteStudentButton) {
            int selectedRow = studentTable.getSelectedRow();
            if (selectedRow >= 0) {
                String id = studentTable.getValueAt(selectedRow,0).toString();
                int confirmation = JOptionPane.showConfirmDialog(this,"Are you sure you want to delete student with ID: " + id + "?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
                if (confirmation == JOptionPane.YES_OPTION) {
                    deleteStudent(id);
                }
            } else {
                JOptionPane.showMessageDialog(this,"Please select a student to delete");
            }
        } else if (ae.getSource() == viewProfileButton) {
           int selectedRow = studentTable.getSelectedRow();
           if (selectedRow >= 0) {
               String id = studentTable.getValueAt(selectedRow,0).toString();
               showStudentProfile(id);
           } else {
               JOptionPane.showMessageDialog(this,"Please select a student to view the profile");
           }
        } else if (ae.getSource() == back) {
            setVisible(false);
            new Homepage(pinnumber).setVisible(true);
        }
    }

    private void showAddStudentDialog() {
        JDialog addStudentDialog = new JDialog(this, "Add Student", true);
        addStudentDialog.setLayout(new GridLayout(5, 2, 10, 10));

        JLabel idLabel = new JLabel("Student ID:");
        JTextField idField = new JTextField();
        addStudentDialog.add(idLabel);
        addStudentDialog.add(idField);

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();
        addStudentDialog.add(nameLabel);
        addStudentDialog.add(nameField);

        JLabel gradeLabel = new JLabel("Grade:");
        JTextField gradeField = new JTextField();
        addStudentDialog.add(gradeLabel);
        addStudentDialog.add(gradeField);

        JLabel statusLabel = new JLabel("Status:");
        JTextField statusField = new JTextField();
        addStudentDialog.add(statusLabel);
        addStudentDialog.add(statusField);

        JButton submitButton = new JButton("Submit");
        JButton cancelButton = new JButton("Cancel");
        addStudentDialog.add(submitButton);
        addStudentDialog.add(cancelButton);

        addStudentDialog.setSize(300, 200);
        addStudentDialog.setLocationRelativeTo(this);

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addStudent(idField.getText(), nameField.getText(), gradeField.getText(), statusField.getText());
                addStudentDialog.dispose();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addStudentDialog.dispose();
            }
        });

        addStudentDialog.setVisible(true);
    }

    private void addStudent(String id, String name, String grade, String status) {
        Conn conn = new Conn();
        try {
            String query = "insert into students values('"+id+"', '"+name+"', '"+grade+"', '"+status+"', '"+pinnumber+"')";
            int rowsInserted = conn.s.executeUpdate(query);

            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(this,"Student added successfully");
                refreshStudentTable();
            } else {
                JOptionPane.showMessageDialog(this,"Failed to add student");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void refreshStudentTable() {
        Conn conn = new Conn();
        DefaultTableModel model = (DefaultTableModel) studentTable.getModel();
        model.setRowCount(0);

        try {
            String query = "select * from students where pinnumber = '" + pinnumber + "'";
            ResultSet rs = conn.s.executeQuery(query);

            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                String grade = rs.getString("grade");
                String status = rs.getString("status");
                model.addRow(new Object[]{id, name, grade, status});
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void showEditStudentDialog(String id) {
        JDialog editStudentDialog = new JDialog(this, "Edit Student", true);
        editStudentDialog.setLayout(new GridLayout(5, 2, 10, 10));

        JLabel idLabel = new JLabel("Student ID:");
        JLabel idValue = new JLabel(id);
        editStudentDialog.add(idLabel);
        editStudentDialog.add(idValue);

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();
        editStudentDialog.add(nameLabel);
        editStudentDialog.add(nameField);

        JLabel gradeLabel = new JLabel("Grade:");
        JTextField gradeField = new JTextField();
        editStudentDialog.add(gradeLabel);
        editStudentDialog.add(gradeField);

        JLabel statusLabel = new JLabel("Status:");
        JTextField statusField = new JTextField();
        editStudentDialog.add(statusLabel);
        editStudentDialog.add(statusField);

        JButton submitButton = new JButton("Submit");
        JButton cancelButton = new JButton("Cancel");
        editStudentDialog.add(submitButton);
        editStudentDialog.add(cancelButton);

        editStudentDialog.setSize(300, 200);
        editStudentDialog.setLocationRelativeTo(this);

        // Load current student details
        loadStudentDetails(id, nameField, gradeField, statusField);

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateStudent(id, nameField.getText(), gradeField.getText(), statusField.getText());
                editStudentDialog.dispose();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editStudentDialog.dispose();
            }
        });

        editStudentDialog.setVisible(true);
    }

    private void loadStudentDetails(String id, JTextField nameField, JTextField gradeField, JTextField statusField) {
        Conn conn = new Conn();
        try {
            String query = "select * from students where id = '" + id + "'";
            ResultSet rs = conn.s.executeQuery(query);
            if (rs.next()) {
                nameField.setText(rs.getString("name"));
                gradeField.setText(rs.getString("grade"));
                statusField.setText(rs.getString("status"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void updateStudent(String id, String name, String grade, String status) {
        Conn conn = new Conn();
        try {
            String query = "update students set name = '" + name + "', grade = '" + grade + "', status = '" + status + "' WHERE id = '" + id + "'";
            int rowsUpdated = conn.s.executeUpdate(query);

            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(this, "Student updated successfully");
                refreshStudentTable();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update student");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void deleteStudent(String id) {
        Conn conn = new Conn();
        try {
            String query = "delete from students where id = '" + id + "'";
            int rowsDeleted = conn.s.executeUpdate(query);
            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(this,"Student deleted successfully");
                refreshStudentTable();
            } else {
                JOptionPane.showMessageDialog(this,"Failed to delete student");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void showStudentProfile(String id) {
        JDialog profileDialog = new JDialog(this, "Student Profile", true);
        profileDialog.setLayout(new GridLayout(5, 2, 10, 10));

        JLabel idLabel = new JLabel("Student ID:");
        JLabel idValue = new JLabel(id);
        profileDialog.add(idLabel);
        profileDialog.add(idValue);

        JLabel nameLabel = new JLabel("Name:");
        JLabel nameValue = new JLabel();
        profileDialog.add(nameLabel);
        profileDialog.add(nameValue);

        JLabel gradeLabel = new JLabel("Grade:");
        JLabel gradeValue = new JLabel();
        profileDialog.add(gradeLabel);
        profileDialog.add(gradeValue);

        JLabel statusLabel = new JLabel("Status:");
        JLabel statusValue = new JLabel();
        profileDialog.add(statusLabel);
        profileDialog.add(statusValue);

        JButton closeButton = new JButton("Close");
        profileDialog.add(new JLabel()); // Empty cell for layout
        profileDialog.add(closeButton);

        profileDialog.setSize(300, 200);
        profileDialog.setLocationRelativeTo(this);

        // Load student details
        loadStudentDetailsForProfile(id, nameValue, gradeValue, statusValue);

        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                profileDialog.dispose();
            }
        });

        profileDialog.setVisible(true);
    }

    private void loadStudentDetailsForProfile(String id, JLabel nameValue, JLabel gradeValue, JLabel statusValue) {
        Conn conn = new Conn();
        try {
            String query = "select * from students where id = '" + id + "'";
            ResultSet rs = conn.s.executeQuery(query);
            if (rs.next()) {
                nameValue.setText(rs.getString("name"));
                gradeValue.setText(rs.getString("grade"));
                statusValue.setText(rs.getString("status"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        new ManageStudents("");
    }
}
