package ui;

import javax.swing.*;
import java.awt.*;
import core.LocalServer;

/**
 * MainWindow - main frame containing tabs for Professor and Student.
 */
public class MainWindow extends JFrame {

    // Toolbar components
    private JToolBar toolbar;
    private JButton zoomInBtn;
    private JButton zoomOutBtn;

    // Shared font size state
    private final float[] fontSize = {14f};

    /**
     * Create main window with toolbar and tabs.
     * @param server LocalServer instance used by panels.
     */
    public MainWindow(LocalServer server) {
        setTitle("EduCloud System");
        setSize(600, 480);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Initialize toolbar and zoom buttons
        toolbar = new JToolBar();
        zoomInBtn = new JButton("Zoom In +");
        zoomOutBtn = new JButton("Zoom Out -");

        toolbar.add(zoomInBtn);
        toolbar.add(zoomOutBtn);
        add(toolbar, BorderLayout.NORTH);

        // Tabs
        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Professor", new ProfessorPanel(server));
        tabs.addTab("Student", new StudentPanel(server));
        add(tabs, BorderLayout.CENTER);

        // Action listeners for zoom buttons
        zoomInBtn.addActionListener(e -> {
            fontSize[0] += 2f;
            updateFontSize(fontSize[0]);
        });

        zoomOutBtn.addActionListener(e -> {
            fontSize[0] = Math.max(10f, fontSize[0] - 2f);
            updateFontSize(fontSize[0]);
        });
    }

    /**
     * Updates font size for all text areas in both tabs.
     */
    private void updateFontSize(float size) {
        Font newFont = new Font("SansSerif", Font.PLAIN, (int) size);
        for (Component comp : getContentPane().getComponents()) {
            if (comp instanceof JTabbedPane) {
                JTabbedPane tabs = (JTabbedPane) comp;
                for (int i = 0; i < tabs.getTabCount(); i++) {
                    Component panel = tabs.getComponentAt(i);
                    if (panel instanceof ui.ProfessorPanel) {
                        ((ui.ProfessorPanel) panel).setTextFont(newFont);
                    } else if (panel instanceof ui.StudentPanel) {
                        ((ui.StudentPanel) panel).setTextFont(newFont);
                    }
                }
            }
        }
    }
}
