package backend;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FilmPlatform {
    private List<Administrator> administrators;
    private List<Subscriber> subscribers;

    private FilmDatabase filmDatabase;

    public FilmPlatform() {
        // check if state is loaded
        FilmPlatform loadedPlatform = loadStateFromFile();

        if (loadedPlatform != null) {
            this.administrators = loadedPlatform.administrators;
            this.subscribers = loadedPlatform.subscribers;
            this.filmDatabase = loadedPlatform.filmDatabase;
        } else {
            // if not loaded, create a new instance
            this.administrators = new ArrayList<>();
            this.subscribers = new ArrayList<>();
            this.filmDatabase = new FilmDatabase(new ArrayList<>(), "movies.ser", this);
            //initialization logic
            filmDatabase.loadSubscribersFromFile();
            filmDatabase.loadMoviesFromFile();
            saveDataOnExit();
        }
    }

    public User login(String username, String password) {
        for (Administrator admin : administrators) {
            if (admin.getUsername().equals(username) && admin.authenticate(password)) {
                return admin;
            }
        }

        for (Subscriber subscriber : subscribers) {
            if (subscriber.getUsername().equals(username) && subscriber.authenticate(password)) {
                return subscriber;
            }
        }

        return null; //failed
    }

    public boolean register(User user) {
        if (getUserByUsername(user.getUsername()) == null) {
            if (user instanceof Administrator) {
                administrators.add((Administrator) user);
            } else if (user instanceof Subscriber) {
                subscribers.add((Subscriber) user);
                subscribers.add((Subscriber) user);
            }
            return true;
        }
        return false;
    }

    private User getUserByUsername(String username) {
        for (Administrator admin : administrators) {
            if (admin.getUsername().equals(username)) {
                return admin;
            }
        }

        for (Subscriber subscriber : subscribers) {
            if (subscriber.getUsername().equals(username)) {
                return subscriber;
            }
        }

        return null;
    }

    public void saveDataOnExit() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            saveData();
            filmDatabase.saveSubscribersToFile();
        }));
    }

    public static FilmPlatform loadStateFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("filmPlatform.ser"))) {
            return (FilmPlatform) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }

    public void saveStateToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("filmPlatform.ser"))) {
            oos.writeObject(this);
        } catch (IOException e) {
        }
    }

    public List<Subscriber> getAllSubscribers() {
        return subscribers;
    }

    public void initialize() {
        filmDatabase.loadMoviesFromFile();
    }

    public void saveData() {
        filmDatabase.saveMoviesToFile();
    }

    public FilmDatabase getFilmDatabase() {
        return this.filmDatabase;
    }
}
