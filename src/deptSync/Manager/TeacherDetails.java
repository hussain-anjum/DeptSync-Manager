package deptSync.Manager;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class TeacherDetails extends JFrame implements ActionListener {

    JTextField teacherIdField, teacherNameField;
    JTable table;
    JButton searchTId, searchName, print, update, add, delete, cancel, refreshBtn;

    TeacherDetails() {
        setTitle("View Information - DeptSync Manager");
        setSize(1010, 700);
        setLocationRelativeTo(null);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel heading = new JLabel("- Teacher Details -");
        heading.setBounds(410, 30, 350, 50);
        heading.setFont(new Font("Arial", Font.BOLD, 20));
        add(heading);

        //Search by TID
        JLabel heading2 = new JLabel("Search by TID");
        heading2.setBounds(20, 98, 120, 20);
        heading2.setFont(new Font("Arial", Font.BOLD, 15));
        add(heading2);

        teacherIdField = new JTextField();
        teacherIdField.setBounds(150, 98, 150, 20);
        teacherIdField.setFont(new Font("Arial", Font.BOLD, 12));
        add(teacherIdField);

        searchTId = new JButton("Search");
        searchTId.setBounds(320, 98, 80, 20);
        searchTId.setBackground(new Color(52, 40, 186));
        searchTId.setForeground(Color.WHITE);
        searchTId.addActionListener(this);
        add(searchTId);

        //Search by Name
        JLabel heading3 = new JLabel("Search by Name");
        heading3.setBounds(460, 98, 150, 20);
        heading3.setFont(new Font("Arial", Font.BOLD, 15));
        add(heading3);

        teacherNameField = new JTextField();
        teacherNameField.setBounds(600, 98, 150, 20);
        teacherNameField.setFont(new Font("Arial", Font.BOLD, 12));
        add(teacherNameField);

        searchName = new JButton("Search");
        searchName.setBounds(770, 98, 80, 20);
        searchName.setBackground(new Color(52, 40, 186));
        searchName.setForeground(Color.WHITE);
        searchName.addActionListener(this);
        add(searchName);

        table = new JTable();
        loadTableData(); //Load table data

        JScrollPane js = new JScrollPane(table);
        js.setBounds(0, 180, 1000, 600);
        add(js);

        print = new JButton("Print");
        print.setBounds(200, 140, 80, 20);
        print.setBackground(new Color(52, 40, 186));
        print.setForeground(Color.WHITE);
        print.addActionListener(this);
        add(print);

        update = new JButton("Update");
        update.setBounds(300, 140, 80, 20);
        update.setBackground(new Color(52, 40, 186));
        update.setForeground(Color.WHITE);
        update.addActionListener(this);
        add(update);

        add = new JButton("Add");
        add.setBounds(400, 140, 80, 20);
        add.setBackground(new Color(52, 40, 186));
        add.setForeground(Color.WHITE);
        add.addActionListener(this);
        add(add);

        delete = new JButton("Delete");
        delete.setBounds(500, 140, 80, 20);
        delete.setBackground(Color.RED);
        delete.setForeground(Color.WHITE);
        delete.addActionListener(this);
        add(delete);

        refreshBtn = new JButton("Refresh");
        refreshBtn.setBounds(600, 140, 80, 20);
        refreshBtn.setBackground(new Color(52, 40, 186));
        refreshBtn.setForeground(Color.WHITE);
        refreshBtn.addActionListener(this);
        add(refreshBtn);

        cancel = new JButton("Cancel");
        cancel.setBounds(700, 140, 80, 20);
        cancel.setBackground(new Color(52, 40, 186));
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        add(cancel);

        //Background Image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/Details.png"));
        Image i2 = i1.getImage().getScaledInstance(1000, 680, Image.SCALE_SMOOTH);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel imageLabel = new JLabel(i3);
        imageLabel.setBounds(0, 0, 900, 700);
        add(imageLabel);

        //MouseListener to auto-fill fields
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    teacherIdField.setText(table.getValueAt(selectedRow, 2).toString());
                    teacherNameField.setText(table.getValueAt(selectedRow, 0).toString());
                }
            }
        });

        setVisible(true);
    }
    //Load all data into table
    private void loadTableData() {
        try {
            Conn c = new Conn();
            ResultSet resultSet = c.statement.executeQuery("SELECT * FROM teacher");
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchTId) {
            searchByTID();
        } else if (e.getSource() == searchName) {
            searchByName();
        } else if (e.getSource() == print) {
            try {
                table.print();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == update) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "Please select a teacher record to update!", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            //Get both ID & Name
            String selectedTID = table.getValueAt(selectedRow, 2).toString();
            String selectedName = table.getValueAt(selectedRow, 0).toString();

            UpdateTeacher updateTeacher = new UpdateTeacher();
            updateTeacher.teacherIdField.setText(selectedTID);
            updateTeacher.teacherNameField.setText(selectedName);

            //auto trigger both search options
            updateTeacher.searchTId.doClick();
            updateTeacher.searchName.doClick();

            setVisible(false);
        } else if (e.getSource() == add) {
            setVisible(false);
            new AddTeacher();
        } else if (e.getSource() == delete) {
            deleteRecord();
        } else if (e.getSource() == refreshBtn) {
            loadTableData();
        } else {
            setVisible(false);
        }
    }

    //Search by TID
    private void searchByTID() {
        String tID = teacherIdField.getText().trim();
        if (!tID.isEmpty()) {
            try {
                Conn c = new Conn();
                String q = "SELECT * FROM teacher WHERE Teacher_ID = ?";
                PreparedStatement pstmt = c.connection.prepareStatement(q);
                pstmt.setString(1, tID);
                ResultSet resultSet = pstmt.executeQuery();

                if (!resultSet.isBeforeFirst()) {
                    JOptionPane.showMessageDialog(null, "No record found!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    table.setModel(DbUtils.resultSetToTableModel(resultSet));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    //Search by Name
    private void searchByName() {
        String tName = teacherNameField.getText().trim();
        if (!tName.isEmpty()) {
            try {
                Conn c = new Conn();
                String q = "SELECT * FROM teacher WHERE Name LIKE ?";
                PreparedStatement pstmt = c.connection.prepareStatement(q);
                pstmt.setString(1, "%" + tName + "%");
                ResultSet resultSet = pstmt.executeQuery();

                if (!resultSet.isBeforeFirst()) {
                    JOptionPane.showMessageDialog(null, "No record found!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    table.setModel(DbUtils.resultSetToTableModel(resultSet));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    // Delete record
    private void deleteRecord() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Please select a record to delete!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String tIDToDelete = teacherIdField.getText().trim();

        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this record?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                Conn c = new Conn();
                String query = "DELETE FROM teacher WHERE Teacher_ID = ?";
                PreparedStatement pstmt = c.connection.prepareStatement(query);
                pstmt.setString(1, tIDToDelete);
                int rowsAffected = pstmt.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Record deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    loadTableData(); // Refresh table after deletion
                } else {
                    JOptionPane.showMessageDialog(null, "Error deleting record!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new TeacherDetails();
    }
}
