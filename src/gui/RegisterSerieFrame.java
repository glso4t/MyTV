package gui;

import backend.FilmPlatform;
import backend.Series;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterSerieFrame extends JFrame {

    private FilmPlatform filmPlatform;

    private JTextField textfield1;
    private JTextField textfield2;
    private JTextField textfield3;
    private JTextField textfield4;
    private JTextField textfield5;
    private JTextField textfield6;
    private JTextField textfield7;

    public RegisterSerieFrame(FilmPlatform filmPlatform) {
        this.filmPlatform = filmPlatform;

        setTitle("Register Series");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(8, 2));

        JLabel label1 = new JLabel("Title:");
        textfield1 = new JTextField();
        JLabel label2 = new JLabel("Description:");
        textfield2 = new JTextField();
        JLabel label3 = new JLabel("Valid for Minors (yes/no):");
        textfield3 = new JTextField();
        JLabel label4 = new JLabel("Year of First Display:");
        textfield4 = new JTextField();
        JLabel label5 = new JLabel("Duration (minutes):");
        textfield5 = new JTextField();
        JLabel label6 = new JLabel("Category:");
        textfield6 = new JTextField();
        JLabel label7 = new JLabel("Protagonists:");
        textfield7 = new JTextField();

        JButton registerButton = new JButton("Register Series");

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerSeries();
            }
        });

        add(label1);
        add(textfield1);
        add(label2);
        add(textfield2);
        add(label3);
        add(textfield3);
        add(label4);
        add(textfield4);
        add(label5);
        add(textfield5);
        add(label6);
        add(textfield6);
        add(label7);
        add(textfield7);
        add(registerButton);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void registerSeries() {
        // Get data from text fields
        String title = textfield1.getText().trim();
        String description = textfield2.getText().trim();
        boolean validForMinors = "yes".equalsIgnoreCase(textfield3.getText().trim());

        int yearOfFirstDisplay;
        try {
            yearOfFirstDisplay = Integer.parseInt(textfield4.getText().trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Enter a valid year of first display.");
            return;
        }

        int duration;
        try {
            duration = Integer.parseInt(textfield5.getText().trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Enter a valid duration for the series.");
            return;
        }

        String category = textfield6.getText().trim();
        String protagonists = textfield7.getText().trim();

        Series series = new Series(title, description, validForMinors, yearOfFirstDisplay, duration, category, protagonists, null, null);
        filmPlatform.getFilmDatabase().addSeries(series);

        JOptionPane.showMessageDialog(null, "Series registered successfully!");
    }
}
