package deptSync.Manager;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class AddStudent extends JFrame implements ActionListener{

    JTextField textName, textFatherName, textMotherName, textAdmissionRoll, textSession, textReg, textRoll, textHscRoll, textHscGPA, textAddress, textPhone, textEmail;
    JComboBox<String> comboDeg;
    JDateChooser cdob;
    JButton submit, cancel;

    AddStudent(){
        setTitle("Add Information - DeptSync Manager");
        setSize(900, 700);
        setLocationRelativeTo(null);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setLayout(null); // Using null layout for precise positioning

        JLabel heading = new JLabel("- Add New Student Details -");
        heading.setBounds(310, 30, 500, 50);
        heading.setFont(new Font("Arial", Font.BOLD, 20));
        add(heading);

        //Labels and Input Fields
        String[] labels = {"Name", "Degree", "Father’s Name", "Mother’s Name","Date of Birth","Admission Roll","Session","Registration No.","Roll","HSC Roll","HSC GPA", "Address", "Phone", "Email"};
        int x1 = 65, x2 = 500, y = 150, width = 120, height = 30;
        JTextField[] textFields = new JTextField[labels.length];

        for (int i = 0; i < labels.length; i++) {
            JLabel lbl = new JLabel(labels[i]);
            lbl.setBounds(i % 2 == 0 ? x1 : x2, y, width, height);
            lbl.setFont(new Font("Arial", Font.BOLD, 15));
            add(lbl);

            if (labels[i].equals("Degree")) {
                String[] degree = {"BSc.(Engg.)", "MSc."};
                comboDeg = new JComboBox<>(degree);
                comboDeg.setBounds((i % 2 == 0 ? x1 : x2) + 130, y + 2, 200, 25);
                add(comboDeg);
            } else if (labels[i].equals("Date of Birth")) {
                cdob = new JDateChooser();
                cdob.setBounds((i % 2 == 0 ? x1 : x2) + 130, y + 2, 200, 25);
                cdob.setFont(new Font("Arial",Font.BOLD,12));
                add(cdob);
            } else if (labels[i].equals("Registration No.")) {
                textReg = new JTextField(generateReg());
                textReg.setBounds((i % 2 == 0 ? x1 : x2) + 130, y + 2, 200, 25);
                textReg.setFont(new Font("Arial", Font.BOLD, 12));
                textReg.setEditable(false);
                add(textReg);
            }else {
                JTextField textField = new JTextField();
                textField.setBounds((i % 2 == 0 ? x1 : x2) + 130, y + 2, 200, 25);
                textField.setFont(new Font("Arial", Font.BOLD, 12));
                add(textField);

                switch (labels[i]) {
                    case "Name" -> textName = textField;
                    case "Father’s Name" -> textFatherName = textField;
                    case "Mother’s Name" -> textMotherName = textField;
                    case "Admission Roll" -> textAdmissionRoll = textField;
                    case "Session" -> textSession = textField;
                    case "Roll" -> textRoll = textField;
                    case "HSC Roll" -> textHscRoll = textField;
                    case "HSC GPA" -> textHscGPA = textField;
                    case "Address" -> textAddress = textField;
                    case "Phone" -> textPhone = textField;
                    case "Email" -> textEmail = textField;
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
        Image i2 = i1.getImage().getScaledInstance(900, 680, Image.SCALE_SMOOTH);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel imageLabel = new JLabel(i3);
        imageLabel.setBounds(0, 0, 900, 700); // Cover the full window
        add(imageLabel);

        setVisible(true);

    }
    private String generateReg() {
        Random rand = new Random();
        return "10" + (100 + rand.nextInt(900)); //Generates 10100 to 10999
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            String Name = textName.getText().trim();
            String Degree = (String) comboDeg.getSelectedItem();
            String Father_Name = textFatherName.getText().trim();
            String Mother_Name = textMotherName.getText().trim();
            String Date_of_Birth = ((JTextField) cdob.getDateEditor().getUiComponent()).getText().trim();
            String Admission_Roll = textAdmissionRoll.getText().trim();
            String Session = textSession.getText().trim();
            String Registration_No = textReg.getText().trim();
            String Roll = textRoll.getText().trim();
            String HSC_Roll = textHscRoll.getText().trim();
            String HSC_GPA = textHscGPA.getText().trim();
            String Address = textAddress.getText().trim();
            String Phone = textPhone.getText().trim();
            String Email = textEmail.getText().trim();

            //Required field check
            if (Name.isEmpty() || Degree.isEmpty() || Father_Name.isEmpty() || Mother_Name.isEmpty()
                    || Date_of_Birth.isEmpty() || Admission_Roll.isEmpty() || Session.isEmpty()
                    || Registration_No.isEmpty() || Roll.isEmpty() || HSC_Roll.isEmpty()
                    || HSC_GPA.isEmpty() || Address.isEmpty() || Phone.isEmpty() || Email.isEmpty()) {
                showError("All fields must be filled!");
                return;
            }

            //Validations
            if (!Name.matches("[a-zA-Z\\s]+")) {
                showError("Invalid Name! Only letters and spaces allowed.");
                return;
            }
            if (!Father_Name.matches("[a-zA-Z\\s]+")) {
                showError("Invalid Father's Name! Only letters and spaces allowed.");
                return;
            }
            if (!Mother_Name.matches("[a-zA-Z\\s]+")) {
                showError("Invalid Mother's Name! Only letters and spaces allowed.");
                return;
            }
            if (!Roll.matches("\\d+")) {
                showError("Invalid Roll! Only digits allowed.");
                return;
            }
            if (!Phone.matches("\\d{11}")) {
                showError("Invalid Phone! Must be 11 digits only.");
                return;
            }
            if (!Email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
                showError("Invalid Email format!");
                return;
            }

            //All passed then Insert into DB
            try {
                String q = "INSERT INTO student VALUES('" + Name + "', '" + Degree + "','" + Father_Name + "','" + Mother_Name + "','" + Date_of_Birth + "', '" + Admission_Roll + "', '" + Session + "', '" + Registration_No + "', '" + Roll + "', '" + HSC_Roll + "', '" + HSC_GPA + "','" + Address + "','" + Phone + "','" + Email + "')";
                Conn c = new Conn();
                c.statement.executeUpdate(q);
                JOptionPane.showMessageDialog(null, "Information submitted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                setVisible(false);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }else{
            setVisible(false);
        }
    }
    public static void main(String[] args) {
        new AddStudent();
    }
}
