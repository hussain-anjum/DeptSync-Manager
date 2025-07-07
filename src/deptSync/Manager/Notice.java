package deptSync.Manager;

import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

public class Notice extends JFrame implements ActionListener {

    JDateChooser noticeDate;
    JTextArea textNotice;
    JButton save, print, cancel;

    Notice() {
        setTitle("Notice - DeptSync Manager");
        setSize(900, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setLayout(null);

        JLabel heading = new JLabel("- Create Notice -");
        heading.setBounds(340, 30, 400, 50);
        heading.setFont(new Font("Arial", Font.BOLD, 22));
        add(heading);

        JLabel lblDate = new JLabel("Select Date");
        lblDate.setBounds(80, 110, 150, 30);
        lblDate.setFont(new Font("Arial", Font.BOLD, 17));
        add(lblDate);

        noticeDate = new JDateChooser();
        noticeDate.setBounds(200, 112, 200, 25);
        noticeDate.setFont(new Font("Arial", Font.BOLD, 12));
        add(noticeDate);

        JLabel lblNotice = new JLabel("Notice");
        lblNotice.setBounds(80, 160, 150, 30);
        lblNotice.setFont(new Font("Arial", Font.BOLD, 17));
        add(lblNotice);

        textNotice = new JTextArea();
        textNotice.setLineWrap(true);
        textNotice.setWrapStyleWord(true);
        textNotice.setFont(new Font("Arial", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(textNotice);
        scrollPane.setBounds(200, 160, 600, 300);
        add(scrollPane);

        save = new JButton("Save");
        save.setBounds(240, 500, 120, 30);
        save.setBackground(new Color(52, 40, 186));
        save.setForeground(Color.WHITE);
        save.addActionListener(this);
        add(save);

        print = new JButton("Print");
        print.setBounds(380, 500, 120, 30);
        print.setBackground(new Color(52, 40, 186));
        print.setForeground(Color.WHITE);
        print.addActionListener(this);
        add(print);

        cancel = new JButton("Cancel");
        cancel.setBounds(520, 500, 120, 30);
        cancel.setBackground(new Color(52, 40, 186));
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        add(cancel);

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
        if (e.getSource() == save) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String date = sdf.format(noticeDate.getDate());
                String text = textNotice.getText();

                if (text.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Notice text cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Conn c = new Conn();
                String q = "INSERT INTO notice (Date, Notice) VALUES ('" + date + "', '" + text + "')";
                c.statement.executeUpdate(q);
                JOptionPane.showMessageDialog(null, "Notice saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == print) {
            printNotice();
        } else {
            setVisible(false);
        }
    }

    private void printNotice() {
        String date = ((JTextField) noticeDate.getDateEditor().getUiComponent()).getText();
        String text = textNotice.getText();

        JTextPane textPane = new JTextPane();
        textPane.setContentType("text/html");

        String html =
                "<html><body style='font-family:Arial; padding:30px 15px 30px 15px;'>" +
                        "<div style='text-align:center;'>" +
                        "<h1 style='font-size:16px; text-decoration: underline;'>Notice</h1>" +
                        "</div>" +
                        "<div style='margin-top:20px; font-size:12px; text-align:left;'>" +
                        "<p><b>Date:</b> " + date + "</p>" +
                        "<p style='margin-top:20px; text-align:justify;'>" + text.replaceAll("\n", "<br>") + "</p>" +
                        "<br><br><br><br><br>" + // Space for signature
                        "<p><b>_________________________</b></p>" +
                        "<div style='font-size:10px;'>" +
                        "(Professor Dr. Md. Sujan Ali)<br>" +
                        "Head of Department<br>" +
                        "Department of Computer Science and Engineering<br>" +
                        "Jatiya Kabi Kazi Nazrul Islam University<br>" +
                        "Trishal, Mymensingh-2224, Bangladesh." +
                        "</div>" +
                        "</div>" +
                        "</body></html>";

        textPane.setText(html);
        textPane.setEditable(false);

        try {
            textPane.print();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Notice();
    }
}