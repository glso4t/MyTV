package movies;

import java.util.ArrayList;

class Serie {

    private String title;
    private String description;
    private boolean suitableForMinors;
    private String category;
    private String mainActors;
    private String sName;
    private int sNum;
    private int year;
    private ArrayList<Season> seasons;
    private ArrayList<Serie> relatedSeries;

    public Serie(String title, String description, boolean suitableForMinors, String category, String mainActors, String seasonName, int seasonNum, int year) {
        this.title = title;
        this.description = description;
        this.suitableForMinors = suitableForMinors;
        this.category = category;
        this.mainActors = mainActors;
        this.sName = seasonName;
        this.sNum = seasonNum;
        this.year = year;
        this.seasons = new ArrayList<>();
        this.relatedSeries = new ArrayList<>();
    }
    public Serie() {
    	
    }

    public void addSeason(Season season) {
        seasons.add(season);
    }

    public void addRelatedSeries(Serie relatedSeries) {
        this.relatedSeries.add(relatedSeries);
    }

	public ArrayList<Season> getSeasons() {
		return seasons;
	}

	public ArrayList<Serie> getRelatedSeries() {
		if(relatedSeries == null) {
			relatedSeries = new ArrayList<>();
		}
		return relatedSeries;
	}

	public String getTitle() {
		return title;
	}
	
	public String toString() {
		return getTitle();
	}

	public String getMainActors() {
		return mainActors;
	}

	public String getCategory() {
		return category;
	}
    
    


}
class Episode {
    private int episodeNumber;
    private int durationMinutes;

    public Episode(int episodeNumber, int durationMinutes) {
        this.episodeNumber = episodeNumber;
        this.durationMinutes = durationMinutes;
    }

}

class Season {

    private int seasonNumber;
    private int releaseYear;
    private ArrayList<Episode> episodes;

    public Season(int seasonNumber, int releaseYear) {
        this.seasonNumber = seasonNumber;
        this.releaseYear = releaseYear;
        this.episodes = new ArrayList<>();
    }

    public void addEpisode(Episode episode) {
        episodes.add(episode);
    }

}