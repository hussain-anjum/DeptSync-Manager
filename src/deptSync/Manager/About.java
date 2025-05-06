package deptSync.Manager;

import javax.swing.*;
import java.awt.*;

public class About extends JFrame {
    About(){
        setTitle("About - DeptSync Manager");
        setSize(700, 500);
        setLocationRelativeTo(null);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setLayout(null);

        JLabel dept = new JLabel("Department of Computer Science and Engineering");
        dept.setBounds(160,80,500,50);
        dept.setFont(new Font("Arial", Font.BOLD, 15));
        dept.setForeground(new Color(52, 40, 186));
        add(dept);

        JLabel heading = new JLabel("<html><div style='text-align: justify;'>"
                + "Jatiya Kabi Kazi Nazrul Islam University was established by the Government of Bangladesh on 01 March 2005. "
                + "The academic program of this university started on 03 June 2007 with four departments under two Faculties. "
                + "The department of Computer Science and Engineering is one of them. "
                + "The development of the present state of the society greatly depends on technological improvement."
                + "</div></html>");
        heading.setBounds(60,75,570,300);
        heading.setFont(new Font("Arial", Font.BOLD, 17));
        heading.setForeground(new Color(52, 40, 186));
        add(heading);

        JLabel name = new JLabel("Professor Dr. Md. Sujan Ali");
        name.setBounds(460,310,200,50);
        name.setFont(new Font("Arial", Font.BOLD, 13));
        name.setForeground(new Color(52, 40, 186));
        add(name);

        JLabel head = new JLabel("Head of the Department");
        head.setBounds(460,337,200,30);
        head.setFont(new Font("Arial", Font.BOLD, 11));
        head.setForeground(new Color(52, 40, 186));
        add(head);

        // Background Image
        ImageIcon bgIcon = new ImageIcon(ClassLoader.getSystemResource("icon/About Back.png"));
        JLabel bgLabel = new JLabel(bgIcon);
        bgLabel.setBounds(0, 0, 700, 500); // Ensure full coverage
        add(bgLabel);


        setVisible(true);
    }
    public static void main(String[] args) {
        new About();
    }
}
