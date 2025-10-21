/**
 * EduCloudApp
 * Entry point for the EduCloud GUI application.
 * Launches the LocalServer and the Swing GUI (MainWindow).
 */
import javax.swing.SwingUtilities;
import core.LocalServer;
import ui.MainWindow;

public class EduCloudApp {
    public static void main(String[] args) {
        // Start local server simulation
        LocalServer server = new LocalServer();
        server.startServer();

        // Launch GUI on the Swing EDT
        SwingUtilities.invokeLater(() -> {
            MainWindow mw = new MainWindow(server);
            mw.setVisible(true);
        });
    }
}
