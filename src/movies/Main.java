package movies;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		
		//Δημιουργια 2 ταινιών και 2 σειρών στο σύστημα 
		Movie_Listing movie1 = new Movie_Listing("Τίτλος1", "Περιγραφή1", true, 2023, 120, "Κατηγορία1", "Πρωταγωνιστές1");
	    Movie_Listing movie2 = new Movie_Listing("Τίτλος2", "Περιγραφή2", false, 2022, 90, "Κατηγορία2", "Πρωταγωνιστές2");

	    Serie serie1 = new Serie("Τίτλος Σειράς1", "Περιγραφή Σειράς1", true, "Κατηγορία Σειράς1", "Πρωταγωνιστές Σειράς1", "seasonName", 1, 2000);
	    Serie serie2 = new Serie("Τίτλος Σειράς2", "Περιγραφή Σειράς2", false, "Κατηγορία Σειράς2", "Πρωταγωνιστές Σειράς2", "seasonName", 2, 2010);

	    //Αρχικοποίηση 2 λιστών με ταινίες και σειρές ώστε να τα περάσω ως παράμετρο στο gui
	    List<Movie_Listing> movies = new ArrayList<>();
	    ArrayList<Serie> series = new ArrayList<>();
	    
	    
	    //Προσθηκη Ταινιών και σειρών στο συστημα
	    movies.add(movie1);
	    movies.add(movie2);

	    series.add(serie1);
	    series.add(serie2);
	    
	    movie1.addRelatedMovie(movie1);
	    movie2.addRelatedMovie(movie2);
	    
	    serie1.addRelatedSeries(serie1);
	    serie2.addRelatedSeries(serie2);
	    
	    

	    //Αρχικοποίηση της γραφικής διασύνδεσης
	    new GUI(movies, series);

	}

}
