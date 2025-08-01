package com.skillbadge.service;

import com.skillbadge.dao.StudentDAO;
import com.skillbadge.model.Student;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class StudentService {

    private final StudentDAO studentDAO;
    private static final Logger logger = Logger.getLogger(StudentService.class.getName());

    
    public StudentService(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    public List<Student> fetchAllStudents() {
        logger.info("Fetching all students from DB...");
        return studentDAO.getAllStudents();
    }

    public void addNewStudent(Student s) {
        logger.info("Adding new student: " + s.getName());
        studentDAO.addStudent(s);
    }

    public List<Student> fetchSortedStudentsBySubcategory(String subcategory) {
    List<String> validSubcategories = List.of("coding", "sport", "academic", "editing", "speaking", "teaching");

    if (!validSubcategories.contains(subcategory.toLowerCase())) {
        throw new IllegalArgumentException("Invalid subcategory. Allowed values: " + validSubcategories);
    }

    return studentDAO.getStudentsBySubcategory(subcategory.toLowerCase());
}



    public void printStudents(List<Student> students) {
        for (Student s : students) {
            logger.info("Student: " + s.getId() + " - " + s.getName() +
                    " | Coding Rank: " + s.getCodingRank() +
                    " | Sport Rank: " + s.getSportRank());
        }
    }
}
