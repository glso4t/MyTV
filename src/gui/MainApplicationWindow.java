package gui;

import backend.FilmPlatform;
import backend.Movie;
import backend.FilmSearch;
import backend.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class MainApplicationWindow {

    private JFrame frame;
    private FilmPlatform filmPlatform;
    private User loggedInUser;

    public MainApplicationWindow(FilmPlatform filmPlatform, User loggedInUser) {
        this.filmPlatform = filmPlatform;
        this.loggedInUser = loggedInUser;

        frame = new JFrame("Film Platform - Main Window");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JButton viewMoviesButton = new JButton("View Movies");
        JButton searchButton = new JButton("Search Movies/Series");
        JButton registerMovieButton = new JButton("Register Movie");
        JButton registerSerieButton = new JButton("Register Series");

        viewMoviesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayMovieList();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openSearchWindow();
            }
        });

        registerMovieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterMovieFrame registerMovieFrame = new RegisterMovieFrame(filmPlatform);
            }
        });

        registerSerieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openRegisterSerieWindow();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(viewMoviesButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(registerMovieButton);
        buttonPanel.add(registerSerieButton);

        frame.add(buttonPanel, BorderLayout.CENTER);

        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                saveDataOnExit();
            }
        });
    }

    private void displayMovieList() {
        List<Movie> movies = filmPlatform.getFilmDatabase().getMovies();

        StringBuilder movieListText = new StringBuilder("Movie List:\n");
        for (Movie movie : movies) {
            movieListText.append("- ").append(movie.getTitle()).append("\n");
        }

        JOptionPane.showMessageDialog(frame, movieListText.toString(), "Movies", JOptionPane.INFORMATION_MESSAGE);
    }

    private void openSearchWindow() {
        JFrame searchFrame = new JFrame("Search Movies");
        searchFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel titleLabel = new JLabel("Title:");
        JTextField titleField = new JTextField();
        JLabel typeLabel = new JLabel("Type (Movie/Series):");
        JTextField typeField = new JTextField();
        JLabel nameLabel = new JLabel("Protagonist Name:");
        JTextField nameField = new JTextField();
        JLabel categoryLabel = new JLabel("Category:");
        JTextField categoryField = new JTextField();
        JLabel reviewNumLabel = new JLabel("Minimum Review Rating (1-5):");
        JTextField reviewNumField = new JTextField();

        JButton searchButton = new JButton("Search");
        JTextArea resultArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(resultArea);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();
                String type = typeField.getText();
                String name = nameField.getText();
                String category = categoryField.getText();

                int reviewNum = 0;
                try {
                    reviewNum = Integer.parseInt(reviewNumField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(searchFrame, "Please enter a valid number for review rating.");
                    return;
                }

                String searchResult = FilmSearch.findMovieOrSeries(title, type, name, true, category, reviewNum, filmPlatform.getFilmDatabase());
                resultArea.setText(searchResult);
            }
        });

        JPanel searchPanel = new JPanel(new GridLayout(7, 2));
        searchPanel.add(titleLabel);
        searchPanel.add(titleField);
        searchPanel.add(typeLabel);
        searchPanel.add(typeField);
        searchPanel.add(nameLabel);
        searchPanel.add(nameField);
        searchPanel.add(categoryLabel);
        searchPanel.add(categoryField);
        searchPanel.add(reviewNumLabel);
        searchPanel.add(reviewNumField);
        searchPanel.add(searchButton);

        searchFrame.setLayout(new BorderLayout());
        searchFrame.add(searchPanel, BorderLayout.NORTH);
        searchFrame.add(scrollPane, BorderLayout.CENTER);

        searchFrame.setSize(500, 300);
        searchFrame.setLocationRelativeTo(frame);
        searchFrame.setVisible(true);
    }
    private void openRegisterSerieWindow() {
        RegisterSerieFrame registerSerieFrame = new RegisterSerieFrame(filmPlatform);
    }

    public void saveDataOnExit() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (filmPlatform != null && filmPlatform.getFilmDatabase() != null) {
                filmPlatform.getFilmDatabase().saveSubscribersToFile();
            }
        }));
    }

}
