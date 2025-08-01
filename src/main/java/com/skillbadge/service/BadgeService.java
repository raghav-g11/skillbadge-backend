package com.skillbadge.service;

import com.skillbadge.dao.BadgeDAO;
import com.skillbadge.dao.StudentDAO;
import com.skillbadge.exception.InvalidRankException;
import com.skillbadge.model.SBadge;
import com.skillbadge.model.Student;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class BadgeService {

    private static final Logger logger = Logger.getLogger(BadgeService.class.getName());

    private final BadgeDAO badgeDAO;
    private final StudentDAO studentDAO;

    
    public BadgeService(BadgeDAO badgeDAO, StudentDAO studentDAO) {
        this.badgeDAO = badgeDAO;
        this.studentDAO = studentDAO;
    }

    public void assignCodingBadges() {
        List<Student> codingStudents = studentDAO.getStudentsBySubcategory("coding");
        for (Student student : codingStudents) {
            assignBadge(student, "Technical", "Coding", student.getCodingRank());
        }
    }

    public void assignSportBadges() {
        List<Student> sportStudents = studentDAO.getStudentsBySubcategory("sport");
        for (Student student : sportStudents) {
            assignBadge(student, "ExtraCurricular", "Sports", student.getSportRank());
        }
    }

    public void assignAcademicBadges() {
        List<Student> academicStudents = studentDAO.getStudentsBySubcategory("academic");
        for (Student student : academicStudents) {
            assignBadge(student, "Academic", "Academics", student.getAcademicRank());
        }
    }

    public void assignEditingBadges() {
        List<Student> editingStudents = studentDAO.getStudentsBySubcategory("editing");
        for (Student student : editingStudents) {
            assignBadge(student, "Creativity", "Editing", student.getEditingRank());
        }
    }

    public void assignSpeakingBadges() {
        List<Student> speakingStudents = studentDAO.getStudentsBySubcategory("speaking");
        for (Student student : speakingStudents) {
            assignBadge(student, "Communication", "Speaking", student.getSpeakingRank());
        }
    }

    public void assignTeachingBadges() {
        List<Student> teachingStudents = studentDAO.getStudentsBySubcategory("teaching");
        for (Student student : teachingStudents) {
            assignBadge(student, "Mentoring", "Teaching", student.getTeachingRank());
        }
    }

    private void assignBadge(Student student, String category, String subcategory, int rank) {
        try {
            int newBadgeId = badgeDAO.generateNewBadgeId();
            SBadge badge = new SBadge(
                    newBadgeId,
                    student.getId(),
                    category,
                    subcategory,
                    rank,
                    student.getDepartment(),
                    student.getClassNo()
            );

            badgeDAO.saveBadge(badge);
            logger.info(" Badge assigned → " + subcategory + " to " + student.getName() +
                    " [Stars: " + badge.getStars() + "]");

        } catch (InvalidRankException e) {
            logger.log(Level.WARNING, "Invalid rank for student ID " + student.getId(), e);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error assigning badge for student ID " + student.getId(), e);
        }
    }
}
