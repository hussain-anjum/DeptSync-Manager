package deptSync.Manager;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class FeeStructure extends JFrame implements ActionListener {
    FeeStructure(){
        setTitle("Fee Details - DeptSync Manager");
        setSize(1000, 500);
        setLocationRelativeTo(null);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setLayout(null);

        JLabel heading = new JLabel("- Fee Structure -");
        heading.setBounds(405, 30, 500, 50);
        heading.setFont(new Font("Arial", Font.BOLD, 23));
        add(heading);

        JTable table = new JTable();

        try{
            Conn c = new Conn();
            ResultSet resultSet = c.statement.executeQuery("select * from fee");
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch(Exception e){
            e.printStackTrace();
        }

        JScrollPane js = new JScrollPane(table);
        js.setBounds(0, 120, 1000, 400);
        add(js);


        //Background Image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/Fee.png"));
        Image i2 = i1.getImage().getScaledInstance(1000, 480, Image.SCALE_SMOOTH);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel imageLabel = new JLabel(i3);
        imageLabel.setBounds(0, 0, 900, 400);
        add(imageLabel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String[] args) {
        new FeeStructure();
    }
}
