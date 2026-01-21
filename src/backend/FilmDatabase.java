package backend;

import backend.Administrator;
import backend.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FilmDatabase {
    private List<Movie> movies;
    private String filePath;
    private FilmPlatform filmPlatform;
    private List<Subscriber> subscribers;
    private List<Administrator>administrators;

    //constructor
    public FilmDatabase(List<Movie> movies, String filePath, FilmPlatform filmPlatform) {
        this.movies = movies;
        this.filePath = filePath;
        this.filmPlatform = filmPlatform;
        this.subscribers = new ArrayList<>();
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public Movie getMovieByTitle(String title) {
        for (Movie movie : movies) {
            if (movie.getTitle().equals(title)) {
                return movie;
            }
        }
        return null;
    }

    public Movie getMovieById(int movieId) {
        for (Movie movie : movies) {
            if (movie.getId() == movieId) {
                return movie;
            }
        }
        return null;
    }

    public void displayMovies() {
        List<Movie> movies = getMovies();
        if (movies.isEmpty()) {
            System.out.println("No movies available.");
        } else {
            System.out.println("Movies:");
            for (Movie movie : movies) {
                System.out.println(movie);
            }
        }
    }

    public void addSubscriber(Subscriber subscriber) {
        subscribers.add(subscriber);
    }
    public void addAdministrator(Administrator administrator) {
        administrators.add(administrator);
    }

    public List<Subscriber> getAllSubscribers() {
        List<Subscriber> allSubscribers = new ArrayList<>();
        for (User user : filmPlatform.getAllSubscribers()) {
            if (user instanceof Subscriber) {
                allSubscribers.add((Subscriber) user);
            }
        }
        return allSubscribers;
    }

// αποθηκεύει τους συνδρομητές σε ένα αρχείο με το όνομα "subscribers.ser"
    public void saveSubscribersToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("subscribers.ser"))) {
            oos.writeObject(subscribers);
            System.out.println("Subscribers saved to file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadSubscribersFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("subscribers.ser"))) {
            subscribers = new ArrayList<>();

            subscribers = (List<Subscriber>) ois.readObject();
            System.out.println("Subscribers loaded from file.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    public void addSeries(Series series) {
        movies.add(series);
    }

    public void saveMoviesToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(movies);
            System.out.println("Movies saved to file successfully!");
        } catch (IOException e) {
            System.out.println("Error saving movies to file: " + e.getMessage());
        }
    }

    public void loadMoviesFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            movies = (List<Movie>) ois.readObject();
            System.out.println("Movies loaded from file successfully!");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading movies from file: " + e.getMessage());
        }
    }
}
