package backend;

import java.util.List;

public class FilmSearch {

    public static String findMovieOrSeries(String title, String type, String name, boolean valid, String category, int reviewNum, FilmDatabase filmDatabase) {
        StringBuilder searchResult = new StringBuilder();

        for (Movie movie : filmDatabase.getMovies()) {
            boolean match = true;

            if (isNotBlank(title) && !containsIgnoreCase(movie.getTitle(), title)) {
                match = false;
            }
            if (isNotBlank(type) && !containsIgnoreCase(getMovieType(movie), type)) {
                match = false;
            }
            if (isNotBlank(name) && !containsIgnoreCase(movie.getProtagonists(), name)) {
                match = false;
            }
            if (isNotBlank(category) && !containsIgnoreCase(movie.getCategory(), category)) {
                match = false;
            }
            if (reviewNum > 0 && calculateAverageRating(movie) < reviewNum) {
                match = false;
            }

            if (match) {
                searchResult.append("Title: ").append(movie.getTitle()).append(" Type: ").append(getMovieType(movie)).append(" Name: ").append(movie.getProtagonists()).append(" Category: ").append(movie.getCategory()).append("\n");
            }
        }

        return searchResult.length() > 0 ? searchResult.toString() : "Movie or Series not found";
    }

    private static String getMovieType(Movie movie) {
        return movie instanceof Series ? "Series" : "Movie";
    }

    private static double calculateAverageRating(Movie movie) {
        List<Rating> ratings = movie.getRatings();
        if (ratings.isEmpty()) {
            return 0.0;
        }

        double sum = 0;
        for (Rating rating : ratings) {
            sum += rating.getRating();
        }

        return sum / ratings.size();
    }

    private static boolean isNotBlank(String str) {
        return str != null && !str.trim().isEmpty();
    }

    private static boolean containsIgnoreCase(String source, String target) {
        return source != null && target != null && source.toLowerCase().contains(target.toLowerCase());
    }
}
