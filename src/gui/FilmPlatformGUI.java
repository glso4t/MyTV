package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FilmPlatformGUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Film Platform");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //login window
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // open login window
                openLoginWindow();
            }
        });

        //exit from application
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JPanel panel = new JPanel();
        panel.add(loginButton);
        panel.add(exitButton);

        frame.getContentPane().add(panel);

        //frame properties
        frame.setSize(300, 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void openLoginWindow() {

        JOptionPane.showMessageDialog(null, "Login window will be implemented here.");
    }
}
