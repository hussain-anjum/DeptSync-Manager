package deptSync.Manager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home extends JFrame implements ActionListener{
    Home() {
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/DeptSync Manager main background.png"));
        Image i2 = i1.getImage().getScaledInstance(1540, 790, Image.SCALE_SMOOTH); // Improved quality
        ImageIcon i3 = new ImageIcon(i2);
        JLabel imageLabel = new JLabel(i3);
        add(imageLabel);

        setTitle("Home - DeptSync Manager");
        setSize(1540, 850);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Menu Bar ==>>
        JMenuBar mb = new JMenuBar();
        //mb.setBackground(new Color(133, 171, 255));
        mb.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // Add Information Menu ==>>
        JMenu addInfo = new JMenu("Add Information");
        addInfo.setForeground(new Color(52, 40, 186));
        addInfo.setFont(new Font("Arial", Font.BOLD, 14));
        mb.add(addInfo);

        // Menu Items
        JMenuItem teacherInfo = new JMenuItem("Add new teacher details", new ImageIcon(ClassLoader.getSystemResource("icon/teacher.png")));
        teacherInfo.setBackground(Color.WHITE);
        teacherInfo.setFont(new Font("Arial", Font.BOLD, 12));
        teacherInfo.addActionListener(this);
        addInfo.add(teacherInfo);

        JMenuItem studentInfo = new JMenuItem("Add new student details", new ImageIcon(ClassLoader.getSystemResource("icon/student.png")));
        studentInfo.setBackground(Color.WHITE);
        studentInfo.setFont(new Font("Arial", Font.BOLD, 12));
        studentInfo.addActionListener(this);
        addInfo.add(studentInfo);

        //View Information ==>>
        JMenu details = new JMenu("View Information");
        details.setForeground(new Color(52, 40, 186));
        details.setFont(new Font("Arial", Font.BOLD, 14));
        mb.add(details);

        // Menu Items
        JMenuItem teacherDetails = new JMenuItem("View teacher details", new ImageIcon(ClassLoader.getSystemResource("icon/teacher details.png")));
        teacherDetails.setBackground(Color.WHITE);
        teacherDetails.setFont(new Font("Arial", Font.BOLD, 12));
        teacherDetails.addActionListener(this);
        details.add(teacherDetails);

        JMenuItem studentDetails = new JMenuItem("View student details", new ImageIcon(ClassLoader.getSystemResource("icon/student details.png")));
        studentDetails.setBackground(Color.WHITE);
        studentDetails.setFont(new Font("Arial", Font.BOLD, 12));
        studentDetails.addActionListener(this);
        details.add(studentDetails);

        //Update details ==>>
        JMenu updateDetails = new JMenu("Update Information");
        updateDetails.setForeground(new Color(52, 40, 186));
        updateDetails.setFont(new Font("Arial", Font.BOLD, 14));
        mb.add(updateDetails);

        // Menu Items
        JMenuItem update_teacherDetails = new JMenuItem("Update teacher details", new ImageIcon(ClassLoader.getSystemResource("icon/u_t_details.png")));
        update_teacherDetails.setBackground(Color.WHITE);
        update_teacherDetails.setFont(new Font("Arial", Font.BOLD, 12));
        update_teacherDetails.addActionListener(this);
        updateDetails.add(update_teacherDetails);

        JMenuItem update_studentDetails = new JMenuItem("Update student details", new ImageIcon(ClassLoader.getSystemResource("icon/u_s_details.png")));
        update_studentDetails.setBackground(Color.WHITE);
        update_studentDetails.setFont(new Font("Arial", Font.BOLD, 12));
        update_studentDetails.addActionListener(this);
        updateDetails.add(update_studentDetails);

        //Leave ==>>
        JMenu leave = new JMenu("Apply Leave");
        leave.setForeground(new Color(52, 40, 186));
        leave.setFont(new Font("Arial", Font.BOLD, 14));
        mb.add(leave);

        // Menu Items
        JMenuItem teacherLeave = new JMenuItem("Teacher's Leave", new ImageIcon(ClassLoader.getSystemResource("icon/teacher leave.png")));
        teacherLeave.setBackground(Color.WHITE);
        teacherLeave.setFont(new Font("Arial", Font.BOLD, 12));
        teacherLeave.addActionListener(this);
        leave.add(teacherLeave);

        JMenuItem studentLeave = new JMenuItem("Student's Leave", new ImageIcon(ClassLoader.getSystemResource("icon/student leave.png")));
        studentLeave.setBackground(Color.WHITE);
        studentLeave.setFont(new Font("Arial", Font.BOLD, 12));
        studentLeave.addActionListener(this);
        leave.add(studentLeave);

        //Leave details ==>>
        JMenu leaveDetails = new JMenu("Leave Details");
        leaveDetails.setForeground(new Color(52, 40, 186));
        leaveDetails.setFont(new Font("Arial", Font.BOLD, 14));
        mb.add(leaveDetails);

        // Menu Items
        JMenuItem teacherLeaveDetails = new JMenuItem("Teacher's Leave Details", new ImageIcon(ClassLoader.getSystemResource("icon/T_leave details.png")));
        teacherLeaveDetails.setBackground(Color.WHITE);
        teacherLeaveDetails.setFont(new Font("Arial", Font.BOLD, 12));
        teacherLeaveDetails.addActionListener(this);
        leaveDetails.add(teacherLeaveDetails);

        JMenuItem studentLeaveDetails = new JMenuItem("Student's Leave Details", new ImageIcon(ClassLoader.getSystemResource("icon/S_leave details.png")));
        studentLeaveDetails.setBackground(Color.WHITE);
        studentLeaveDetails.setFont(new Font("Arial", Font.BOLD, 12));
        studentLeaveDetails.addActionListener(this);
        leaveDetails.add(studentLeaveDetails);

        //Examination ==>>
        //JMenu exam = new JMenu("Examination");
        //exam.setForeground(new Color(52, 40, 186));
        //exam.setFont(new Font("Arial", Font.BOLD, 14));
        //mb.add(exam);

        // Menu Items
        //JMenuItem marks = new JMenuItem("Enter Marks", new ImageIcon(ClassLoader.getSystemResource("icon/enter mark.png")));
        //marks.setBackground(Color.WHITE);
        //marks.setFont(new Font("Arial", Font.BOLD, 12));
        //exam.add(marks);

        //JMenuItem semResult = new JMenuItem("Semester results", new ImageIcon(ClassLoader.getSystemResource("icon/sem results.png")));
        //semResult.setBackground(Color.WHITE);
        //semResult.setFont(new Font("Arial", Font.BOLD, 12));
        //exam.add(semResult);

        //Fee details ==>>
        JMenu fee = new JMenu("Fee Details");
        fee.setForeground(new Color(52, 40, 186));
        fee.setFont(new Font("Arial", Font.BOLD, 14));
        mb.add(fee);

        // Menu Items
        JMenuItem feeStructure = new JMenuItem("Fee Structure", new ImageIcon(ClassLoader.getSystemResource("icon/fees.png")));
        feeStructure.setBackground(Color.WHITE);
        feeStructure.setFont(new Font("Arial", Font.BOLD, 12));
        feeStructure.addActionListener(this);
        fee.add(feeStructure);

        JMenuItem feeForm = new JMenuItem("Fee Payment", new ImageIcon(ClassLoader.getSystemResource("icon/Fee Payment.png")));
        feeForm.setBackground(Color.WHITE);
        feeForm.setFont(new Font("Arial", Font.BOLD, 12));
        feeForm.addActionListener(this);
        fee.add(feeForm);

        //Utility ==>>
        JMenu utility = new JMenu("Utility");
        utility.setForeground(new Color(52, 40, 186));
        utility.setFont(new Font("Arial", Font.BOLD, 14));
        mb.add(utility);

        // Menu Items
        JMenuItem calculator = new JMenuItem("Calculator", new ImageIcon(ClassLoader.getSystemResource("icon/calculator.png")));
        calculator.setBackground(Color.WHITE);
        calculator.setFont(new Font("Arial", Font.BOLD, 12));
        calculator.addActionListener(this);
        utility.add(calculator);

        JMenuItem excel = new JMenuItem("Microsoft Excel", new ImageIcon(ClassLoader.getSystemResource("icon/excel.png")));
        excel.setBackground(Color.WHITE);
        excel.setFont(new Font("Arial", Font.BOLD, 12));
        excel.addActionListener(this);
        utility.add(excel);

        JMenuItem word = new JMenuItem("Microsoft Word", new ImageIcon(ClassLoader.getSystemResource("icon/word.png")));
        word.setBackground(Color.WHITE);
        word.setFont(new Font("Arial", Font.BOLD, 12));
        word.addActionListener(this);
        utility.add(word);

        //About ==>>
        JMenu about = new JMenu("About");
        about.setForeground(new Color(52, 40, 186));
        about.setFont(new Font("Arial", Font.BOLD, 14));
        mb.add(about);

        // Menu Items
        JMenuItem About = new JMenuItem("Department of CSE", new ImageIcon(ClassLoader.getSystemResource("icon/logo icon.png")));
        About.setBackground(Color.WHITE);
        About.setFont(new Font("Arial", Font.BOLD, 12));
        About.addActionListener(this);
        about.add(About);

        //Exit ==>>
        JMenu exit = new JMenu("Exit");
        exit.setForeground(new Color(52, 40, 186));
        exit.setFont(new Font("Arial", Font.BOLD, 14));
        mb.add(exit);

        // Menu Items
        JMenuItem Exit = new JMenuItem("", new ImageIcon(ClassLoader.getSystemResource("icon/exit.png")));
        Exit.setBackground(Color.WHITE);
        Exit.setFont(new Font("Arial", Font.BOLD, 12));
        Exit.addActionListener(this);
        exit.add(Exit);


        // Adding Hover Effect
        UIManager.put("Menu.selectionBackground", new Color(70, 130, 180));
        UIManager.put("MenuItem.selectionBackground", new Color(70, 130, 180));

        setJMenuBar(mb);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if(s.equals("")){
            System.exit(0);
        } else if (s.equals("Calculator")) {
            try{
                Runtime.getRuntime().exec("calc.exe");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (s.equals("Microsoft Excel")) {
            try {
                Runtime.getRuntime().exec("cmd /c start excel");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (s.equals("Microsoft Word")) {
            try {
                Runtime.getRuntime().exec("cmd /c start winword");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (s.equals("Add new teacher details")) {
            new AddTeacher();
        } else if (s.equals("Add new student details")) {
            new AddStudent();
        } else if (s.equals("View teacher details")) {
            new TeacherDetails();
        } else if (s.equals("View student details")) {
            new StudentDetails();
        } else if (s.equals("Teacher's Leave")) {
            new TeacherLeave();
        } else if (s.equals("Student's Leave")) {
            new StudentLeave();
        } else if (s.equals("Department of CSE")) {
            new About();
        } else if (s.equals("Teacher's Leave Details")) {
            new TeacherLeaveDetails();
        } else if (s.equals("Student's Leave Details")) {
            new StudentLeaveDetails();
        } else if (s.equals("Update teacher details")) {
            new UpdateTeacher();
        } else if (s.equals("Update student details")) {
            new UpdateStudent();
        } else if (s.equals("Fee Structure")) {
            new FeeStructure();
        } else if (s.equals("Fee Payment")) {
            try {
                Desktop.getDesktop().browse(new java.net.URI("https://billpay.sonalibank.com.bd/JKKNIU/Home/"));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Home();
    }
}
