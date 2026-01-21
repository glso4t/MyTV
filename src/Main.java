import backend.Administrator;
import backend.Movie;
import backend.Subscriber;
import backend.User;
import backend.FilmPlatform;
import gui.LoginWindow;


import javax.swing.*;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        FilmPlatform filmPlatform = new FilmPlatform();
        filmPlatform.initialize();

        LoginWindow loginWindow = new LoginWindow(filmPlatform);


        Scanner scanner = new Scanner(System.in);

        User authenticatedUser = null;

        while (true) {
            System.out.println("Main Menu:");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int mainChoice = scanner.nextInt();
            scanner.nextLine();

            switch (mainChoice) {
                case 1:
                    System.out.print("Username: ");
                    String loginUsername = scanner.nextLine();
                    System.out.print("Password: ");
                    String loginPassword = scanner.nextLine();

                    authenticatedUser = filmPlatform.login(loginUsername, loginPassword);

                    if (authenticatedUser != null) {
                        System.out.println("Authentication successful!");
                        handleUserActions(authenticatedUser, filmPlatform, scanner);
                    } else {
                        System.out.println("Authentication failed. Invalid username or password.");
                    }
                    break;

                case 2:
                    boolean exitRegistration = false;

                    while (!exitRegistration) {
                        System.out.println("Registration Menu:");
                        System.out.println("1. Register as backend.Administrator");
                        System.out.println("2. Register as backend.Subscriber");
                        System.out.println("3. Back to Main Menu");
                        System.out.print("Enter your choice: ");
                        int registrationChoice = scanner.nextInt();
                        scanner.nextLine();

                        switch (registrationChoice) {
                            case 1:
                                System.out.print("Enter a username for the new backend.Administrator: ");
                                String adminUsername = scanner.nextLine();
                                System.out.print("Enter a password: ");
                                String adminPassword = scanner.nextLine();

                                Administrator newAdmin = new Administrator(adminUsername, adminPassword, filmPlatform.getFilmDatabase());
                                filmPlatform.getFilmDatabase().addAdministrator(newAdmin);
                                filmPlatform.getFilmDatabase().saveSubscribersToFile();
                                System.out.println("backend.Administrator registration successful!");
                                break;

                            case 2:
                                System.out.print("Enter a username for the new backend.Subscriber: ");
                                String subscriberUsername = scanner.nextLine();
                                System.out.print("Enter a password: ");
                                String subscriberPassword = scanner.nextLine();

                                Subscriber newSubscriber = new Subscriber(subscriberUsername, subscriberPassword, filmPlatform.getFilmDatabase());
                                filmPlatform.getFilmDatabase().addSubscriber(newSubscriber);
                                filmPlatform.getFilmDatabase().saveSubscribersToFile();
                                System.out.println("backend.Subscriber registration successful!");
                                break;

                            case 3:
                                System.out.println("Exiting registration. Returning to the Main Menu.");
                                exitRegistration = true;
                                break;

                            default:
                                System.out.println("Invalid choice. Please enter a valid option.");
                                break;
                        }
                    }
                    break;

                case 3:
                    System.out.println("Exiting the program. Goodbye!");
                    loginWindow.getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }
        }

    }

    private static void handleUserActions(User user, FilmPlatform filmPlatform, Scanner scanner) {
        while (true) {
            if (user instanceof Administrator) {
                // backend.Administrator menu
                System.out.println("backend.Administrator Menu:");
                System.out.println("1. Add backend.Movie");
                System.out.println("2. Edit backend.Movie");
                System.out.println("3. Delete backend.Movie");
                System.out.println("4. Logout");
                System.out.print("Enter your choice: ");
                int adminChoice = scanner.nextInt();
                scanner.nextLine();

                switch (adminChoice) {
                    case 1:
                        ((Administrator) user).addMovie();
                        break;

                    case 2:
                        ((Administrator) user).editMovie();
                        break;

                    case 3:

                        filmPlatform.getFilmDatabase().displayMovies();

                        System.out.print("Enter the ID of the movie to delete: ");
                        int movieIdToDelete = scanner.nextInt();
                        scanner.nextLine();

                        Movie movieToDelete = filmPlatform.getFilmDatabase().getMovieById(movieIdToDelete);
                        if (movieToDelete != null) {
                            ((Administrator) user).deleteMovie(movieToDelete);
                        } else {
                            System.out.println("backend.Movie not found.");
                        }
                        break;

                    case 4:
                        System.out.println("Logging out. Goodbye!");
                        return;

                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                        break;
                }
            } else if (user instanceof Subscriber) {
                // backend.Subscriber menu
                System.out.println("backend.Subscriber Menu:");
                System.out.println("1. Search Movies");
                System.out.println("2. Rate backend.Movie");
                System.out.println("3. Add to Favorites");
                System.out.println("4. Remove from Favorites");
                System.out.println("5. View Favorites");
                System.out.println("6. Logout");
                System.out.print("Enter your choice: ");
                int subscriberChoice = scanner.nextInt();
                scanner.nextLine();

                switch (subscriberChoice) {
                    case 1:
                        System.out.print("Enter a keyword to search for movies: ");
                        String keyword = scanner.nextLine();
                        List<Movie> searchResults = ((Subscriber) user).searchMovies(keyword);
                        break;

                    case 2:
                        System.out.print("Enter the ID of the movie to rate: ");
                        int movieId = scanner.nextInt();
                        scanner.nextLine();
                        ((Subscriber) user).rateMovie(filmPlatform.getFilmDatabase().getMovieById(movieId));
                        break;

                    case 3:
                        System.out.print("Enter the ID of the movie to add to favorites: ");
                        int addFavoriteId = scanner.nextInt();
                        scanner.nextLine();
                        ((Subscriber) user).addToFavorites(filmPlatform.getFilmDatabase().getMovieById(addFavoriteId));
                        break;

                    case 4:
                        System.out.print("Enter the ID of the movie to remove from favorites: ");
                        int removeFavoriteId = scanner.nextInt();
                        scanner.nextLine();
                        ((Subscriber) user).removeFromFavorites(filmPlatform.getFilmDatabase().getMovieById(removeFavoriteId), filmPlatform.getFilmDatabase());
                        break;

                    case 5:
                        ((Subscriber) user).viewFavorites();
                        break;

                    case 6:
                        System.out.println("Logging out. Goodbye!");
                        return;

                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                        break;
                }
            }
        }
    }
}
