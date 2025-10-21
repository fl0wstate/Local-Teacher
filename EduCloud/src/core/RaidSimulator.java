package core;

import java.io.*;

/**
 * RaidSimulator - simulates RAID mirroring by copying the data file to a backup location.
 */
public class RaidSimulator {
    /**
     * Mirror source file to backup file (simple copy).
     * @param source path to source file
     * @param backup path to backup file
     */
    public void mirrorData(String source, String backup) {
        try {
            File src = new File(source);
            File dest = new File(backup);
            dest.getParentFile().mkdirs();
            try (InputStream in = new FileInputStream(src);
                 OutputStream out = new FileOutputStream(dest)) {
                in.transferTo(out);
            }
            System.out.println("💾 Data mirrored to backup (RAID-1 simulation).");
        } catch (IOException e) {
            System.out.println("⚠️ RAID mirroring failed: " + e.getMessage());
        }
    }
}
