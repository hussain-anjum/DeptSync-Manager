package deptSync.Manager;

import java.awt.*;
import javax.swing.*;

public class Splash {
    public static void main(String[] args) {
        new SplashScreen();
    }
}
class SplashScreen extends JFrame {
    JProgressBar progressBar;

    SplashScreen() {
        setTitle("DeptSync Manager");
        setLayout(new BorderLayout());
        setUndecorated(true);
        setSize(600, 400);
        setLocationRelativeTo(null);

        //loading image
        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/Splash.png"));
        Image scaledImage = imageIcon.getImage().getScaledInstance(600, 400, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
        add(imageLabel, BorderLayout.CENTER);

        //progress bar
        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        progressBar.setForeground(new Color(52, 40, 186)); // Hex #3428ba converted to RGB
        add(progressBar, BorderLayout.SOUTH);

        setVisible(true);
        loadProgress();
    }

    private void loadProgress() {
        try {
            for (int i = 0; i <= 100; i++) {
                Thread.sleep(50);
                progressBar.setValue(i);
            }
            dispose();
            new Login(); //Open login window
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
