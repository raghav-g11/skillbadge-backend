package com.skillbadge.dao;

import com.skillbadge.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class StudentDAO {

    @Autowired
    private DataSource dataSource;

    private static final Logger logger = Logger.getLogger(StudentDAO.class.getName());

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM student";

        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Student s = new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("department"),
                        rs.getInt("class_no"),
                        rs.getInt("coding_rank"),
                        rs.getInt("sport_rank"),
                        rs.getString("coding_language"),
                        rs.getString("sport")
                );
                students.add(s);
            }

        } catch (SQLException e) {
            logger.severe("Error fetching students: " + e.getMessage());
        }

        return students;
    }

    public void addStudent(Student s) {
        String sql = "INSERT INTO student (id, name, age, department, class_no, coding_rank, sport_rank, coding_language, sport) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, s.getId());
            stmt.setString(2, s.getName());
            stmt.setInt(3, s.getAge());
            stmt.setString(4, s.getDepartment());
            stmt.setInt(5, s.getClassNo());
            stmt.setInt(6, s.getCodingRank());
            stmt.setInt(7, s.getSportRank());
            stmt.setString(8, s.getCodingLanguage());
            stmt.setString(9, s.getSport());

            stmt.executeUpdate();
            logger.info("Student inserted successfully: " + s.getName());

        } catch (SQLException e) {
            logger.severe("Error inserting student: " + e.getMessage());
        }
    }

    public List<Student> getStudentsBySubcategory(String subcategory) {
    List<Student> students = new ArrayList<>();
    String sql;

    switch (subcategory.toLowerCase()) {
        case "coding":
        case "codingrank":
            sql = "SELECT * FROM student ORDER BY coding_rank ASC";
            break;
        case "sport":
        case "sportrank":
            sql = "SELECT * FROM student ORDER BY sport_rank ASC";
            break;
        case "academic":
        case "academicrank":
            sql = "SELECT * FROM student ORDER BY class_no ASC"; // or any logic you define
            break;
        case "editing":
        case "speaking":
        case "teaching":
            // Return all students without sorting for now
            sql = "SELECT * FROM student";
            break;
        default:
            logger.warning("Invalid subcategory for sorting: " + subcategory);
            return students;
    }

    try (Connection conn = dataSource.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            Student s = new Student(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("age"),
                    rs.getString("department"),
                    rs.getInt("class_no"),
                    rs.getInt("coding_rank"),
                    rs.getInt("sport_rank"),
                    rs.getString("coding_language"),
                    rs.getString("sport")
            );
            students.add(s);
        }

    } catch (SQLException e) {
        logger.severe("Error fetching students by subcategory: " + e.getMessage());
    }

    return students;
}

}
