package deptSync.Manager;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class AddTeacher extends JFrame implements ActionListener {

    JTextField textName, textTeacherID, textNid, textAddress, textPhone, textEmail, textEducation, textExperience;
    JComboBox<String> comboDes;
    JDateChooser cdob;
    JButton submit, cancel;

    AddTeacher(){
        setTitle("Add Information - DeptSync Manager");
        setSize(900, 700);
        setLocationRelativeTo(null);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setLayout(null); // Using null layout for precise positioning

        JLabel heading = new JLabel("- Add New Teacher Details -");
        heading.setBounds(310, 30, 500, 50);
        heading.setFont(new Font("Arial", Font.BOLD, 20));
        add(heading);

        //Labels and Input Fields
        String[] labels = {"Name", "Designation", "Teacher ID", "NID", "Date of Birth", "Address", "Phone", "Email", "Education", "Experience"};
        int x1 = 65, x2 = 500, y = 150, width = 120, height = 30;
        JTextField[] textFields = new JTextField[labels.length];

        for (int i = 0; i < labels.length; i++) {
            JLabel lbl = new JLabel(labels[i]);
            lbl.setBounds(i % 2 == 0 ? x1 : x2, y, width, height);
            lbl.setFont(new Font("Arial", Font.BOLD, 15));
            add(lbl);

            if (labels[i].equals("Designation")) {
                String[] designations = {"Professor", "Associate Professor", "Assistant Professor", "Lecturer"};
                comboDes = new JComboBox<>(designations);
                comboDes.setBounds((i % 2 == 0 ? x1 : x2) + 130, y + 2, 200, 25);
                add(comboDes);
            } else if (labels[i].equals("Teacher ID")) {
                textTeacherID = new JTextField(generateTeacherID());
                textTeacherID.setBounds((i % 2 == 0 ? x1 : x2) + 130, y + 2, 200, 25);
                textTeacherID.setFont(new Font("Arial", Font.BOLD, 12));
                textTeacherID.setEditable(false);
                add(textTeacherID);
            } else if (labels[i].equals("Date of Birth")) {
                cdob = new JDateChooser();
                cdob.setBounds((i % 2 == 0 ? x1 : x2) + 130, y + 2, 200, 25);
                cdob.setFont(new Font("Arial",Font.BOLD,12));
                add(cdob);
            } else {
                JTextField textField = new JTextField();
                textField.setBounds((i % 2 == 0 ? x1 : x2) + 130, y + 2, 200, 25);
                textField.setFont(new Font("Arial", Font.BOLD, 12));
                add(textField);

                switch (labels[i]) {
                    case "Name" -> textName = textField;
                    case "NID" -> textNid = textField;
                    case "Address" -> textAddress = textField;
                    case "Phone" -> textPhone = textField;
                    case "Email" -> textEmail = textField;
                    case "Education" -> textEducation = textField;
                    case "Experience" -> textExperience = textField;
                }
            }
            if (i % 2 != 0) y += 50;
        }
        //Submit and Cancel Buttons
        submit = new JButton("Submit");
        submit.setBounds(300, 450, 120, 30);
        submit.setBackground(new Color(52, 40, 186));
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        add(submit);

        cancel = new JButton("Cancel");
        cancel.setBounds(480, 450, 120, 30);
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
            String Date_of_Birth = ((JTextField) cdob.getDateEditor().getUiComponent()).getText().trim();
            String Address = textAddress.getText().trim();
            String Phone = textPhone.getText().trim();
            String Email = textEmail.getText().trim();
            String Education = textEducation.getText().trim();
            String Experience = textExperience.getText().trim();

            //Required field check
            if (Name.isEmpty() || Designation.isEmpty() || Teacher_ID.isEmpty() || NID.isEmpty() ||
                    Date_of_Birth.isEmpty() || Address.isEmpty() || Phone.isEmpty() || Email.isEmpty() ||
                    Education.isEmpty() || Experience.isEmpty()) {
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

            //All passed then Insert into DB
            try {
                String q = "INSERT INTO teacher VALUES('" + Name + "', '" + Designation + "','" + Teacher_ID + "','" + NID + "','" + Date_of_Birth + "','" + Address + "','" + Phone + "','" + Email + "','" + Education + "','" + Experience + "')";
                Conn c = new Conn();
                c.statement.executeUpdate(q);
                JOptionPane.showMessageDialog(null, "Information submitted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                setVisible(false);
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