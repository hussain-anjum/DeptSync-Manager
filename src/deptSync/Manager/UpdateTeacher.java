package deptSync.Manager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class UpdateTeacher extends JFrame implements ActionListener {
    JTextField textName, textTeacherID, teacherIdField, textNid, textDob, textAddress, textPhone, textEmail, textEducation, textExperience;
    JComboBox<String> comboDes;
    JButton searchTId, update, cancel;
    String selectedTID;

    UpdateTeacher(){
        setTitle("Update Information - DeptSync Manager");
        setSize(900, 700);
        setLocationRelativeTo(null);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setLayout(null);

        JLabel heading = new JLabel("- Update Teacher Details -");
        heading.setBounds(320, 30, 500, 50);
        heading.setFont(new Font("Arial", Font.BOLD, 20));
        add(heading);

        JLabel heading2 = new JLabel("Search by TID");
        heading2.setBounds(65, 120, 120, 20);
        heading2.setFont(new Font("Arial", Font.BOLD, 15));
        add(heading2);

        teacherIdField = new JTextField();
        teacherIdField.setBounds(195, 120, 150, 20);
        teacherIdField.setFont(new Font("Arial", Font.BOLD, 12));
        add(teacherIdField);

        searchTId = new JButton("Search");
        searchTId.setBounds(360, 119, 80, 20);
        searchTId.setBackground(new Color(52, 40, 186));
        searchTId.setForeground(Color.WHITE);
        searchTId.addActionListener(this);
        add(searchTId);


        JLabel lblName = new JLabel("Name*");
        lblName.setBounds(65, 170, 120, 30);
        lblName.setFont(new Font("Arial", Font.BOLD, 15));
        add(lblName);

        textName = new JTextField();
        textName.setBounds(195, 172, 200, 25);
        textName.setFont(new Font("Arial", Font.BOLD, 12));
        textName.setEditable(false);
        add(textName);

        JLabel lblDes = new JLabel("Designation");
        lblDes.setBounds(500, 170, 120, 30);
        lblDes.setFont(new Font("Arial", Font.BOLD, 15));
        add(lblDes);

        String[] designations = {"Professor", "Associate Professor", "Assistant Professor", "Lecturer"};
        comboDes = new JComboBox<>(designations);
        comboDes.setBounds(630, 172, 200, 25);
        add(comboDes);

        JLabel lblTID = new JLabel("Teacher ID*");
        lblTID.setBounds(65, 220, 120, 30);
        lblTID.setFont(new Font("Arial", Font.BOLD, 15));
        add(lblTID);

        textTeacherID = new JTextField();
        textTeacherID.setBounds(195, 222, 200, 25);
        textTeacherID.setFont(new Font("Arial", Font.BOLD, 12));
        textTeacherID.setEditable(false);
        add(textTeacherID);

        JLabel lblNid = new JLabel("NID");
        lblNid.setBounds(500, 220, 120, 30);
        lblNid.setFont(new Font("Arial", Font.BOLD, 15));
        add(lblNid);

        textNid = new JTextField();
        textNid.setBounds(630, 222, 200, 25);
        textNid.setFont(new Font("Arial", Font.BOLD, 12));
        add(textNid);

        JLabel lblDOB = new JLabel("Date of Birth*");
        lblDOB.setBounds(65, 270, 120, 30);
        lblDOB.setFont(new Font("Arial", Font.BOLD, 15));
        add(lblDOB);

        textDob = new JTextField();
        textDob.setBounds(195, 272, 200, 25);
        textDob.setFont(new Font("Arial", Font.BOLD, 12));
        textDob.setEditable(false);
        add(textDob);

        JLabel lblAddress = new JLabel("Address");
        lblAddress.setBounds(500, 270, 120, 30);
        lblAddress.setFont(new Font("Arial", Font.BOLD, 15));
        add(lblAddress);

        textAddress = new JTextField();
        textAddress.setBounds(630, 272, 200, 25);
        textAddress.setFont(new Font("Arial", Font.BOLD, 12));
        add(textAddress);

        JLabel lblPhone = new JLabel("Phone");
        lblPhone.setBounds(65, 320, 120, 30);
        lblPhone.setFont(new Font("Arial", Font.BOLD, 15));
        add(lblPhone);

        textPhone = new JTextField();
        textPhone.setBounds(195, 322, 200, 25);
        textPhone.setFont(new Font("Arial", Font.BOLD, 12));
        add(textPhone);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setBounds(500, 320, 120, 30);
        lblEmail.setFont(new Font("Arial", Font.BOLD, 15));
        add(lblEmail);

        textEmail = new JTextField();
        textEmail.setBounds(630, 322, 200, 25);
        textEmail.setFont(new Font("Arial", Font.BOLD, 12));
        add(textEmail);

        JLabel lblEducation = new JLabel("Education");
        lblEducation.setBounds(65, 370, 120, 30);
        lblEducation.setFont(new Font("Arial", Font.BOLD, 15));
        add(lblEducation);

        textEducation = new JTextField();
        textEducation.setBounds(195, 372, 200, 25);
        textEducation.setFont(new Font("Arial", Font.BOLD, 12));
        add(textEducation);

        JLabel lblExperience = new JLabel("Experience");
        lblExperience.setBounds(500, 370, 120, 30);
        lblExperience.setFont(new Font("Arial", Font.BOLD, 15));
        add(lblExperience);

        textExperience = new JTextField();
        textExperience.setBounds(630, 372, 200, 25);
        textExperience.setFont(new Font("Arial", Font.BOLD, 12));
        add(textExperience);

        update = new JButton("Update");
        update.setBounds(300, 470, 120, 30);
        update.setBackground(new Color(52, 40, 186));
        update.setForeground(Color.WHITE);
        update.addActionListener(this);
        add(update);

        cancel = new JButton("Cancel");
        cancel.setBounds(480, 470, 120, 30);
        cancel.setBackground(new Color(52, 40, 186));
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        add(cancel);

        //Background Image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/Details.png"));
        Image i2 = i1.getImage().getScaledInstance(900, 680, Image.SCALE_SMOOTH);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel imageLabel = new JLabel(i3);
        imageLabel.setBounds(0, 0, 900, 700);
        add(imageLabel);

        setVisible(true);
    }
    public UpdateTeacher(String teacherID) {
        this(); //Call the default constructor
        this.selectedTID = teacherID;
        fetchAndFillData(teacherID);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchTId) {
            String tID = teacherIdField.getText();
            try {
                Conn c = new Conn();
                String query = "SELECT * FROM teacher WHERE Teacher_ID = '" + tID + "'";
                ResultSet rs = c.statement.executeQuery(query);
                if (rs.next()) {
                    textName.setText(rs.getString("Name"));
                    textTeacherID.setText(rs.getString("Teacher_ID"));
                    comboDes.setSelectedItem(rs.getString("Designation"));
                    textNid.setText(rs.getString("NID"));
                    textDob.setText(rs.getString("Date_of_Birth"));
                    textAddress.setText(rs.getString("Address"));
                    textPhone.setText(rs.getString("Phone"));
                    textEmail.setText(rs.getString("Email"));
                    textEducation.setText(rs.getString("Education"));
                    textExperience.setText(rs.getString("Experience"));
                } else {
                    JOptionPane.showMessageDialog(null, "No record found!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == update) {
            String Designation = (String) comboDes.getSelectedItem();
            String NID = textNid.getText().trim();
            String Address = textAddress.getText().trim();
            String Phone = textPhone.getText().trim();
            String Email = textEmail.getText().trim();
            String Education = textEducation.getText().trim();
            String Experience = textExperience.getText().trim();

            //Field validation
            if (Designation.isEmpty() || NID.isEmpty() || Address.isEmpty() || Phone.isEmpty()
                    || Email.isEmpty() || Education.isEmpty() || Experience.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields must be filled!", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

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
            //Update if all valid
            try {
                Conn c = new Conn();
                String q = "UPDATE teacher SET Designation='" + Designation + "', NID='" + NID + "', Address='" + Address + "', Phone='" + Phone + "', Email='" + Email + "', Education='" + Education + "', Experience='" + Experience + "' WHERE Teacher_ID='" + textTeacherID.getText() + "'";
                c.statement.executeUpdate(q);
                JOptionPane.showMessageDialog(this, "Information updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                //setVisible(false);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            setVisible(false);
        }
    }
    private void fetchAndFillData(String tID) {
        try {
            Conn c = new Conn();
            String query = "SELECT * FROM teacher WHERE Teacher_ID = ?";
            PreparedStatement ps = c.connection.prepareStatement(query);
            ps.setString(1, tID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                textName.setText(rs.getString("Name"));
                textTeacherID.setText(rs.getString("Teacher_ID"));
                comboDes.setSelectedItem(rs.getString("Designation"));
                textNid.setText(rs.getString("NID"));
                textDob.setText(rs.getString("Date_of_Birth"));
                textAddress.setText(rs.getString("Address"));
                textPhone.setText(rs.getString("Phone"));
                textEmail.setText(rs.getString("Email"));
                textEducation.setText(rs.getString("Education"));
                textExperience.setText(rs.getString("Experience"));
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
