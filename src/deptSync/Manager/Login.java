package deptSync.Manager;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

public class Login extends JFrame {
    Login() {
        setTitle("Admin Login - DeptSync Manager");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        //background image
        ImageIcon bgImage = new ImageIcon(ClassLoader.getSystemResource("icon/Login image.png"));
        JLabel backgroundLabel = new JLabel(bgImage);
        backgroundLabel.setLayout(new BorderLayout());

        //login form Panel
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

            //if (username.equals("admin") && password.equals("admin123")) {
            //    JOptionPane.showMessageDialog(this, "Login Successful!");
            //    dispose();
            // Proceed to the main system....

            String query = "select * from login where username = '"+username+"' and password = '"+password+"'";
            try{
                Conn c = new Conn();
                ResultSet resultSet = c.statement.executeQuery(query);
                if(resultSet.next()){
                    setVisible(false);
                    new Home();
                }else {
                    JOptionPane.showMessageDialog(this, "Invalid Username or Password!", "Error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (Exception ex) {
                ex.printStackTrace();
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
        new Login();
    }
}
