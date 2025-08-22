package deptSync.Manager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class UpdateStudent extends JFrame implements ActionListener {
    JTextField textName, textFatherName, textMotherName, textDob, textAdmissionRoll, textSession, textReg, textRoll,
            rollField, textPermanentAddress, textPresentAddress, textBloodGroup, textPhone, textEmail;
    JComboBox<String> comboDeg;
    JButton searchRoll, update, cancel;

    UpdateStudent(){
        setTitle("Update Information - DeptSync Manager");
        setSize(900, 700);
        setLocationRelativeTo(null);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setLayout(null);

        JLabel heading = new JLabel("- Update Student Details -");
        heading.setBounds(320, 30, 500, 50);
        heading.setFont(new Font("Arial", Font.BOLD, 20));
        add(heading);

        JLabel heading2 = new JLabel("Search by Roll");
        heading2.setBounds(65, 120, 120, 20);
        heading2.setFont(new Font("Arial", Font.BOLD, 15));
        add(heading2);

        rollField = new JTextField();
        rollField.setBounds(200, 120, 150, 20);
        rollField.setFont(new Font("Arial", Font.BOLD, 12));
        add(rollField);

        searchRoll = new JButton("Search");
        searchRoll.setBounds(360, 119, 80, 20);
        searchRoll.setBackground(new Color(52, 40, 186));
        searchRoll.setForeground(Color.WHITE);
        searchRoll.addActionListener(this);
        add(searchRoll);

        JLabel lblName = new JLabel("Student Name*");
        lblName.setBounds(65, 170, 120, 30);
        lblName.setFont(new Font("Arial", Font.BOLD, 15));
        add(lblName);

        textName = new JTextField();
        textName.setBounds(200, 172, 200, 25);
        textName.setFont(new Font("Arial", Font.BOLD, 12));
        textName.setEditable(false);
        add(textName);

        JLabel lblDeg = new JLabel("Degree");
        lblDeg.setBounds(480, 170, 120, 30);
        lblDeg.setFont(new Font("Arial", Font.BOLD, 15));
        add(lblDeg);

        String[] deg = {"BSc.(Engg.)", "MSc."};
        comboDeg = new JComboBox<>(deg);
        comboDeg.setBounds(630, 172, 200, 25);
        add(comboDeg);

        JLabel lblFname = new JLabel("Father’s Name*");
        lblFname.setBounds(65, 220, 120, 30);
        lblFname.setFont(new Font("Arial", Font.BOLD, 15));
        add(lblFname);

        textFatherName = new JTextField();
        textFatherName.setBounds(200, 222, 200, 25);
        textFatherName.setFont(new Font("Arial", Font.BOLD, 12));
        textFatherName.setEditable(false);
        add(textFatherName);

        JLabel lblMname = new JLabel("Mother’s Name*");
        lblMname.setBounds(480, 220, 120, 30);
        lblMname.setFont(new Font("Arial", Font.BOLD, 15));
        add(lblMname);

        textMotherName = new JTextField();
        textMotherName.setBounds(630, 222, 200, 25);
        textMotherName.setFont(new Font("Arial", Font.BOLD, 12));
        textMotherName.setEditable(false);
        add(textMotherName);

        JLabel lblDOB = new JLabel("Date of Birth*");
        lblDOB.setBounds(65, 270, 120, 30);
        lblDOB.setFont(new Font("Arial", Font.BOLD, 15));
        add(lblDOB);

        textDob = new JTextField();
        textDob.setBounds(200, 272, 200, 25);
        textDob.setFont(new Font("Arial", Font.BOLD, 12));
        textDob.setEditable(false);
        add(textDob);

        JLabel lblAddRoll= new JLabel("Admission Roll*");
        lblAddRoll.setBounds(480, 270, 120, 30);
        lblAddRoll.setFont(new Font("Arial", Font.BOLD, 15));
        add(lblAddRoll);

        textAdmissionRoll = new JTextField();
        textAdmissionRoll.setBounds(630, 272, 200, 25);
        textAdmissionRoll.setFont(new Font("Arial", Font.BOLD, 12));
        textAdmissionRoll.setEditable(false);
        add(textAdmissionRoll);

        JLabel lblSession = new JLabel("Session*");
        lblSession.setBounds(65, 320, 120, 30);
        lblSession.setFont(new Font("Arial", Font.BOLD, 15));
        add(lblSession);

        textSession = new JTextField();
        textSession.setBounds(200, 322, 200, 25);
        textSession.setFont(new Font("Arial", Font.BOLD, 12));
        textSession.setEditable(false);
        add(textSession);

        JLabel lblReg = new JLabel("Registration No.*");
        lblReg.setBounds(480, 320, 150, 30);
        lblReg.setFont(new Font("Arial", Font.BOLD, 15));
        add(lblReg);

        textReg = new JTextField();
        textReg.setBounds(630, 322, 200, 25);
        textReg.setFont(new Font("Arial", Font.BOLD, 12));
        textReg.setEditable(false);
        add(textReg);

        JLabel lblRoll = new JLabel("Roll");
        lblRoll.setBounds(65, 370, 120, 30);
        lblRoll.setFont(new Font("Arial", Font.BOLD, 15));
        add(lblRoll);

        textRoll = new JTextField();
        textRoll.setBounds(200, 372, 200, 25);
        textRoll.setFont(new Font("Arial", Font.BOLD, 12));
        add(textRoll);

        JLabel lblPermanent = new JLabel("Permanent Address*");
        lblPermanent.setBounds(480, 370, 170, 30);
        lblPermanent.setFont(new Font("Arial", Font.BOLD, 15));
        add(lblPermanent);

        textPermanentAddress = new JTextField();
        textPermanentAddress.setBounds(630, 372, 200, 25);
        textPermanentAddress.setFont(new Font("Arial", Font.BOLD, 12));
        textPermanentAddress.setEditable(false);
        add(textPermanentAddress);

        JLabel lblPresent = new JLabel("Present Address");
        lblPresent.setBounds(65, 420, 170, 30);
        lblPresent.setFont(new Font("Arial", Font.BOLD, 15));
        add(lblPresent);

        textPresentAddress = new JTextField();
        textPresentAddress.setBounds(200, 422, 200, 25);
        textPresentAddress.setFont(new Font("Arial", Font.BOLD, 12));
        add(textPresentAddress);

        JLabel lblBlood = new JLabel("Blood Group*");
        lblBlood.setBounds(480, 420, 150, 30);
        lblBlood.setFont(new Font("Arial", Font.BOLD, 15));
        add(lblBlood);

        textBloodGroup = new JTextField();
        textBloodGroup.setBounds(630, 422, 200, 25);
        textBloodGroup.setFont(new Font("Arial", Font.BOLD, 12));
        textBloodGroup.setEditable(false);
        add(textBloodGroup);

        JLabel lblPhone = new JLabel("Phone");
        lblPhone.setBounds(65, 470, 120, 30);
        lblPhone.setFont(new Font("Arial", Font.BOLD, 15));
        add(lblPhone);

        textPhone = new JTextField();
        textPhone.setBounds(200, 472, 200, 25);
        textPhone.setFont(new Font("Arial", Font.BOLD, 12));
        add(textPhone);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setBounds(480, 470, 120, 30);
        lblEmail.setFont(new Font("Arial", Font.BOLD, 15));
        add(lblEmail);

        textEmail = new JTextField();
        textEmail.setBounds(630, 472, 200, 25);
        textEmail.setFont(new Font("Arial", Font.BOLD, 12));
        add(textEmail);

        //Update and Cancel Buttons
        update = new JButton("Update");
        update.setBounds(300, 550, 120, 30);
        update.setBackground(new Color(52, 40, 186));
        update.setForeground(Color.WHITE);
        update.addActionListener(this);
        add(update);

        cancel = new JButton("Cancel");
        cancel.setBounds(480, 550, 120, 30);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchRoll) {
            String roll = rollField.getText();
            try {
                Conn c = new Conn();
                String query = "SELECT * FROM student WHERE Roll = '" + roll + "'";
                ResultSet rs = c.statement.executeQuery(query);
                if (rs.next()) {
                    textName.setText(rs.getString("Name"));
                    comboDeg.setSelectedItem(rs.getString("Degree"));
                    textFatherName.setText(rs.getString("Father_Name"));
                    textMotherName.setText(rs.getString("Mother_Name"));
                    textDob.setText(rs.getString("Date_of_Birth"));
                    textAdmissionRoll.setText(rs.getString("Admission_Roll"));
                    textSession.setText(rs.getString("Session"));
                    textReg.setText(rs.getString("Registration_No"));
                    textRoll.setText(rs.getString("Roll"));
                    textPermanentAddress.setText(rs.getString("Permanent_Address"));
                    textPresentAddress.setText(rs.getString("Present_Address"));
                    textBloodGroup.setText(rs.getString("Blood_Group"));
                    textPhone.setText(rs.getString("Phone"));
                    textEmail.setText(rs.getString("Email"));
                } else {
                    JOptionPane.showMessageDialog(null, "No record found!", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == update) {
            String Name = textName.getText().trim();
            String Degree = (String) comboDeg.getSelectedItem();
            String Father_Name = textFatherName.getText().trim();
            String Mother_Name = textMotherName.getText().trim();
            String Roll = textRoll.getText().trim();
            String Present_Address = textPresentAddress.getText().trim();
            String Phone = textPhone.getText().trim();
            String Email = textEmail.getText().trim();

            //Field validation
            if (Name.isEmpty() || Degree.isEmpty() || Father_Name.isEmpty() || Mother_Name.isEmpty()
                    || Roll.isEmpty() || Present_Address.isEmpty() || Phone.isEmpty() || Email.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields must be filled!", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (!Name.matches("[a-zA-Z.\\s]+")) {
                JOptionPane.showMessageDialog(this, "Invalid Name! Only letters and spaces allowed.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!Father_Name.matches("[a-zA-Z.\\s]+")) {
                JOptionPane.showMessageDialog(this, "Invalid Father's Name! Only letters and spaces allowed.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!Mother_Name.matches("[a-zA-Z.\\s]+")) {
                JOptionPane.showMessageDialog(this, "Invalid Mother's Name! Only letters and spaces allowed.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!Roll.matches("\\d+")) {
                JOptionPane.showMessageDialog(this, "Invalid Roll! Only digits allowed.", "Error", JOptionPane.ERROR_MESSAGE);
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
                String q = "UPDATE student SET Degree='" + Degree + "', Roll='" + Roll +
                        "', Present_Address='" + Present_Address + "', Phone='" + Phone +
                        "', Email='" + Email + "' WHERE Roll='" + textRoll.getText() + "'";
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

    public static void main(String[] args) {
        new UpdateStudent();
    }
}