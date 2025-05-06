package deptSync.Manager;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentLeaveDetails extends JFrame implements ActionListener {

    JTextField rollField, sessionField;
    JTable table;
    JButton searchRoll, searchSession, print, delete, cancel;

    StudentLeaveDetails(){
        setTitle("Leave Information - DeptSync Manager");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setLayout(null);

        JLabel heading = new JLabel("- Student's Leave Details -");
        heading.setBounds(365, 30, 350, 50);
        heading.setFont(new Font("Arial", Font.BOLD, 20));
        add(heading);

        JLabel heading2 = new JLabel("Search by Roll");
        heading2.setBounds(20, 98, 120, 20);
        heading2.setFont(new Font("Arial", Font.BOLD, 15));
        add(heading2);

        rollField = new JTextField();
        rollField.setBounds(150, 98, 150, 20);
        rollField.setFont(new Font("Arial", Font.BOLD, 12));
        add(rollField);

        searchRoll = new JButton("Search");
        searchRoll.setBounds(320, 98, 80, 20);
        searchRoll.setBackground(new Color(52, 40, 186));
        searchRoll.setForeground(Color.WHITE);
        searchRoll.addActionListener(this);
        add(searchRoll);

        JLabel heading3 = new JLabel("Search by Session");
        heading3.setBounds(450, 98, 150, 20);
        heading3.setFont(new Font("Arial", Font.BOLD, 15));
        add(heading3);

        sessionField = new JTextField();
        sessionField.setBounds(600, 98, 150, 20);
        sessionField.setFont(new Font("Arial", Font.BOLD, 12));
        add(sessionField);

        searchSession = new JButton("Search");
        searchSession.setBounds(770, 98, 80, 20);
        searchSession.setBackground(new Color(52, 40, 186));
        searchSession.setForeground(Color.WHITE);
        searchSession.addActionListener(this);
        add(searchSession);

        table = new JTable();
        loadTableData(); // Load table data

        JScrollPane js = new JScrollPane(table);
        js.setBounds(0, 180, 990, 600);
        add(js);

        print = new JButton("Print");
        print.setBounds(350, 140, 80, 20);
        print.setBackground(new Color(52, 40, 186));
        print.setForeground(Color.WHITE);
        print.addActionListener(this);
        add(print);

        delete = new JButton("Delete");
        delete.setBounds(450, 140, 80, 20);
        delete.setBackground(Color.RED);
        delete.setForeground(Color.WHITE);
        delete.addActionListener(this);
        add(delete);

        cancel = new JButton("Cancel");
        cancel.setBounds(550, 140, 80, 20);
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
                    rollField.setText(table.getValueAt(selectedRow, 0).toString()); //Roll is column 0
                    sessionField.setText(table.getValueAt(selectedRow, 3).toString()); //Session is column 3
                }
            }
        });

        setVisible(true);
    }

    //Method to load data into the table
    private void loadTableData() {
        try {
            Conn c = new Conn();
            ResultSet resultSet = c.statement.executeQuery("SELECT * FROM studentLeave");
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchRoll) {
            String roll = rollField.getText().trim();
            if (!roll.isEmpty()) {
                searchByRoll(roll);
            }
        } else if (e.getSource() == searchSession) {
            String session = sessionField.getText().trim();
            if (!session.isEmpty()) {
                searchBySession(session);
            }
        } else if (e.getSource() == print) {
            try {
                table.print();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == delete) {
            deleteRecord();
        } else {
            setVisible(false);
        }
    }

    // Search by Roll
    private void searchByRoll(String roll) {
        try {
            Conn c = new Conn();
            String q = "SELECT * FROM studentLeave WHERE Roll = ?";
            PreparedStatement pstmt = c.connection.prepareStatement(q);
            pstmt.setString(1, roll);
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

    // Search by Session
    private void searchBySession(String session) {
        try {
            Conn c = new Conn();
            String q = "SELECT * FROM studentLeave WHERE Session = ?";
            PreparedStatement pstmt = c.connection.prepareStatement(q);
            pstmt.setString(1, session);
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

    // Method to delete selected record
    private void deleteRecord() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Please select a record to delete!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String rollToDelete = rollField.getText().trim();

        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this record?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                Conn c = new Conn();
                String query = "DELETE FROM studentLeave WHERE Roll = ?";
                PreparedStatement pstmt = c.connection.prepareStatement(query);
                pstmt.setString(1, rollToDelete);

                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Record deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    loadTableData(); //Refresh table after deletion
                } else {
                    JOptionPane.showMessageDialog(null, "Error deleting record!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new StudentLeaveDetails();
    }
}
