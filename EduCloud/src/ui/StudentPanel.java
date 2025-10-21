package ui;

import javax.swing.*;
import java.awt.*;
import core.DataService;
import core.LlmAssistant;
import core.LocalServer;

/**
 * StudentPanel - Swing panel to allow students to view materials and get guidance.
 */
public class StudentPanel extends JPanel {
    private JTextField studentNameField, courseField;
    private JTextArea outputArea;

    private final DataService dataService = new DataService();
    // private final LlmAssistant ai = new LlmAssistant();


    public void setTextFont(java.awt.Font font) {
        outputArea.setFont(font);
    }
    /**
     * Constructor for StudentPanel.
     * @param server LocalServer instance to simulate student connection.
     */
    public StudentPanel(LocalServer server) {
        setLayout(new BorderLayout(10,10));

        JPanel form = new JPanel(new GridLayout(2, 2, 6, 6));
        form.add(new JLabel("Student Name:"));
        studentNameField = new JTextField();
        form.add(studentNameField);

        form.add(new JLabel("Course Name:"));
        courseField = new JTextField();
        form.add(courseField);

        JButton viewBtn = new JButton("View Materials");
        viewBtn.addActionListener(e -> viewMaterials(server));

        outputArea = new JTextArea(12, 40);
        outputArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(outputArea);

        JPanel top = new JPanel(new BorderLayout());
        top.add(form, BorderLayout.CENTER);
        top.add(viewBtn, BorderLayout.SOUTH);

        add(top, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
    }

    /**
     * Reads materials for the course and shows AI guidance.
     * @param server LocalServer instance to simulate student connection.
     */
    private void viewMaterials(LocalServer server) {
        String studentName = studentNameField.getText().trim();
        String courseName = courseField.getText().trim();

        if (studentName.isEmpty() || courseName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields!");
            return;
        }

        server.connectClient(studentName);
        outputArea.append("\n=== Materials for " + courseName + " ===\n");
        java.util.List<String> materials = dataService.getMaterialsForCourse(courseName);
        if (materials.isEmpty()) {
            outputArea.append("No materials found for this course.\n");
        } else {
            for (String m : materials) {
                outputArea.append(" - " + m + "\n");
            }
        }
        // Simulated AI guidance appended to text area
        // String guidance = ai.generateGuidance(courseName, materials);
        outputArea.append("\n" + guidance + "\n");
    }
}
