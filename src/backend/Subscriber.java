package backend;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Date;
import java.io.Serializable;


public class Subscriber extends User implements Serializable{
    private List<Movie> favorites;
    private List<Rating> ratings;

    private FilmDatabase filmDatabase;

    public Subscriber(String username, String password, FilmDatabase filmDatabase) {
        super(username, password);
        this.filmDatabase = filmDatabase;
        this.ratings = new ArrayList<>();
        this.favorites = new ArrayList<>();
        filmDatabase.addSubscriber(this);
    }
    public void rateMovie(Movie movie) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Rate backend.Movie: " + movie.getTitle());

        System.out.print("Enter the text of the evaluation (up to 500 characters): ");
        String text = scanner.nextLine();

        System.out.print("Enter the rating (1 to 5): ");
        int ratingValue = scanner.nextInt();

        if (validateRatingInput(text, ratingValue)) {
            Rating rating = new Rating(text, ratingValue, new Date(), getUsername());
            ratings.add(rating);
            movie.addRating(rating);
            System.out.println("backend.Rating posted successfully!");
        } else {
            System.out.println("Invalid input. Please enter valid values for the rating.");
        }
    }

    private boolean validateRatingInput(String text, int rating) {
        return !text.isEmpty() && rating >= 1 && rating <= 5;
    }

    public void editRating(Movie movie) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Edit backend.Rating for backend.Movie: " + movie.getTitle());

        Rating selectedRating = displayAndSelectRating(movie);

        if (selectedRating != null) {
            System.out.print("Enter the new text of the evaluation (up to 500 characters): ");
            String newText = scanner.nextLine();

            System.out.print("Enter the new rating (1 to 5): ");
            int newRatingValue = scanner.nextInt();

            if (validateRatingInput(newText, newRatingValue)) {
                // update
                selectedRating.setText(newText);
                selectedRating.setRating(newRatingValue);

                System.out.println("backend.Rating updated successfully!");
            } else {
                System.out.println("Invalid input. Please enter valid values for the rating.");
            }
        } else {
            System.out.println("backend.Rating not found.");
        }
    }

    private Rating displayAndSelectRating(Movie movie) {
        System.out.println("Select a backend.Rating to Edit:");

        List<Rating> movieRatings = movie.getRatings();

        for (int i = 0; i < movieRatings.size(); i++) {
            Rating rating = movieRatings.get(i);
            System.out.println(i + 1 + ". Text: " + rating.getText() + " | backend.Rating: " + rating.getRating());
        }

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        if (choice >= 1 && choice <= movieRatings.size()) {
            return movieRatings.get(choice - 1);
        } else {
            return null;
        }
    }

    public void deleteRating(Movie movie) {
        Rating selectedRating = displayAndSelectRating(movie);

        if (selectedRating != null) {
            movie.removeRating(selectedRating);
            ratings.remove(selectedRating);

            System.out.println("backend.Rating deleted successfully!");
        } else {
            System.out.println("backend.Rating not found.");
        }
    }
    public List<Movie> searchMovies(String keyword) {
        List<Movie> searchResults = new ArrayList<>();

        for (Movie movie : filmDatabase.getMovies()) {
            if (movie.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                searchResults.add(movie);
            }
        }

        return searchResults;
    }


    public void addToFavorites(Movie movie) {
        favorites.add(movie);
        System.out.println("backend.Movie added to favorites successfully!");
    }
    public void removeFromFavorites(Movie movie, FilmDatabase filmDatabase) {
        favorites.remove(movie);
        System.out.println("backend.Movie removed from favorites successfully!");

        for (Subscriber subscriber : filmDatabase.getAllSubscribers()) {
            if (subscriber != this) {
                subscriber.removeFromFavorites(movie, filmDatabase);
            }
        }
    }

    public void viewFavorites() {
        System.out.println("Favorites:");
        for (Movie movie : favorites) {
            System.out.println("Title: " + movie.getTitle() + " | Type: " + getType(movie));
        }
    }

    private String getType(Movie movie) {
        if (movie instanceof Series) {
            return "backend.Series";
        } else {
            return "backend.Movie";
        }
    }
    public List<Movie> getFavorites() {
        return favorites;
    }

    public List<Rating> getRatings() {
        return ratings;
    }
}
