package deptSync.Manager;

import javax.swing.*;
import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

public class Login extends JFrame {

    //Password hashing methods
    private static String hashPassword(String password, String salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt.getBytes());
            byte[] hashedPassword = md.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hashedPassword);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    //1st time password setup method
    public static void createHashedPassword(String username, String plainPassword) {
        String salt = generateSalt();
        String hashedPassword = hashPassword(plainPassword, salt);

        try {
            Conn c = new Conn();
            PreparedStatement pst = c.prepareStatement("INSERT INTO login (username, password, salt) VALUES (?, ?, ?)");
            pst.setString(1, username);
            pst.setString(2, hashedPassword);
            pst.setString(3, salt);
            pst.executeUpdate();
            pst.close();
            System.out.println("Password hashed and saved successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    Login() {
        setTitle("Admin Login - DeptSync Manager");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        //Background image
        ImageIcon bgImage = new ImageIcon(ClassLoader.getSystemResource("icon/Login image.png"));
        JLabel backgroundLabel = new JLabel(bgImage);
        backgroundLabel.setLayout(new BorderLayout());

        //Login form Panel
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridBagLayout());
        loginPanel.setOpaque(false);
        loginPanel.setPreferredSize(new Dimension(350, 250));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblHeading = new JLabel("Log in as Admin user");
        lblHeading.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel lblUser = new JLabel("Username:");
        JTextField txtUser = new JTextField(15);

        JLabel lblPass = new JLabel("Password:");
        JPasswordField txtPass = new JPasswordField(15);

        JButton btnLogin = new JButton("LOGIN");
        btnLogin.setBackground(new Color(52, 40, 186));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);

        //Login action
        btnLogin.addActionListener(e -> {
            if(e.getSource() == btnLogin){
                String username = txtUser.getText();
                String password = new String(txtPass.getPassword());

                String query = "SELECT password, salt FROM login WHERE username = ?";
                try{
                    Conn c = new Conn();
                    PreparedStatement pst = c.prepareStatement(query);
                    pst.setString(1, username);
                    ResultSet resultSet = pst.executeQuery();

                    if(resultSet.next()){
                        String storedHash = resultSet.getString("password");
                        String salt = resultSet.getString("salt");

                        //Hash input password with stored salt and compare
                        String hashedInput = hashPassword(password, salt);

                        if(storedHash.equals(hashedInput)){
                            setVisible(false);
                            new Home();
                        } else {
                            JOptionPane.showMessageDialog(this, "Invalid Username or Password!", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Invalid Username or Password!", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                    pst.close();
                    resultSet.close();

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Database connection error!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        //adding components to Panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        loginPanel.add(lblHeading, gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 1;
        loginPanel.add(lblUser, gbc);
        gbc.gridx = 1;
        loginPanel.add(txtUser, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        loginPanel.add(lblPass, gbc);
        gbc.gridx = 1;
        loginPanel.add(txtPass, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        loginPanel.add(btnLogin, gbc);

        //Right-aligning the login panel
        JPanel containerPanel = new JPanel(new BorderLayout());
        containerPanel.setOpaque(false);
        containerPanel.add(loginPanel, BorderLayout.EAST);

        backgroundLabel.add(containerPanel, BorderLayout.CENTER);
        add(backgroundLabel);
        setVisible(true);
    }

    public static void main(String[] args) {
        //1st time run ==>
        //createHashedPassword("csejkkniu", "admin123");

        new Login();
    }
}