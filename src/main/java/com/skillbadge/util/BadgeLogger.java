package  com.skillbadge.util;

import java.io.FileWriter;
import java.io.IOException;

public class BadgeLogger {
    private static final String FILE_NAME = "badge_report.txt";

    public static void log(String message) {
        try (FileWriter fw = new FileWriter(FILE_NAME, true)) {
            fw.write(message + "\n");
        } catch (IOException e) {
            System.out.println("Logger error: " + e.getMessage());
        }
    }
}
