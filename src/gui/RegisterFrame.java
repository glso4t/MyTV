package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import backend.FilmPlatform;
import backend.User;
import backend.Subscriber;

public class RegisterFrame {
    private JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;

    private FilmPlatform filmPlatform;

    public RegisterFrame(FilmPlatform filmPlatform) {
        this.filmPlatform = filmPlatform;

        frame = new JFrame("Register");
        frame.setSize(300, 150);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // dispose the window on close, doesn't exit
        frame.setLayout(new GridLayout(4, 2));

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();

        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                char[] password = passwordField.getPassword();

                // Validate input
                if (username.isEmpty() || password.length == 0) {
                    JOptionPane.showMessageDialog(frame, "Please enter both username and password.");
                    return;
                }

                // Registration logic
                registerUser(username, new String(password));
            }
        });

        frame.add(usernameLabel);
        frame.add(usernameField);
        frame.add(passwordLabel);
        frame.add(passwordField);
        frame.add(registerButton);

        frame.setVisible(true);
    }

    private void registerUser(String username, String password) {
        User newUser = new Subscriber(username, password, filmPlatform.getFilmDatabase());

        boolean registrationSuccess = filmPlatform.register(newUser);

        if (registrationSuccess) {
            JOptionPane.showMessageDialog(frame, "Registration successful!");
            frame.dispose();
        } else {
            JOptionPane.showMessageDialog(frame, "Username already exists. Please choose another.");
        }
    }

    public JFrame getFrame() {
        return frame;
    }
}

class WrongConfirmPasswordFrame extends JFrame {
    private JPanel panel;
    private JLabel label;
    private JButton okButton;

    public WrongConfirmPasswordFrame() {
        panel = new JPanel();
        label = new JLabel("Fields Password and Confirm Password don't match");
        okButton = new JButton("ok");

        okButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        panel.add(label);
        panel.add(okButton);

        this.setContentPane(panel);

        this.setVisible(true);
        this.setSize(350, 100);
        this.setTitle("Wrong Confirm Password Screen");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

}

class PasswordTooShortFrame extends JFrame {
    private JPanel panel;
    private JLabel firstLabel, secondLabel;
    private JButton okButton;

    public PasswordTooShortFrame() {
        panel = new JPanel();
        firstLabel = new JLabel("Password Too Short");
        secondLabel = new JLabel("Please enter a password at least 8 characters long");
        okButton = new JButton("ok");

        okButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        panel.add(firstLabel);
        panel.add(secondLabel);
        panel.add(okButton);

        this.setContentPane(panel);

        this.setVisible(true);
        this.setSize(350, 150);
        this.setTitle("Too Short Password Screen");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

}

class PersonalDataAlreadyExistFrame extends JFrame {
    private JPanel panel;
    private JLabel label;
    private JButton okButton;

    public PersonalDataAlreadyExistFrame() {
        panel = new JPanel();
        label = new JLabel("This Username/Email is already taken");
        okButton = new JButton("ok");

        okButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        panel.add(label);
        panel.add(okButton);

        this.setContentPane(panel);

        this.setVisible(true);
        this.setSize(300, 100);
        this.setTitle("Personal Data Already Exist Screen");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

}

class AcceptTermsFrame extends JFrame {
    private JPanel panel;
    private JLabel label;
    private JButton okButton;

    public AcceptTermsFrame() {
        panel = new JPanel();
        label = new JLabel("You need to accept the Terms of Use & Privacy Policy to register");
        okButton = new JButton("ok");

        okButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        panel.add(label);
        panel.add(okButton);

        this.setContentPane(panel);

        this.setVisible(true);
        this.setSize(400, 100);
        this.setTitle("Accept Terms Screen");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}