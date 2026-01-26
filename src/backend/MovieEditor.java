package backend;

import backend.Movie;

import java.util.Scanner;

public class MovieEditor {
    public Movie editMovie(Movie movie) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Editing backend.Movie: " + movie.getTitle());

        String newTitle = scanner.nextLine();
        if (!newTitle.isEmpty()) {
            movie.setTitle(newTitle);
        }

        String newGenre = scanner.nextLine();
        if (!newGenre.isEmpty()) {
            movie.setGenre(newGenre);
        }

        String newProtagonist = scanner.nextLine();
        if (!newProtagonist.isEmpty()) {
            movie.setProtagonists(newProtagonist);
        }

        String newAppropriate = scanner.nextLine();
        if (!newAppropriate.isEmpty()) {
            movie.setAppropriateForMinors(Boolean.parseBoolean(newAppropriate));
        }

        String newCategory = scanner.nextLine();
        if (!newCategory.isEmpty()) {
            movie.setCategory(newCategory);
        }

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
