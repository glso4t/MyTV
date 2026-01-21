package movies;

import java.util.ArrayList;
import java.util.List;

public class Search {
	
	Movie_Listing movies = new Movie_Listing();
	Serie series = new Serie();
	private List<Movie_Listing> moviess = movies.getRelatedMovies();
	private ArrayList<Serie> seriess = series.getRelatedSeries();
	
	public Search() {
		
	}
	
	public String findMovieOrSerie(String title, String type,
			String name, boolean valid, String category, int review_num) {
		
		for (Movie_Listing element : moviess) {
			if(title.equals(element.title) && type.equals("Movie") && 
					name.equals(element.names) && category.equals(element.category) &&
					review_num >= 4) {
				return ("Title: " + title  + " type: Movie " + " Name: " + name + " category: " + category.toString());
			}else {
				return "Movie not found";
			}
		}
		
		for (Serie element : seriess) {
			if(title.equals(element.getTitle()) && type.equals("Serie") && 
					name.equals(element.getMainActors()) && category.equals(element.getCategory()) &&
					review_num >= 4) {
				return ("Title: " + title  + " type: Movie " + " Name: " + name + " category: " + category.toString());
			}else {
				return "Movie not found";
			}
		}
		return "";
		
	}
}
