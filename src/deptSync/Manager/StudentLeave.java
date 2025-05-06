package deptSync.Manager;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class StudentLeave extends JFrame implements ActionListener{

    JTextField rollField, textName, textReg, textSession, textTotalLeave, textLeaveReason, textPhone, textEmail;
    JDateChooser LeaveSD, LeaveED;
    JComboBox<String> comboApproval;
    JButton searchRoll, submit, cancel;

    StudentLeave(){
        setTitle("Apply Leave - DeptSync Manager");
        setSize(900, 700);
        setLocationRelativeTo(null);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setLayout(null);

        JLabel heading = new JLabel("- Apply For Student's Leave -");
        heading.setBounds(320, 30, 350, 50);
        heading.setFont(new Font("Arial", Font.BOLD, 18));
        add(heading);

        JLabel heading2 = new JLabel("Search by Roll");
        heading2.setBounds(65, 98, 120, 20);
        heading2.setFont(new Font("Arial", Font.BOLD, 15));
        add(heading2);

        rollField = new JTextField();
        rollField.setBounds(200, 98, 150, 20);
        rollField.setFont(new Font("Arial", Font.BOLD, 12));
        add(rollField);

        searchRoll = new JButton("Search");
        searchRoll.setBounds(360, 98, 80, 20);
        searchRoll.setBackground(new Color(52, 40, 186));
        searchRoll.setForeground(Color.WHITE);
        searchRoll.addActionListener(this);
        add(searchRoll);

        //Row 2
        JLabel lblName = new JLabel("Student Name");
        lblName.setBounds(65, 150, 120, 30);
        lblName.setFont(new Font("Arial", Font.BOLD, 15));
        add(lblName);

        textName = new JTextField();
        textName.setBounds(200, 152, 200, 25);
        textName.setFont(new Font("Arial", Font.BOLD, 12));
        add(textName);

        JLabel lblReg = new JLabel("Registration No.");
        lblReg.setBounds(450, 150, 150, 30);
        lblReg.setFont(new Font("Arial", Font.BOLD, 15));
        add(lblReg);

        textReg = new JTextField();
        textReg.setBounds(600, 152, 200, 25);
        textReg.setFont(new Font("Arial", Font.BOLD, 12));
        add(textReg);

        JLabel lblSession = new JLabel("Session");
        lblSession.setBounds(65, 190, 120, 30);
        lblSession.setFont(new Font("Arial", Font.BOLD, 15));
        add(lblSession);

        textSession = new JTextField();
        textSession.setBounds(200, 192, 200, 25);
        textSession.setFont(new Font("Arial", Font.BOLD, 12));
        add(textSession);

        //Row 3
        JLabel lblStart = new JLabel("Leave Start Date");
        lblStart.setBounds(65, 230, 150, 30);
        lblStart.setFont(new Font("Arial", Font.BOLD, 15));
        add(lblStart);

        LeaveSD = new JDateChooser();
        LeaveSD.setBounds(200, 232, 200, 25);
        LeaveSD.setFont(new Font("Arial", Font.BOLD, 12));
        add(LeaveSD);

        JLabel lblEnd = new JLabel("Leave End Date");
        lblEnd.setBounds(450, 230, 150, 30);
        lblEnd.setFont(new Font("Arial", Font.BOLD, 15));
        add(lblEnd);

        LeaveED = new JDateChooser();
        LeaveED.setBounds(600, 232, 200, 25);
        LeaveED.setFont(new Font("Arial", Font.BOLD, 12));
        add(LeaveED);

        //Row 4
        JLabel lblTotalLeave = new JLabel("Total Number of Leave Days Requested");
        lblTotalLeave.setBounds(65, 280, 300, 30);
        lblTotalLeave.setFont(new Font("Arial", Font.BOLD, 15));
        add(lblTotalLeave);

        textTotalLeave = new JTextField();
        textTotalLeave.setBounds(370, 282, 200, 25);
        textTotalLeave.setFont(new Font("Arial", Font.BOLD, 12));
        add(textTotalLeave);

        //Row 5
        JLabel lblReason = new JLabel("Reason for Leave");
        lblReason.setBounds(65, 330, 150, 30);
        lblReason.setFont(new Font("Arial", Font.BOLD, 15));
        add(lblReason);

        textLeaveReason = new JTextField();
        textLeaveReason.setBounds(200, 332, 600, 50);
        textLeaveReason.setFont(new Font("Arial", Font.BOLD, 12));
        add(textLeaveReason);

        JLabel heading3 = new JLabel("*Contact Information During Leave: ");
        heading3.setBounds(65, 385, 350, 50);
        heading3.setFont(new Font("Arial", Font.BOLD, 12));
        add(heading3);

        //Row 6
        JLabel lblPhone = new JLabel("Phone");
        lblPhone.setBounds(65, 435, 100, 30);
        lblPhone.setFont(new Font("Arial", Font.BOLD, 15));
        add(lblPhone);

        textPhone = new JTextField();
        textPhone.setBounds(200, 437, 200, 25);
        textPhone.setFont(new Font("Arial", Font.BOLD, 12));
        add(textPhone);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setBounds(450, 435, 100, 30);
        lblEmail.setFont(new Font("Arial", Font.BOLD, 15));
        add(lblEmail);

        textEmail = new JTextField();
        textEmail.setBounds(600, 437, 200, 25);
        textEmail.setFont(new Font("Arial", Font.BOLD, 12));
        add(textEmail);

        JLabel lblApproval = new JLabel("Chairman Approval");
        lblApproval.setBounds(65, 485, 150, 30);
        lblApproval.setFont(new Font("Arial", Font.BOLD, 13));
        add(lblApproval);

        String[] options = {"Approved", "Rejected"};
        comboApproval = new JComboBox<>(options);
        comboApproval.setBounds(200, 487, 200, 25);
        comboApproval.setFont(new Font("Arial", Font.BOLD, 12));
        add(comboApproval);

        // Submit and Cancel Buttons
        submit = new JButton("Submit");
        submit.setBounds(300, 555, 120, 30);
        submit.setBackground(new Color(52, 40, 186));
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        add(submit);

        cancel = new JButton("Cancel");
        cancel.setBounds(480, 555, 120, 30);
        cancel.setBackground(new Color(52, 40, 186));
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        add(cancel);

        // Background Image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/Details.png"));
        Image i2 = i1.getImage().getScaledInstance(900, 680, Image.SCALE_SMOOTH);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel imageLabel = new JLabel(i3);
        imageLabel.setBounds(0, 0, 900, 700); // Cover the full window
        add(imageLabel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == searchRoll) {
            String roll = rollField.getText();  // Get Roll entered by user
            try {
                Conn c = new Conn();
                String query = "SELECT Name, Registration_No, Session FROM student WHERE Roll = '"+roll+"'";
                ResultSet rs = c.statement.executeQuery(query);

                if(rs.next()) {
                    textName.setText(rs.getString("Name"));
                    textReg.setText(rs.getString("Registration_No"));
                    textSession.setText(rs.getString("Session"));
                } else {
                    JOptionPane.showMessageDialog(null, "No record found!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        else if(e.getSource() == submit) {
            String Roll = rollField.getText();
            String Name = textName.getText();
            String Registration_No = textReg.getText();
            String Session = textSession.getText();
            String LeaveStart = ((JTextField) LeaveSD.getDateEditor().getUiComponent()).getText();
            String LeaveEnd = ((JTextField) LeaveED.getDateEditor().getUiComponent()).getText();
            String TotalLeave = textTotalLeave.getText();
            String Reason = textLeaveReason.getText();
            String Phone = textPhone.getText();
            String Email = textEmail.getText();
            String Approval = (String) comboApproval.getSelectedItem();

            try {
                Conn c = new Conn();
                String q = "INSERT INTO studentLeave (Roll, Name, Registration_No, Session, Leave_Start, Leave_End, Total_Leave_Days, Reason, Phone, Email, Chairman_Approval) VALUES ('"+Roll+"', '"+Name+"', '"+Registration_No+"', '"+Session+"', '"+LeaveStart+"', '"+LeaveEnd+"', '"+TotalLeave+"', '"+Reason+"', '"+Phone+"', '"+Email+"', '"+Approval+"')";

                c.statement.executeUpdate(q);
                JOptionPane.showMessageDialog(null, "Information submitted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                setVisible(false);
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }

        else {
            setVisible(false);
        }
    }
    public static void main(String[] args) {
        new StudentLeave();
    }
}
