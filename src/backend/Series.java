package backend;

import java.util.List;

public class Series extends Movie {
    private List<Season> seasonList;

    public Series(String title, String description, boolean appropriateForMinors, int yearOfFirstDisplay, int duration,
                  String category, String protagonists, List<Movie> relatedMovies, List<Season> seasonList) {
        super(title, description, appropriateForMinors, yearOfFirstDisplay, duration, category, protagonists, relatedMovies);
        this.seasonList = seasonList;
    }

    public List<Season> getSeasonList() {
        return seasonList;
    }

    public void setSeasonList(List<Season> seasonList) {
        this.seasonList = seasonList;
    }

    @Override
    public void displayDetails(User user, FilmPlatform filmPlatform) {
        System.out.println("backend.Series Details:");
        super.displayDetails(user, filmPlatform);

        for (Season season : seasonList) {
            System.out.println("backend.Season " + season.getNumber() + " (" + season.getYearOfProjection() + ")");
            for (Episode episode : season.getEpisodeList()) {
                System.out.println("  backend.Episode Duration: " + episode.getDuration() + " minutes");
            }
        }
    }
}