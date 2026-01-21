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

        System.out.print("Title: ");
        String title = scanner.nextLine();

        System.out.print("Description: ");
        String description = scanner.nextLine();

        System.out.print("Suitability (true/false): ");
        boolean suitability = scanner.nextBoolean();
        scanner.nextLine();

        System.out.print("Year of First Display: ");
        int yearOfFirstDisplay = scanner.nextInt();
        scanner.nextLine();

        System.out.print("backend.Movie Duration (in minutes): ");
        int duration = scanner.nextInt();
        scanner.nextLine();

        System.out.print("backend.Movie Category (horror, drama, sci-fi, comedy, action): ");
        String category = scanner.nextLine();

        System.out.print("Protagonists: ");
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

        System.out.println("Enter the title of the movie you want to edit: ");
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
