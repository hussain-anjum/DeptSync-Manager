package deptSync.Manager;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TeacherLeave extends JFrame implements ActionListener {

    JTextField tIdField, tNameSearchField;
    JTextField textName, textDeg, textNid;
    JComboBox<String> comboLeaveType;
    JDateChooser LeaveSD, LeaveED;
    JTextField textTotalLeave;
    JTextArea textLeaveReason;
    JComboBox<String> comboPermissionReq;
    JTextField textPermissionReason;
    JTextField textPhone, textEmail;
    JTextField textPrevEnjoyed, textRemaining;
    JComboBox<String> comboApproval;
    JButton searchTid, searchByNameBtn, submit, print, cancel;

    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    private static final int CASUAL_YEARLY_QUOTA = 20;

    TeacherLeave() {
        setTitle("Apply Leave - DeptSync Manager");
        setSize(1000, 800);
        setLocationRelativeTo(null);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel heading = new JLabel("- Apply For Teacher's Leave -");
        heading.setBounds(370, 10, 400, 40);
        heading.setFont(new Font("Arial", Font.BOLD, 18));
        add(heading);

        JLabel lblSearchTid = new JLabel("Search by TID:");
        lblSearchTid.setBounds(55, 70, 120, 22);
        lblSearchTid.setFont(new Font("Arial", Font.BOLD, 14));
        add(lblSearchTid);

        tIdField = new JTextField();
        tIdField.setBounds(170, 70, 150, 22);
        tIdField.setFont(new Font("Arial", Font.BOLD, 12));
        add(tIdField);

        searchTid = new JButton("Search");
        searchTid.setBounds(330, 70, 80, 22);
        searchTid.setBackground(new Color(52, 40, 186));
        searchTid.setForeground(Color.WHITE);
        searchTid.addActionListener(this);
        add(searchTid);

        JLabel lblSearchName = new JLabel("Search by Name:");
        lblSearchName.setBounds(460, 70, 140, 22);
        lblSearchName.setFont(new Font("Arial", Font.BOLD, 14));
        add(lblSearchName);

        tNameSearchField = new JTextField();
        tNameSearchField.setBounds(600, 70, 160, 22);
        tNameSearchField.setFont(new Font("Arial", Font.BOLD, 12));
        add(tNameSearchField);

        searchByNameBtn = new JButton("Search");
        searchByNameBtn.setBounds(770, 70, 80, 22);
        searchByNameBtn.setBackground(new Color(52, 40, 186));
        searchByNameBtn.setForeground(Color.WHITE);
        searchByNameBtn.addActionListener(this);
        add(searchByNameBtn);

        int x1 = 55, x2 = 520, y = 115, gapY = 40, labelW = 220, fieldW = 220, h = 26;

        JLabel lblName = new JLabel("Teacher Name:");
        lblName.setBounds(x1, y, labelW, h);
        lblName.setFont(new Font("Arial", Font.BOLD, 15));
        add(lblName);

        textName = new JTextField();
        textName.setBounds(x1 + 170, y, fieldW, h);
        textName.setFont(new Font("Arial", Font.BOLD, 12));
        textName.setEditable(false);
        add(textName);

        JLabel lblNid = new JLabel("Teacher's NID:");
        lblNid.setBounds(x2, y, labelW, h);
        lblNid.setFont(new Font("Arial", Font.BOLD, 15));
        add(lblNid);

        textNid = new JTextField();
        textNid.setBounds(x2 + 170, y, fieldW, h);
        textNid.setFont(new Font("Arial", Font.BOLD, 12));
        textNid.setEditable(false);
        add(textNid);

        y += gapY;

        JLabel lblDeg = new JLabel("Designation:");
        lblDeg.setBounds(x1, y, labelW, h);
        lblDeg.setFont(new Font("Arial", Font.BOLD, 15));
        add(lblDeg);

        textDeg = new JTextField();
        textDeg.setBounds(x1 + 170, y, fieldW, h);
        textDeg.setFont(new Font("Arial", Font.BOLD, 12));
        textDeg.setEditable(false);
        add(textDeg);

        JLabel lblLeaveType = new JLabel("Type of leave:");
        lblLeaveType.setBounds(x2, y, labelW, h);
        lblLeaveType.setFont(new Font("Arial", Font.BOLD, 15));
        add(lblLeaveType);

        comboLeaveType = new JComboBox<>(new String[]{"Casual", "Earned", "Medical", "On Duty"});
        comboLeaveType.setBounds(x2 + 170, y, fieldW, h);
        add(comboLeaveType);

        y += gapY;

        JLabel lblStart = new JLabel("Leave Start Date:");
        lblStart.setBounds(x1, y, labelW, h);
        lblStart.setFont(new Font("Arial", Font.BOLD, 15));
        add(lblStart);

        LeaveSD = new JDateChooser();
        LeaveSD.setBounds(x1 + 170, y, fieldW, h);
        LeaveSD.setFont(new Font("Arial", Font.BOLD, 12));
        add(LeaveSD);

        JLabel lblEnd = new JLabel("Leave End Date:");
        lblEnd.setBounds(x2, y, labelW, h);
        lblEnd.setFont(new Font("Arial", Font.BOLD, 15));
        add(lblEnd);

        LeaveED = new JDateChooser();
        LeaveED.setBounds(x2 + 170, y, fieldW, h);
        LeaveED.setFont(new Font("Arial", Font.BOLD, 12));
        add(LeaveED);

        y += gapY;

        JLabel lblTotalLeave = new JLabel("Total Number of Leave Days Requested:");
        lblTotalLeave.setBounds(x1, y, 350, h);
        lblTotalLeave.setFont(new Font("Arial", Font.BOLD, 15));
        add(lblTotalLeave);

        textTotalLeave = new JTextField();
        textTotalLeave.setBounds(x1 + 320, y, 120, h);
        textTotalLeave.setFont(new Font("Arial", Font.BOLD, 13));
        add(textTotalLeave);

        //Auto-calc on date change
        LeaveSD.getDateEditor().addPropertyChangeListener(evt -> onDatesChanged());
        LeaveED.getDateEditor().addPropertyChangeListener(evt -> onDatesChanged());

        //If user edits manually, still recompute remaining (for Casual)
        textTotalLeave.getDocument().addDocumentListener(new SimpleDocListener(this::updateCasualCounters));

        y += gapY;

        JLabel lblReason = new JLabel("Reason for Leave:");
        lblReason.setBounds(x1, y, labelW, h);
        lblReason.setFont(new Font("Arial", Font.BOLD, 15));
        add(lblReason);

        textLeaveReason = new JTextArea();
        textLeaveReason.setLineWrap(true);
        textLeaveReason.setWrapStyleWord(true);
        JScrollPane spReason = new JScrollPane(textLeaveReason);
        spReason.setBounds(x1 + 170, y, 640, 50);
        add(spReason);

        y += (gapY + 38);

        JLabel lblPerm = new JLabel("Is permission required to leave the workplace?");
        lblPerm.setBounds(x1, y, 380, h);
        lblPerm.setFont(new Font("Arial", Font.BOLD, 14));
        add(lblPerm);

        comboPermissionReq = new JComboBox<>(new String[]{"No", "Yes"});
        comboPermissionReq.setBounds(x1 + 340, y, 100, h);
        add(comboPermissionReq);

        JLabel lblPermReason = new JLabel("(If yes, the reason must be mentioned):");
        lblPermReason.setBounds(x1, y + 35, 320, h);
        lblPermReason.setFont(new Font("Arial", Font.PLAIN, 12));
        add(lblPermReason);

        textPermissionReason = new JTextField();
        textPermissionReason.setBounds(x1 + 340, y + 35, 470, h);
        textPermissionReason.setEnabled(false);
        add(textPermissionReason);

        comboPermissionReq.addActionListener(ae -> {
            boolean need = "Yes".equals(comboPermissionReq.getSelectedItem());
            textPermissionReason.setEnabled(need);
            if (!need) textPermissionReason.setText("");
        });

        y += (gapY + 35 + 10);

        JLabel subHead1 = new JLabel("*Contact Information During Leave:");
        subHead1.setBounds(x1, y, 320, h);
        subHead1.setFont(new Font("Arial", Font.ITALIC, 12));
        add(subHead1);

        y += gapY;

        JLabel lblPhone = new JLabel("Phone:");
        lblPhone.setBounds(x1, y, labelW, h);
        lblPhone.setFont(new Font("Arial", Font.BOLD, 15));
        add(lblPhone);

        textPhone = new JTextField();
        textPhone.setBounds(x1 + 170, y, fieldW, h);
        textPhone.setFont(new Font("Arial", Font.BOLD, 12));
        add(textPhone);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(x2, y, labelW, h);
        lblEmail.setFont(new Font("Arial", Font.BOLD, 15));
        add(lblEmail);

        textEmail = new JTextField();
        textEmail.setBounds(x2 + 170, y, fieldW, h);
        textEmail.setFont(new Font("Arial", Font.BOLD, 12));
        add(textEmail);

        y += gapY;

        JLabel subHead2 = new JLabel("*Additional Information:");
        subHead2.setBounds(x1, y, 220, h);
        subHead2.setFont(new Font("Arial", Font.ITALIC, 12));
        add(subHead2);

        y += gapY;

        JLabel lblPrev = new JLabel("How many days of related leave has he/she previously enjoyed?");
        lblPrev.setBounds(x1, y, 520, h);
        lblPrev.setFont(new Font("Arial", Font.BOLD, 13));
        add(lblPrev);

        textPrevEnjoyed = new JTextField();
        textPrevEnjoyed.setBounds(x1 + 400, y, 70, h);
        textPrevEnjoyed.setFont(new Font("Arial", Font.BOLD, 12));
        textPrevEnjoyed.setEditable(false);
        add(textPrevEnjoyed);

        y += gapY;

        JLabel lblRemain = new JLabel("How many days of leave does he/she have?");
        lblRemain.setBounds(x1, y, 380, h);
        lblRemain.setFont(new Font("Arial", Font.BOLD, 13));
        add(lblRemain);

        textRemaining = new JTextField();
        textRemaining.setBounds(x1 + 400, y, 70, h);
        textRemaining.setFont(new Font("Arial", Font.BOLD, 12));
        textRemaining.setEditable(false);
        add(textRemaining);

        //Disable/mark N/A for non-Casual initially? No, default is Casual
        comboLeaveType.addActionListener(ae -> onLeaveTypeChanged());

        y += gapY;

        JLabel lblApproval = new JLabel("Chairman Approval:");
        lblApproval.setBounds(x1, y, 180, h);
        lblApproval.setFont(new Font("Arial", Font.BOLD, 13));
        add(lblApproval);

        comboApproval = new JComboBox<>(new String[]{"Approved", "Rejected"});
        comboApproval.setBounds(x1 + 170, y, 150, h);
        add(comboApproval);

        submit = new JButton("Submit");
        submit.setBounds(290, y + 55, 120, 30);
        submit.setBackground(new Color(52, 40, 186));
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        add(submit);

        print = new JButton("Print");
        print.setBounds(430, y + 55, 120, 30);
        print.setBackground(new Color(52, 40, 186));
        print.setForeground(Color.WHITE);
        print.addActionListener(this);
        add(print);

        cancel = new JButton("Cancel");
        cancel.setBounds(570, y + 55, 120, 30);
        cancel.setBackground(new Color(52, 40, 186));
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        add(cancel);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/Leave background.png"));
        Image i2 = i1.getImage().getScaledInstance(1000, 800, Image.SCALE_SMOOTH);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel imageLabel = new JLabel(i3);
        imageLabel.setBounds(0, 0, 1000, 800);
        add(imageLabel);

        setLayout(null);
        setVisible(true);
    }

    //Utility: recalc days when date changes
    private void onDatesChanged() {
        calculateLeaveDays();
        updateCasualCounters();
    }

    private void onLeaveTypeChanged() {
        String type = (String) comboLeaveType.getSelectedItem();
        if (!"Casual".equalsIgnoreCase(type)) {
            textPrevEnjoyed.setText("N/A");
            textRemaining.setText("N/A");
            textPrevEnjoyed.setEditable(false);
            textRemaining.setEditable(false);
        } else {
            textPrevEnjoyed.setEditable(false);
            textRemaining.setEditable(false);
            updateCasualCounters();
        }
    }

    private void calculateLeaveDays() {
        try {
            Date start = LeaveSD.getDate();
            Date end = LeaveED.getDate();
            if (start != null && end != null && !end.before(start)) {
                long diff = end.getTime() - start.getTime();
                long days = (diff / (1000 * 60 * 60 * 24)) + 1;
                textTotalLeave.setText(String.valueOf(days));
            } else {
                textTotalLeave.setText("");
            }
        } catch (Exception e) {
            textTotalLeave.setText("");
        }
    }

    //For Casual: compute previously enjoyed (same year) and remaining (quota - previous - current)
    private void updateCasualCounters() {
        String type = (String) comboLeaveType.getSelectedItem();
        if (!"Casual".equalsIgnoreCase(type)) return;

        Date start = LeaveSD.getDate();
        if (start == null) {
            textPrevEnjoyed.setText("");
            textRemaining.setText("");
            return;
        }

        int year = getYear(start);
        String tid = tIdField.getText().trim();
        if (tid.isEmpty()) {
            textPrevEnjoyed.setText("");
            textRemaining.setText("");
            return;
        }

        int alreadyEnjoyed = 0;
        try {
            Conn c = new Conn();
            String q = "SELECT Leave_Start, Total_Leave_Days FROM teacherLeave WHERE TID = ? AND Leave_Type = 'Casual' AND Chairman_Approval = 'Approved'";
            PreparedStatement ps = c.connection.prepareStatement(q);
            ps.setString(1, tid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Date prevStartDate = rs.getDate("Leave_Start");
                if (prevStartDate != null) {
                    int prevYear = getYear(prevStartDate);
                    if (prevYear == year) {
                        int leaveDays = rs.getInt("Total_Leave_Days");
                        alreadyEnjoyed += leaveDays;
                    }
                }
            }
            c.connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        int req = 0;
        try { req = Integer.parseInt(textTotalLeave.getText().trim()); } catch (Exception ignore) {}

        textPrevEnjoyed.setText(String.valueOf(alreadyEnjoyed));
        textRemaining.setText(String.valueOf(Math.max(0, CASUAL_YEARLY_QUOTA - (alreadyEnjoyed + req))));
    }

    private int getYear(Date d) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        return cal.get(Calendar.YEAR);
    }

    //Validations
    private boolean validateForm() {
        String tID = tIdField.getText().trim();
        String name = textName.getText().trim();
        String nid = textNid.getText().trim();
        String deg = textDeg.getText().trim();
        String type = (String) comboLeaveType.getSelectedItem();
        String total = textTotalLeave.getText().trim();
        String reason = textLeaveReason.getText().trim();
        String phone = textPhone.getText().trim();
        String email = textEmail.getText().trim();
        String perm = (String) comboPermissionReq.getSelectedItem();
        String permReason = textPermissionReason.getText().trim();

        //Date validation
        Date startDate = LeaveSD.getDate();
        Date endDate = LeaveED.getDate();

        if (tID.isEmpty() || name.isEmpty() || nid.isEmpty() || deg.isEmpty() ||
                type.isEmpty() || startDate == null || endDate == null || total.isEmpty() ||
                reason.isEmpty() || phone.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled!", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (!name.matches("[a-zA-Z.\\s]+")) {
            JOptionPane.showMessageDialog(this, "Invalid Name!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!nid.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "NID must contain digits only.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!total.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Total Leave Days must be a number.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        //Date sanity: start <= end
        if (endDate.before(startDate)) {
            JOptionPane.showMessageDialog(this, "Invalid date range! End date must be on/after Start date.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!phone.matches("\\d{11}")) {
            JOptionPane.showMessageDialog(this, "Invalid Phone! Must be 11 digits.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            JOptionPane.showMessageDialog(this, "Invalid Email format!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if ("Yes".equals(perm) && permReason.isBlank()) {
            JOptionPane.showMessageDialog(this, "Please mention the reason for permission.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        //For Casual: ensure quota not exceeded (optional hard-check)
        if ("Casual".equalsIgnoreCase(type)) {
            int prev = 0;
            try { prev = Integer.parseInt(textPrevEnjoyed.getText().trim()); } catch (Exception ignored) {}
            int req = Integer.parseInt(total);
            if (prev + req > CASUAL_YEARLY_QUOTA) {
                JOptionPane.showMessageDialog(this,
                        "Casual leave quota exceeded! (" + (prev + req) + " > " + CASUAL_YEARLY_QUOTA + ")",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }

        return true;
    }

    //Search & Fill
    private void searchByTID() {
        String tID = tIdField.getText().trim();
        if (tID.isEmpty()) return;

        try {
            Conn c = new Conn();
            String query = "SELECT Name, Designation, NID FROM teacher WHERE Teacher_ID = ?";
            PreparedStatement ps = c.connection.prepareStatement(query);
            ps.setString(1, tID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                textName.setText(rs.getString("Name"));
                textDeg.setText(rs.getString("Designation"));
                textNid.setText(rs.getString("NID"));
                updateCasualCounters();
            } else {
                JOptionPane.showMessageDialog(this, "No record found!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            c.connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void searchByName() {
        String tName = tNameSearchField.getText().trim();
        if (tName.isEmpty()) return;

        try {
            Conn c = new Conn();
            String q = "SELECT Name, Designation, NID, Teacher_ID FROM teacher WHERE Name LIKE ? LIMIT 1";
            PreparedStatement ps = c.connection.prepareStatement(q);
            ps.setString(1, "%" + tName + "%");
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // Fill both ways
                textName.setText(rs.getString("Name"));
                textDeg.setText(rs.getString("Designation"));
                textNid.setText(rs.getString("NID"));
                tIdField.setText(rs.getString("Teacher_ID"));
                updateCasualCounters();
            } else {
                JOptionPane.showMessageDialog(this, "No record found!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            c.connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //Print
    private void printLeaveApplication() {
        String name = textName.getText();
        String tid = tIdField.getText();
        String designation = textDeg.getText();
        String nid = textNid.getText();
        String type = (String) comboLeaveType.getSelectedItem();
        String startDate = LeaveSD.getDate() != null ? sdf.format(LeaveSD.getDate()) : "";
        String endDate = LeaveED.getDate() != null ? sdf.format(LeaveED.getDate()) : "";
        String totalDays = textTotalLeave.getText();
        String reason = textLeaveReason.getText();
        String permission = (String) comboPermissionReq.getSelectedItem();
        String permissionReason = textPermissionReason.getText();
        String phone = textPhone.getText();
        String email = textEmail.getText();
        String prev = textPrevEnjoyed.getText();
        String remain = textRemaining.getText();
        String status = (String) comboApproval.getSelectedItem();

        JTextPane textPane = new JTextPane();
        textPane.setContentType("text/html");

        String html = "<html><body style='font-family:Arial; padding:10px;'>"
                + "<div style='text-align:center;'>"
                + "<h1 style='font-size:16px; margin-bottom:4px;'>Leave Application Status</h1>"
                + "<h4 style='font-style:italic; color:gray; margin-top:0;'>Chairman Approval Copy</h4>"
                + "</div>"
                + "<div style='margin-top:20px; font-size:10px; text-align:left;'>"
                + "<p><b>Name:</b> " + name + "</p>"
                + "<p><b>Teacher ID:</b> " + tid + "</p>"
                + "<p><b>Designation:</b> " + designation + "</p>"
                + "<p><b>NID:</b> " + nid + "</p>"
                + "<p><b>Leave Type:</b> " + type + "</p>"
                + "<p><b>Leave Start Date:</b> " + startDate + "</p>"
                + "<p><b>Leave End Date:</b> " + endDate + "</p>"
                + "<p><b>Total Leave Days:</b> " + totalDays + "</p>"
                + "<p><b>Reason:</b> " + reason + "</p>"
                + "<p><b>Permission Required:</b> " + permission + (permission.equals("Yes") ? " — " + permissionReason : "") + "</p>"
                + "<p><b>Phone:</b> " + phone + "</p>"
                + "<p><b>Email:</b> " + email + "</p>"
                + ("Casual".equalsIgnoreCase(type) ? ("<p><b>Previously Enjoyed (Casual, this year):</b> " + prev + "</p>"
                + "<p><b>Remaining (Casual, this year):</b> " + remain + "</p>") : "")
                + "<br><p style='font-size:11px; font-style:italic;'><b>Application Status:</b> " + status + "</p>"
                + "</div></body></html>";

        textPane.setText(html);
        textPane.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textPane);
        scrollPane.setPreferredSize(new Dimension(650, 700));

        int option = JOptionPane.showConfirmDialog(this, scrollPane, "📄 Leave Application Preview", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (option == JOptionPane.OK_OPTION) {
            try {
                textPane.print();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    //Submit
    private void handleSubmit() {
        if (!validateForm()) return;

        String tID = tIdField.getText().trim();
        String name = textName.getText().trim();
        String designation = textDeg.getText().trim();
        String nid = textNid.getText().trim();
        String type = (String) comboLeaveType.getSelectedItem();
        Date startDate = LeaveSD.getDate();
        Date endDate = LeaveED.getDate();
        int totalLeave = Integer.parseInt(textTotalLeave.getText().trim());
        String reason = textLeaveReason.getText().trim();
        String permReq = (String) comboPermissionReq.getSelectedItem();
        String permReason = textPermissionReason.getText().trim();
        String phone = textPhone.getText().trim();
        String email = textEmail.getText().trim();
        String prev = "Casual".equalsIgnoreCase(type) ? textPrevEnjoyed.getText().trim() : "N/A";
        String remaining = "Casual".equalsIgnoreCase(type) ? textRemaining.getText().trim() : "N/A";
        String approval = (String) comboApproval.getSelectedItem();

        try {
            Conn c = new Conn();
            String q = "INSERT INTO teacherLeave "
                    + "(TID, Name, Designation, Teacher_NID, Leave_Type, Leave_Start, Leave_End, Total_Leave_Days, Reason, "
                    + "Permission_Required, Permission_Reason, Phone, Email, Previously_Enjoyed, Remaining_Leave, Chairman_Approval) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement ps = c.connection.prepareStatement(q);
            ps.setString(1, tID);
            ps.setString(2, name);
            ps.setString(3, designation);
            ps.setString(4, nid);
            ps.setString(5, type);
            // Convert Date to SQL Date
            ps.setDate(6, new java.sql.Date(startDate.getTime()));
            ps.setDate(7, new java.sql.Date(endDate.getTime()));
            ps.setInt(8, totalLeave);
            ps.setString(9, reason);
            ps.setString(10, permReq);
            ps.setString(11, permReq.equals("Yes") ? permReason : "");
            ps.setString(12, phone);
            ps.setString(13, email);
            ps.setString(14, prev);
            ps.setString(15, remaining);
            ps.setString(16, approval);

            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Information submitted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            c.connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //Actions
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchTid) {
            searchByTID();
        } else if (e.getSource() == searchByNameBtn) {
            searchByName();
        } else if (e.getSource() == submit) {
            handleSubmit();
        } else if (e.getSource() == print) {
            printLeaveApplication();
        } else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new TeacherLeave();
    }

    //Tiny helper for doc changes
    static class SimpleDocListener implements DocumentListener {
        private final Runnable onChange;
        SimpleDocListener(Runnable onChange) { this.onChange = onChange; }
        public void insertUpdate(DocumentEvent e) { onChange.run(); }
        public void removeUpdate(DocumentEvent e) { onChange.run(); }
        public void changedUpdate(DocumentEvent e) { onChange.run(); }
    }
}