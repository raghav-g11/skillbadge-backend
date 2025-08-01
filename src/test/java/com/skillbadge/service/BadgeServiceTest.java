package com.skillbadge.service;

import com.skillbadge.dao.BadgeDAO;
import com.skillbadge.dao.StudentDAO;
import com.skillbadge.model.SBadge;
import com.skillbadge.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class BadgeServiceTest {

    private BadgeDAO badgeDAO;
    private StudentDAO studentDAO;
    private BadgeService badgeService;

    @BeforeEach
    void setUp() {
        badgeDAO = mock(BadgeDAO.class);
        studentDAO = mock(StudentDAO.class);
        badgeService = new BadgeService(badgeDAO, studentDAO);
    }

    @Test
    void testAssignCodingBadges() throws Exception {
        // Prepare test student
        Student student = new Student();
        student.setId(1);
        student.setName("Raghavan");
        student.setDepartment("AIDS");
        student.setClassNo(4);
        student.setCodingRank(2);

        List<Student> mockStudents = Arrays.asList(student);
        when(studentDAO.getStudentsBySubcategory("coding")).thenReturn(mockStudents);
        when(badgeDAO.generateNewBadgeId()).thenReturn(1001);

        // Call the method
        badgeService.assignCodingBadges();

        // Capture the badge object passed to saveBadge
        ArgumentCaptor<SBadge> badgeCaptor = ArgumentCaptor.forClass(SBadge.class);
        verify(badgeDAO, times(1)).saveBadge(badgeCaptor.capture());

        SBadge assignedBadge = badgeCaptor.getValue();
        assertEquals(1, assignedBadge.getStudentId());
        assertEquals("Technical", assignedBadge.getBadgeType());
        assertEquals("Coding", assignedBadge.getSubcategory());
        assertEquals(5, assignedBadge.getStars()); // since rank = 2
    }

    @Test
    void testAssignSportBadges() throws Exception {
        Student student = new Student();
        student.setId(2);
        student.setName("John");
        student.setDepartment("ECE");
        student.setClassNo(3);
        student.setSportRank(8);

        List<Student> mockStudents = Arrays.asList(student);
        when(studentDAO.getStudentsBySubcategory("sport")).thenReturn(mockStudents);
        when(badgeDAO.generateNewBadgeId()).thenReturn(1002);

        badgeService.assignSportBadges();

        ArgumentCaptor<SBadge> badgeCaptor = ArgumentCaptor.forClass(SBadge.class);
        verify(badgeDAO).saveBadge(badgeCaptor.capture());

        SBadge badge = badgeCaptor.getValue();
        assertEquals("ExtraCurricular", badge.getBadgeType());
        assertEquals("Sports", badge.getSubcategory());
        assertEquals(4, badge.getStars()); // Rank 8
    }
}
