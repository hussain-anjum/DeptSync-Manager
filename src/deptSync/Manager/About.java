package deptSync.Manager;

import javax.swing.*;
import java.awt.*;

public class About extends JFrame {
    About(){
        setTitle("About - DeptSync Manager");
        setSize(700, 500);
        setLocationRelativeTo(null);

        // About the project text
        JLabel aboutText = new JLabel("<html><div style='text-align: justify;'>"
                + "DeptSync Manager is a departmental management system developed for the "
                + "Department of Computer Science and Engineering, Jatiya Kabi Kazi Nazrul Islam University. "
                + "This project helps in managing departmental activities "
                + "and administrative tasks in a digital way.<br><br>"
                + "<b>Developed by:</b> Md. Hussain Anjum Ratul<br>"
                + "<b>Supervisor:</b> Dr. Habiba Sultana (Associate Professor)<br>"
                + "<b>Project Year:</b> 2nd Year, 2nd Semester<br>"
                + "<b>Technologies Used:</b> Java (Swing), MySQL Database, JDBC, IntelliJ<br><br>"
                + "This project aims to digitalize departmental processes and make academic management "
                + "faster, more efficient, and paperless."
                + "</div></html>");
        aboutText.setBounds(50, 70, 600, 300);
        aboutText.setFont(new Font("Calibri", Font.PLAIN, 16));
        aboutText.setForeground(new Color(52, 40, 186));
        add(aboutText);

        JLabel devName = new JLabel("— Thank You");
        devName.setBounds(480, 350, 300, 30);
        devName.setFont(new Font("Arial", Font.BOLD, 13));
        devName.setForeground(new Color(52, 40, 186));
        add(devName);

        // Background Image
        ImageIcon bgIcon = new ImageIcon(ClassLoader.getSystemResource("icon/About Back.png"));
        JLabel bgLabel = new JLabel(bgIcon);
        bgLabel.setBounds(0, 0, 700, 500);
        add(bgLabel);

        setVisible(true);
    }

    public static void main(String[] args) {
        new About();
    }
}
