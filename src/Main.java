import backend.FilmPlatform;
import gui.LoginWindow;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        FilmPlatform filmPlatform = new FilmPlatform();
        filmPlatform.initialize();

        SwingUtilities.invokeLater(() -> new LoginWindow(filmPlatform));
    }
}
