package ui;

import javax.swing.*;
import java.awt.*;
import core.DataService;
import core.RaidSimulator;
import core.DatabaseSimulator;
import core.LocalServer;

/**
 * ProfessorPanel - Swing panel to allow a professor to upload course materials.
 */
public class ProfessorPanel extends JPanel {
    private JTextField nameField, courseField, materialField;
    private JTextArea outputArea;

    private final DataService dataService = new DataService();
    private final RaidSimulator raid = new RaidSimulator();
    private final DatabaseSimulator db = new DatabaseSimulator();


    public void setTextFont(java.awt.Font font) {
        outputArea.setFont(font);
    }
    /**
     * Constructor for ProfessorPanel.
     * @param server LocalServer used to simulate client connections.
     */
    public ProfessorPanel(LocalServer server) {
        setLayout(new BorderLayout(10,10));

        JPanel form = new JPanel(new GridLayout(3, 2, 6, 6));
        form.add(new JLabel("Professor Name:"));
        nameField = new JTextField();
        form.add(nameField);

        form.add(new JLabel("Course Name:"));
        courseField = new JTextField();
        form.add(courseField);

        form.add(new JLabel("Material Name:"));
        materialField = new JTextField();
        form.add(materialField);

        JButton uploadBtn = new JButton("Upload Material");
        uploadBtn.addActionListener(e -> uploadMaterial(server));

        outputArea = new JTextArea(8, 40);
        outputArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(outputArea);

        JPanel top = new JPanel(new BorderLayout());
        top.add(form, BorderLayout.CENTER);
        top.add(uploadBtn, BorderLayout.SOUTH);

        add(top, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
    }

    /**
     * Handles uploading a material: saves via DataService, logs via DatabaseSimulator,
     * and triggers RAID mirroring.
     * @param server LocalServer instance to simulate professor connection.
     */
    private void uploadMaterial(LocalServer server) {
        String profName = nameField.getText().trim();
        String courseName = courseField.getText().trim();
        String material = materialField.getText().trim();

        if (profName.isEmpty() || courseName.isEmpty() || material.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields!");
            return;
        }

        server.connectClient(profName);
        dataService.saveMaterial(profName, courseName, material);
        db.insert("Materials", profName + ":" + courseName + ":" + material);
        raid.mirrorData("data/educloud_data.txt", "data/backup/educloud_backup.txt");

        outputArea.append("Uploaded: " + material + " by " + profName + " for course " + courseName + "\n");
        materialField.setText("");
    }
}
