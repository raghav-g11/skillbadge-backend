package com.skillbadge.service;

import com.skillbadge.dao.StudentDAO;
import com.skillbadge.model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CodingBadgeAssignerTest {

    @Autowired
    private BadgeService badgeService;

    @Autowired
    private StudentDAO studentDAO;

    @Test
    public void testAssignCodingBadges() {
        // Insert test student
        Student s = new Student(100, "Unit Test Student", 21, "TestDept", 1, 2, 99, "Java", "Badminton");
        studentDAO.addStudent(s);

        // Should assign badge without exceptions
        assertDoesNotThrow(() -> badgeService.assignCodingBadges());
    }
}
