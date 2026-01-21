package movies;

import java.util.ArrayList;
import java.util.List;

public class Movie_Listing {
	
	public String title;
	public String description;
	public boolean valid18;
	public int year_listing;
	public int movie_duration;
	public String category;
	public String names;
	public List<Movie_Listing> relatedMovies;
	public String review;
	
	public Movie_Listing(String title, String description,
			boolean valid, int year, int duration, String category,
			String names) {
		this.title = title;
		this.description = description;
		valid18 = valid;
		year_listing = year;
		movie_duration = duration;
		this.category = category;
		this.names = names;
		this.relatedMovies = new ArrayList<>();
	}
	
	public Movie_Listing() {
		
	}
	
	public void addRelatedMovie(Movie_Listing movie) {
		relatedMovies.add(movie);
	}

	
	public void setReview(String review) {
		this.review = review;
	}

	public String getTitle() {
		return title;
	}
	
	public String toString() {
		return getTitle();
	}

	public List<Movie_Listing> getRelatedMovies() {
	    if (relatedMovies == null) {
	        relatedMovies = new ArrayList<>();
	    }
	    return relatedMovies;
	}


}