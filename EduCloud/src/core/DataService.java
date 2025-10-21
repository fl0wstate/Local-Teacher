package core;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * DataService - middleware that saves and reads materials from a text file.
 * This acts as a simple persistence layer for Phase 1.
 */
public class DataService {
    private final String dataPath = "data/educloud_data.txt";

    /**
     * Save a material record as CSV: professor,course,material
     * @param professor professor name
     * @param course course name
     * @param material material name
     */
    public void saveMaterial(String professor, String course, String material) {
        try {
            File f = new File(dataPath);
            f.getParentFile().mkdirs();
            try (FileWriter fw = new FileWriter(f, true)) {
                fw.write(professor + "," + course + "," + material + "\n");
            }
            System.out.println("📤 Material saved successfully!");
        } catch (IOException e) {
            System.out.println("❌ Error saving material: " + e.getMessage());
        }
    }

    /**
     * Returns a list of material names for a specific course.
     * @param course course name
     * @return list of materials (may be empty)
     */
    public List<String> getMaterialsForCourse(String course) {
        List<String> materials = new ArrayList<>();
        try {
            File f = new File(dataPath);
            if (!f.exists()) return materials;
            try (Scanner reader = new Scanner(f)) {
                while (reader.hasNextLine()) {
                    String line = reader.nextLine();
                    String[] parts = line.split(",");
                    if (parts.length >= 3 && parts[1].equalsIgnoreCase(course)) {
                        materials.add(parts[2]);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error reading materials: " + e.getMessage());
        }
        return materials;
    }
}
