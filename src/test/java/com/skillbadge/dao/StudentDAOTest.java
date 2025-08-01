package com.skillbadge.dao;

import com.skillbadge.model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class StudentDAOTest {

    @Autowired
    private StudentDAO studentDAO;

    @Test
    public void testFetchAllStudents() {
        List<Student> students = studentDAO.getAllStudents();
        assertNotNull(students);
        assertTrue(students.size() >= 0); // basic check
    }

    @Test
    public void testInsertStudent() {
        Student s = new Student(99, "Test User", 22, "Test Dept", 9, 5, 12, "Python", "Table Tennis");
        studentDAO.addStudent(s);

        List<Student> students = studentDAO.getAllStudents();
        boolean found = students.stream().anyMatch(st -> st.getId() == 99);
        assertTrue(found, "Inserted student with ID 99 should be found");
    }
}
