package  com.skillbadge.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileLogger {
    public static void logBadgeAssignment(String studentName, String badgeName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("badge_report.txt", true))) {
            writer.write("Badge '" + badgeName + "' assigned to " + studentName + "\n");
        } catch (IOException e) {
            System.out.println("Error writing to badge_report.txt: " + e.getMessage());
        }
    }
}
