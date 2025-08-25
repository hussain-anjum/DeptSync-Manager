package deptSync.Manager;

import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class UpdateTeacher extends JFrame implements ActionListener {
    JTextField textName, textTeacherID, teacherIdField, teacherNameField, textNid, textPermanentAddress, textPresentAddress, textAccountNo, textPhone, textEmail, textEducation;
    JComboBox<String> comboDes, comboBlood;
    JDateChooser cjoin;
    JButton searchTId, searchName, update, cancel;
    String selectedTID;

    UpdateTeacher(){
        setTitle("Update Information - DeptSync Manager");
        setSize(950, 700);
        setLocationRelativeTo(null);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel heading = new JLabel("- Update Teacher Details -");
        heading.setBounds(320, 30, 500, 50);
        heading.setFont(new Font("Arial", Font.BOLD, 20));
        add(heading);

        //Search by TID
        JLabel heading2 = new JLabel("Search by TID");
        heading2.setBounds(55, 120, 120, 20);
        heading2.setFont(new Font("Arial", Font.BOLD, 15));
        add(heading2);

        teacherIdField = new JTextField();
        teacherIdField.setBounds(180, 120, 150, 20);
        teacherIdField.setFont(new Font("Arial", Font.BOLD, 12));
        add(teacherIdField);

        searchTId = new JButton("Search");
        searchTId.setBounds(340, 119, 80, 20);
        searchTId.setBackground(new Color(52, 40, 186));
        searchTId.setForeground(Color.WHITE);
        searchTId.addActionListener(this);
        add(searchTId);

        //Search by Name
        JLabel heading3 = new JLabel("Search by Name");
        heading3.setBounds(498, 120, 130, 20);
        heading3.setFont(new Font("Arial", Font.BOLD, 15));
        add(heading3);

        teacherNameField = new JTextField();
        teacherNameField.setBounds(630, 120, 150, 20);
        teacherNameField.setFont(new Font("Arial", Font.BOLD, 12));
        add(teacherNameField);

        searchName = new JButton("Search");
        searchName.setBounds(790, 119, 80, 20);
        searchName.setBackground(new Color(52, 40, 186));
        searchName.setForeground(Color.WHITE);
        searchName.addActionListener(this);
        add(searchName);

        //Labels & Fields
        String[] labels = {"Name*", "Designation", "Teacher ID*", "NID", "Joining Date", "Permanent Address*",
                "Present Address", "Account No.", "Phone", "Email", "Education", "Blood Group*"};

        int x1 = 55, x2 = 500, y = 170, width = 160, height = 30;

        for (int i = 0; i < labels.length; i++) {
            JLabel lbl = new JLabel(labels[i]);
            lbl.setBounds(i % 2 == 0 ? x1 : x2, y, width, height);
            lbl.setFont(new Font("Arial", Font.BOLD, 15));
            add(lbl);

            if (labels[i].equals("Designation")) {
                String[] designations = {"Professor", "Associate Professor", "Assistant Professor", "Lecturer"};
                comboDes = new JComboBox<>(designations);
                comboDes.setBounds((i % 2 == 0 ? x1 : x2) + 150, y + 2, 200, 25);
                add(comboDes);

            } else if (labels[i].equals("Teacher ID*")) {
                textTeacherID = new JTextField();
                textTeacherID.setBounds((i % 2 == 0 ? x1 : x2) + 150, y + 2, 200, 25);
                textTeacherID.setFont(new Font("Arial", Font.BOLD, 12));
                textTeacherID.setEditable(false);
                add(textTeacherID);

            } else if (labels[i].equals("Joining Date")) {
                cjoin = new JDateChooser();
                cjoin.setBounds((i % 2 == 0 ? x1 : x2) + 150, y + 2, 200, 25);
                cjoin.setFont(new Font("Arial",Font.BOLD,12));
                add(cjoin);

            } else if (labels[i].equals("Blood Group*")) {
                String[] bloodGroups = {"A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-"};
                comboBlood = new JComboBox<>(bloodGroups);
                comboBlood.setBounds((i % 2 == 0 ? x1 : x2) + 150, y + 2, 200, 25);
                comboBlood.setEnabled(false);
                add(comboBlood);

            } else {
                JTextField textField = new JTextField();
                textField.setBounds((i % 2 == 0 ? x1 : x2) + 150, y + 2, 200, 25);
                textField.setFont(new Font("Arial", Font.BOLD, 12));
                add(textField);

                switch (labels[i]) {
                    case "Name*" -> { textName = textField; textName.setEditable(false); }
                    case "NID" -> textNid = textField;
                    case "Permanent Address*" -> { textPermanentAddress = textField; textPermanentAddress.setEditable(false); }
                    case "Present Address" -> textPresentAddress = textField;
                    case "Account No." -> textAccountNo = textField;
                    case "Phone" -> textPhone = textField;
                    case "Email" -> textEmail = textField;
                    case "Education" -> textEducation = textField;
                }
            }
            if (i % 2 != 0) y += 50;
        }

        update = new JButton("Update");
        update.setBounds(300, 525, 120, 30);
        update.setBackground(new Color(52, 40, 186));
        update.setForeground(Color.WHITE);
        update.addActionListener(this);
        add(update);

        cancel = new JButton("Cancel");
        cancel.setBounds(480, 525, 120, 30);
        cancel.setBackground(new Color(52, 40, 186));
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        add(cancel);

        // Background
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/Details.png"));
        Image i2 = i1.getImage().getScaledInstance(950, 680, Image.SCALE_SMOOTH);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel imageLabel = new JLabel(i3);
        imageLabel.setBounds(0, 0, 950, 700);
        add(imageLabel);

        setVisible(true);
    }

    public UpdateTeacher(String teacherID) {
        this();
        this.selectedTID = teacherID;
        fetchAndFillData("Teacher_ID", teacherID);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchTId) {
            String tID = teacherIdField.getText();
            fetchAndFillData("Teacher_ID", tID);

        } else if (e.getSource() == searchName) {
            String tName = teacherNameField.getText();
            fetchAndFillData("Name", tName);

        } else if (e.getSource() == update) {
            String Designation = (String) comboDes.getSelectedItem();
            String NID = textNid.getText().trim();
            String Joining_Date = ((JTextField) cjoin.getDateEditor().getUiComponent()).getText().trim();
            String Permanent_Address = textPermanentAddress.getText().trim();
            String Present_Address = textPresentAddress.getText().trim();
            String Account_No = textAccountNo.getText().trim();
            String Phone = textPhone.getText().trim();
            String Email = textEmail.getText().trim();
            String Education = textEducation.getText().trim();
            String Blood_Group = (String) comboBlood.getSelectedItem();

            if (Designation.isEmpty() || NID.isEmpty() || Joining_Date.isEmpty() || Permanent_Address.isEmpty() ||
                    Present_Address.isEmpty() || Account_No.isEmpty() || Phone.isEmpty() ||
                    Email.isEmpty() || Education.isEmpty() || Blood_Group.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields must be filled!", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Validations
            if (!NID.matches("\\d+")) {
                JOptionPane.showMessageDialog(this, "Invalid NID! Only digits allowed.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!Phone.matches("\\d{11}")) {
                JOptionPane.showMessageDialog(this, "Invalid Phone! Must be 11 digits.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!Email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
                JOptionPane.showMessageDialog(this, "Invalid Email format!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!Account_No.matches("\\d+")) {
                JOptionPane.showMessageDialog(this, "Invalid Account No! Only digits allowed.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                Conn c = new Conn();
                String q = "UPDATE teacher SET Designation='" + Designation + "', NID='" + NID + "', Joining_Date='" + Joining_Date +
                        "', Permanent_Address='" + Permanent_Address + "', Present_Address='" + Present_Address + "', Account_No='" + Account_No +
                        "', Phone='" + Phone + "', Email='" + Email + "', Education='" + Education +
                        "' WHERE Teacher_ID='" + textTeacherID.getText() + "'";
                c.statement.executeUpdate(q);
                JOptionPane.showMessageDialog(this, "Information updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            setVisible(false);
        }
    }

    private void fetchAndFillData(String field, String value) {
        try {
            Conn c = new Conn();
            String query = "SELECT * FROM teacher WHERE " + field + " = ?";
            PreparedStatement ps = c.connection.prepareStatement(query);
            ps.setString(1, value);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                textName.setText(rs.getString("Name"));
                textTeacherID.setText(rs.getString("Teacher_ID"));
                comboDes.setSelectedItem(rs.getString("Designation"));
                textNid.setText(rs.getString("NID"));
                ((JTextField) cjoin.getDateEditor().getUiComponent()).setText(rs.getString("Joining_Date"));
                textPermanentAddress.setText(rs.getString("Permanent_Address"));
                textPresentAddress.setText(rs.getString("Present_Address"));
                textAccountNo.setText(rs.getString("Account_No"));
                textPhone.setText(rs.getString("Phone"));
                textEmail.setText(rs.getString("Email"));
                textEducation.setText(rs.getString("Education"));
                comboBlood.setSelectedItem(rs.getString("Blood_Group"));
            } else {
                JOptionPane.showMessageDialog(null, "No record found!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new UpdateTeacher();
    }
}