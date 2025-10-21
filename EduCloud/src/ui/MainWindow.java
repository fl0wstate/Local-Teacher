package ui;

import javax.swing.*;
import java.awt.*;
import core.LocalServer;

/**
 * MainWindow - main frame containing tabs for Professor and Student.
 */
public class MainWindow extends JFrame {
    /**
     * Create main window with two tabs.
     * @param server LocalServer instance used by panels.
     */
    public MainWindow(LocalServer server) {
        setTitle("EduCloud System");
        setSize(600, 480);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Professor", new ProfessorPanel(server));
        tabs.addTab("Student", new StudentPanel(server));

        add(tabs, BorderLayout.CENTER);
    }
}
