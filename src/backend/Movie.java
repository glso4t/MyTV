package backend;

import java.util.*;

public class Movie {
    private String title;
    private String description;
    private String genre;
    private boolean appropriateForMinors;
    private int yearOfFirstDisplay;
    private int duration;
    private String category;
    private String protagonists;
    private List<Movie> relatedMovies;
    private List<Rating> ratings;
    private int minOverallGrade;
    private int id;


    public Movie(String title, String description, boolean appropriateForMinors, int yearOfFirstDisplay,
                 int duration, String category, String protagonists, List<Movie> relatedMovies)  {
        this.title = title;
        this.description = description;
        this.genre = genre;
        this.appropriateForMinors = appropriateForMinors;
        this.yearOfFirstDisplay = yearOfFirstDisplay;
        this.duration = duration;
        this.category = category;
        this.protagonists = protagonists;
        this.relatedMovies = relatedMovies;
        this.ratings = new ArrayList<>();
        this.minOverallGrade = minOverallGrade;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {

        return genre;
    }

    public void setGenre(String genre) {

        this.genre = genre;
    }

    public boolean isAppropriateForMinors() {
        return appropriateForMinors;
    }

    public void setAppropriateForMinors(boolean appropriateForMinors) {
        this.appropriateForMinors = appropriateForMinors;
    }

    public int getYearOfFirstDisplay() {
        return yearOfFirstDisplay;
    }

    public void setYearOfFirstDisplay(int yearOfFirstDisplay) {
        this.yearOfFirstDisplay = yearOfFirstDisplay;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProtagonists() {
        return protagonists;
    }

    public void setProtagonists(String protagonists) {
        this.protagonists = protagonists;
    }

    public List<Movie> getRelatedMovies() {
        return relatedMovies;
    }

    public void setRelatedMovies(List<Movie> relatedMovies) {
        this.relatedMovies = relatedMovies;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public void addRating(Rating rating) {
        ratings.add(rating);
    }

    public int getMinOverallGrade() {
        return minOverallGrade;
    }

    public void setMinOverallGrade(int minOverallGrade) {
        this.minOverallGrade = minOverallGrade;
    }
    public int getId() {
        return id;
    }


    public void displayDetails(User user, FilmPlatform filmPlatform) {
        System.out.println("backend.Movie Details:");
        System.out.println("Title: " + title);
        System.out.println("Description: " + description);
        System.out.println("Appropriate for Minors: " + appropriateForMinors);
        System.out.println("Year of First Display: " + yearOfFirstDisplay);
        System.out.println("Duration: " + duration + " minutes");
        System.out.println("Category: " + category);
        System.out.println("Protagonists: " + protagonists);
        System.out.println("Related Movies: " + relatedMovies);
        System.out.println("Number of Ratings: " + ratings.size());
        System.out.println("Average backend.Rating: " + calculateAverageRating());

        if (!ratings.isEmpty()) {
            System.out.println("All Ratings:");
            for (Rating rating : ratings) {
                System.out.println("Text: " + rating.getText());
                System.out.println("backend.Rating: " + rating.getRating());
                System.out.println("Date: " + rating.getDate());
                System.out.println("Username: " + rating.getUsername());
                System.out.println("------");
            }
        }

        if (user instanceof Administrator) {
            System.out.println("Options for backend.Administrator:");
            System.out.println("1. Edit backend.Movie");
            System.out.println("2. Delete backend.Movie");
        } else if (user instanceof Subscriber)  {
            System.out.println("Options for backend.Subscriber:");
            System.out.println("1. Enter backend.Rating");
            System.out.println("2. Add to Favorites");
            System.out.println("3. Remove from Favorites");

            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    ((Subscriber) user).rateMovie(this);
                    break;
                case 2:
                    ((Subscriber) user).addToFavorites(this);
                    System.out.println("Added to Favorites!");
                    break;
                case 3:
                    ((Subscriber) user).removeFromFavorites(this, filmPlatform.getFilmDatabase());
                    System.out.println("Removed from Favorites!");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private double calculateAverageRating() {
        if (ratings.isEmpty()) {
            return 0.0;
        }

        double sum = 0;
        for (Rating rating : ratings) {
            sum += rating.getRating();
        }

        return sum / ratings.size();
    }

    public void removeRating(Rating rating) {
        ratings.remove(rating);
    }

}






class Season {
    private int number;
    private int yearOfProjection;
    private List<Episode> episodeList;

    public Season(int number, int yearOfProjection, List<Episode> episodeList) {
        this.number = number;
        this.yearOfProjection = yearOfProjection;
        this.episodeList = episodeList;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getYearOfProjection() {
        return yearOfProjection;
    }

    public void setYearOfProjection(int yearOfProjection) {
        this.yearOfProjection = yearOfProjection;
    }

    public List<Episode> getEpisodeList() {
        return episodeList;
    }

    public void setEpisodeList(List<Episode> episodeList) {
        this.episodeList = episodeList;
    }
}

class Episode {
    private int duration;

    public Episode(int duration) {
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
