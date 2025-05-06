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

public class TeacherLeaveDetails extends JFrame implements ActionListener {

    JTextField teacherIdField;
    JTable table;
    JButton searchTId, print, delete, cancel;

    TeacherLeaveDetails(){
        setTitle("Leave Information - DeptSync Manager");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setLayout(null);

        JLabel heading = new JLabel("- Teacher's Leave Details -");
        heading.setBounds(365, 30, 350, 50);
        heading.setFont(new Font("Arial", Font.BOLD, 20));
        add(heading);

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

        table = new JTable();
        loadTableData(); // Load table data

        JScrollPane js = new JScrollPane(table);
        js.setBounds(0, 180, 990, 600);
        add(js);

        print = new JButton("Print");
        print.setBounds(20, 140, 80, 20);
        print.setBackground(new Color(52, 40, 186));
        print.setForeground(Color.WHITE);
        print.addActionListener(this);
        add(print);

        delete = new JButton("Delete");
        delete.setBounds(120, 140, 80, 20);
        delete.setBackground(Color.RED);
        delete.setForeground(Color.WHITE);
        delete.addActionListener(this);
        add(delete);

        cancel = new JButton("Cancel");
        cancel.setBounds(220, 140, 80, 20);
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
                    teacherIdField.setText(table.getValueAt(selectedRow, 0).toString());
                }
            }
        });

        setVisible(true);
    }

    //Method to load data into the table
    private void loadTableData() {
        try {
            Conn c = new Conn();
            ResultSet resultSet = c.statement.executeQuery("SELECT * FROM teacherLeave");
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchTId) {
            searchByTID();
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

    //Search by TID
    private void searchByTID() {
        String tID = teacherIdField.getText().trim();
        if (!tID.isEmpty()) {
            try {
                Conn c = new Conn();
                String q = "SELECT * FROM teacherLeave WHERE TID = ?";
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

    // Method to delete selected record
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
                String query = "DELETE FROM teacherLeave WHERE TID = ?";
                PreparedStatement pstmt = c.connection.prepareStatement(query);
                pstmt.setString(1, tIDToDelete);
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
        new TeacherLeaveDetails();
    }
}
