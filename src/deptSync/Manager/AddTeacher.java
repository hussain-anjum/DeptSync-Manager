package deptSync.Manager;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class AddTeacher extends JFrame implements ActionListener {

    JTextField textName, textTeacherID, textNid, textPermanentAddress, textPresentAddress, textPhone, textEmail, textEducation, textAccountNo;
    JComboBox<String> comboDes, comboBlood;
    JDateChooser cjoin;
    JButton submit, cancel;

    AddTeacher(){
        setTitle("Add Information - DeptSync Manager");
        setSize(900, 700);
        setLocationRelativeTo(null);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel heading = new JLabel("- Add New Teacher Details -");
        heading.setBounds(310, 30, 500, 50);
        heading.setFont(new Font("Arial", Font.BOLD, 20));
        add(heading);

        //Labels and Input Fields
        String[] labels = {"Name", "Designation", "Teacher ID", "NID", "Joining Date", "Permanent Address",
                            "Present Address", "Account No.", "Phone", "Email", "Education", "Blood Group"};

        int x1 = 65, x2 = 480, y = 150, width = 160, height = 30;

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
            } else if (labels[i].equals("Teacher ID")) {
                textTeacherID = new JTextField(generateTeacherID());
                textTeacherID.setBounds((i % 2 == 0 ? x1 : x2) + 150, y + 2, 200, 25);
                textTeacherID.setFont(new Font("Arial", Font.BOLD, 12));
                textTeacherID.setEditable(false);
                add(textTeacherID);
            } else if (labels[i].equals("Joining Date")) {
                cjoin = new JDateChooser();
                cjoin.setBounds((i % 2 == 0 ? x1 : x2) + 150, y + 2, 200, 25);
                cjoin.setFont(new Font("Arial",Font.BOLD,12));
                add(cjoin);
            } else if (labels[i].equals("Blood Group")) {
                String[] bloodGroups = {"A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-"};
                comboBlood = new JComboBox<>(bloodGroups);
                comboBlood.setBounds((i % 2 == 0 ? x1 : x2) + 150, y + 2, 200, 25);
                add(comboBlood);
            } else {
                JTextField textField = new JTextField();
                textField.setBounds((i % 2 == 0 ? x1 : x2) + 150, y + 2, 200, 25);
                textField.setFont(new Font("Arial", Font.BOLD, 12));
                add(textField);

                switch (labels[i]) {
                    case "Name" -> textName = textField;
                    case "NID" -> textNid = textField;
                    case "Permanent Address" -> textPermanentAddress = textField;
                    case "Present Address" -> textPresentAddress = textField;
                    case "Account No." -> textAccountNo = textField;
                    case "Phone" -> textPhone = textField;
                    case "Email" -> textEmail = textField;
                    case "Education" -> textEducation = textField;
                }
            }
            if (i % 2 != 0) y += 50;
        }

        //Submit and Cancel Buttons
        submit = new JButton("Submit");
        submit.setBounds(300, 525, 120, 30);
        submit.setBackground(new Color(52, 40, 186));
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        add(submit);

        cancel = new JButton("Cancel");
        cancel.setBounds(480, 525, 120, 30);
        cancel.setBackground(new Color(52, 40, 186));
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        add(cancel);

        //Background Image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/add info background.png"));
        Image i2 = i1.getImage().getScaledInstance(900, 730, Image.SCALE_SMOOTH);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel imageLabel = new JLabel(i3);
        imageLabel.setBounds(0, 0, 900, 730);
        add(imageLabel);

        setVisible(true);
    }

    private String generateTeacherID() {
        Random rand = new Random();
        return "TID" + (1000 + rand.nextInt(9000)); //Generates TID1000-TID9999
    }

    private void showError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == submit){
            String Name = textName.getText().trim();
            String Designation = (String) comboDes.getSelectedItem();
            String Teacher_ID = textTeacherID.getText().trim();
            String NID = textNid.getText().trim();
            String Joining_Date = ((JTextField) cjoin.getDateEditor().getUiComponent()).getText().trim();
            String Permanent_Address = textPermanentAddress.getText().trim();
            String Present_Address = textPresentAddress.getText().trim();
            String Account_No = textAccountNo.getText().trim();
            String Phone = textPhone.getText().trim();
            String Email = textEmail.getText().trim();
            String Education = textEducation.getText().trim();
            String Blood_Group = (String) comboBlood.getSelectedItem();

            //Required field check
            if (Name.isEmpty() || Designation.isEmpty() || Teacher_ID.isEmpty() || NID.isEmpty() ||
                    Joining_Date.isEmpty() || Permanent_Address.isEmpty() || Present_Address.isEmpty() ||
                    Account_No.isEmpty() || Phone.isEmpty() || Email.isEmpty() || Education.isEmpty() || Blood_Group.isEmpty()) {
                showError("All fields must be filled!");
                return;
            }

            //Validations
            if (!Name.matches("[a-zA-Z.\\s]+")) {
                showError("Invalid Name!");
                return;
            }
            if (!NID.matches("\\d+")) {
                showError("Invalid NID! Only digits allowed.");
                return;
            }
            if (!Phone.matches("\\d{11}")) {
                showError("Invalid Phone! Must be 11 digits.");
                return;
            }
            if (!Email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
                showError("Invalid Email format!");
                return;
            }
            if (!Account_No.matches("\\d+")) {
                showError("Invalid Account No! Only digits allowed.");
                return;
            }

            //All passed then Insert into DB
            try {
                String q = "INSERT INTO teacher VALUES('" + Name + "', '" + Designation + "','" + Teacher_ID + "','" + NID + "','" + Joining_Date + "','" + Permanent_Address + "','" + Present_Address + "', '" + Account_No + "', '" + Phone + "','" + Email + "','" + Education + "','" + Blood_Group + "')";
                Conn c = new Conn();
                c.statement.executeUpdate(q);
                JOptionPane.showMessageDialog(null, "Information submitted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                //setVisible(false);
            } catch(Exception ex){
                ex.printStackTrace();
            }
        }else{
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new AddTeacher();
    }
}