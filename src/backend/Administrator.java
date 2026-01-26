package backend;

import java.util.Scanner;

public class Administrator extends User {
    private FilmDatabase filmDatabase;

    //constructor
    public Administrator(String username, String password, FilmDatabase filmDatabase) {
        super(username, password);
        this.filmDatabase = filmDatabase;
    }

    public void addMovie() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter backend.Movie Details:");

        String title = scanner.nextLine();

        String description = scanner.nextLine();

        boolean suitability = scanner.nextBoolean();
        scanner.nextLine();

        int yearOfFirstDisplay = scanner.nextInt();
        scanner.nextLine();

        int duration = scanner.nextInt();
        scanner.nextLine();

        String category = scanner.nextLine();

        String protagonists = scanner.nextLine();

        Movie newMovie = new Movie(title, description, suitability, yearOfFirstDisplay, duration, category, protagonists, null);

        filmDatabase.addMovie(newMovie);
        filmDatabase.saveMoviesToFile();
        System.out.println("backend.Movie added successfully!");
    }

    public void deleteMovie(Movie movie) {
        filmDatabase.getMovies().remove(movie);
        filmDatabase.saveMoviesToFile();
        System.out.println("backend.Movie deleted successfully!");

        for (Subscriber subscriber : filmDatabase.getAllSubscribers()) {
            subscriber.removeFromFavorites(movie, filmDatabase);
        }
    }

    public void editMovie() {
        Scanner scanner = new Scanner(System.in);

        String movieTitle = scanner.nextLine();

        Movie movieToEdit = filmDatabase.getMovieByTitle(movieTitle);

        if (movieToEdit != null) {
            MovieEditor movieEditor = new MovieEditor();
            Movie editedMovie = movieEditor.editMovie(movieToEdit);

            filmDatabase.getMovies().remove(movieToEdit);
            filmDatabase.addMovie(editedMovie);
            filmDatabase.saveMoviesToFile();

            System.out.println("backend.Movie edited successfully!");
        } else {
            System.out.println("backend.Movie not found.");
        }
    }



}
