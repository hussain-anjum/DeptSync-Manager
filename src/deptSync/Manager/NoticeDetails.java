package deptSync.Manager;

import com.toedter.calendar.JDateChooser;
import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;

public class NoticeDetails extends JFrame implements ActionListener {

    JDateChooser noticeDate;
    JTable table;
    JButton searchBtn, createBtn, deleteBtn, refreshBtn, cancelBtn;

    NoticeDetails(){
        setTitle("Notice Information - DeptSync Manager");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setLayout(null);

        JLabel heading = new JLabel("- Notice Details -");
        heading.setBounds(400, 30, 350, 50);
        heading.setFont(new Font("Arial", Font.BOLD, 20));
        add(heading);

        JLabel heading2 = new JLabel("Search by Date");
        heading2.setBounds(20, 98, 120, 20);
        heading2.setFont(new Font("Arial", Font.BOLD, 15));
        add(heading2);

        noticeDate = new JDateChooser();
        noticeDate.setBounds(150, 98, 150, 20);
        noticeDate.setFont(new Font("Arial", Font.BOLD, 12));
        add(noticeDate);

        searchBtn = new JButton("Search");
        searchBtn.setBounds(320, 98, 80, 20);
        searchBtn.setBackground(new Color(52, 40, 186));
        searchBtn.setForeground(Color.WHITE);
        searchBtn.addActionListener(this);
        add(searchBtn);

        table = new JTable();
        loadTableData(); // Load table data

        JScrollPane js = new JScrollPane(table);
        js.setBounds(0, 180, 1000, 600);
        add(js);

        createBtn = new JButton("Create");
        createBtn.setBounds(20, 140, 80, 20);
        createBtn.setBackground(new Color(52, 40, 186));
        createBtn.setForeground(Color.WHITE);
        createBtn.addActionListener(this);
        add(createBtn);

        deleteBtn = new JButton("Delete");
        deleteBtn.setBounds(120, 140, 80, 20);
        deleteBtn.setBackground(Color.RED);
        deleteBtn.setForeground(Color.WHITE);
        deleteBtn.addActionListener(this);
        add(deleteBtn);

        refreshBtn = new JButton("Refresh");
        refreshBtn.setBounds(220, 140, 80, 20);
        refreshBtn.setBackground(new Color(52, 40, 186));
        refreshBtn.setForeground(Color.WHITE);
        refreshBtn.addActionListener(this);
        add(refreshBtn);

        cancelBtn = new JButton("Cancel");
        cancelBtn.setBounds(320, 140, 80, 20);
        cancelBtn.setBackground(new Color(52, 40, 186));
        cancelBtn.setForeground(Color.WHITE);
        cancelBtn.addActionListener(this);
        add(cancelBtn);

        //Background Image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/Details.png"));
        Image i2 = i1.getImage().getScaledInstance(1000, 680, Image.SCALE_SMOOTH);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel imageLabel = new JLabel(i3);
        imageLabel.setBounds(0, 0, 900, 700);
        add(imageLabel);

        //Mouse Listener for date selection from table
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    try {
                        String selectedDateStr = table.getValueAt(selectedRow, 0).toString(); //assuming Date is in column 0
                        java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(selectedDateStr);
                        noticeDate.setDate(date);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        setVisible(true);
    }

    //Method to load data into the table
    private void loadTableData() {
        try {
            Conn c = new Conn();
            ResultSet resultSet = c.statement.executeQuery("SELECT * FROM notice");
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Search by date
    private void searchByDate() {
        java.util.Date selectedDate = noticeDate.getDate();
        if (selectedDate != null) {
            String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(selectedDate);
            try {
                Conn c = new Conn();
                String q = "SELECT * FROM notice WHERE Date = ?";
                PreparedStatement pstmt = c.connection.prepareStatement(q);
                pstmt.setString(1, dateStr);
                ResultSet rs = pstmt.executeQuery();

                if (!rs.isBeforeFirst()) {
                    JOptionPane.showMessageDialog(null, "No notice found!", "Not Found", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    table.setModel(DbUtils.resultSetToTableModel(rs));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please select a date!", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    //Delete selected notice
    private void deleteNotice() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Please select a notice to delete!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String dateToDelete = table.getValueAt(selectedRow, 0).toString(); //Assuming date column is 0
        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure to delete this notice?", "Confirm", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                Conn c = new Conn();
                String query = "DELETE FROM notice WHERE Date = ?";
                PreparedStatement pstmt = c.connection.prepareStatement(query);
                pstmt.setString(1, dateToDelete);
                int affectedRows = pstmt.executeUpdate();

                if (affectedRows > 0) {
                    JOptionPane.showMessageDialog(null, "Notice deleted successfully!", "Deleted", JOptionPane.INFORMATION_MESSAGE);
                    loadTableData();
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to delete!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void actionPerformed(ActionEvent e){
        if (e.getSource() == searchBtn) {
            searchByDate();
        } else if (e.getSource() == createBtn) {
            setVisible(false);
            new Notice();
        } else if (e.getSource() == deleteBtn) {
            deleteNotice();
        } else if (e.getSource() == refreshBtn) {
            loadTableData();
        } else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new NoticeDetails();
    }
}
