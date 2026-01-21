package backend;

import backend.Movie;

import java.util.Scanner;

public class MovieEditor {
    public Movie editMovie(Movie movie) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Editing backend.Movie: " + movie.getTitle());
        System.out.println("Enter new details or press Enter to keep existing values.");

        System.out.print("New Title: ");
        String newTitle = scanner.nextLine();
        if (!newTitle.isEmpty()) {
            movie.setTitle(newTitle);
        }

        System.out.print("New Genre (film or series): ");
        String newGenre = scanner.nextLine();
        if (!newGenre.isEmpty()) {
            movie.setGenre(newGenre);
        }

        System.out.print("New Protagonist name: ");
        String newProtagonist = scanner.nextLine();
        if (!newProtagonist.isEmpty()) {
            movie.setProtagonists(newProtagonist);
        }

        System.out.print("Is it Appropriate for minors (true/false): ");
        String newAppropriate = scanner.nextLine();
        if (!newAppropriate.isEmpty()) {
            movie.setAppropriateForMinors(Boolean.parseBoolean(newAppropriate));
        }

        System.out.print("New Category (horror, comedy, etc.): ");
        String newCategory = scanner.nextLine();
        if (!newCategory.isEmpty()) {
            movie.setCategory(newCategory);
        }

        System.out.print("New Minimum Overall Assessment Grade (e.g. >= 4): ");
        String newMinOverallGrade = scanner.nextLine();
        if (!newMinOverallGrade.isEmpty()) {
            try {
                int minOverallGrade = Integer.parseInt(newMinOverallGrade);
                movie.setMinOverallGrade(minOverallGrade);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }

        return movie;
    }
}
