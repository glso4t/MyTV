package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import backend.FilmPlatform;
import backend.User;

public class LoginWindow {
    private JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;

    private FilmPlatform filmPlatform;

    public LoginWindow(FilmPlatform filmPlatform) {
        this.filmPlatform = filmPlatform;

        frame = new JFrame("Login");
        frame.setSize(300, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(3, 2));

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                char[] password = passwordField.getPassword();

                if (username.isEmpty() || password.length == 0) {
                    JOptionPane.showMessageDialog(frame, "Please enter both username and password.");
                    return;
                }

                User loggedInUser = filmPlatform.login(username, new String(password));

                if (loggedInUser != null) {
                    JOptionPane.showMessageDialog(frame, "Login successful!");

                    new MainApplicationWindow(filmPlatform, loggedInUser);

                    frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid username or password. Please try again.");
                }
            }
        });

        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //registration window
                RegisterFrame registrationWindow = new RegisterFrame(filmPlatform);
            }
        });


        frame.add(usernameLabel);
        frame.add(usernameField);
        frame.add(passwordLabel);
        frame.add(passwordField);
        frame.add(loginButton);
        frame.add(registerButton);

        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }


}
