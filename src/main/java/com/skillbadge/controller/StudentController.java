package com.skillbadge.controller;

import com.skillbadge.model.Student;
import com.skillbadge.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.logging.Logger;

//import org.springframework.web.server.ResponseStatusException;
//import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private static final Logger logger = Logger.getLogger(StudentController.class.getName());

    @Autowired
    private StudentService studentService;

    // ✅ POST: Add new student
    @PostMapping("")
    public String addStudent(@RequestBody Student student) {
        studentService.addNewStudent(student);
        return "Student added successfully!";
    }

    // ✅ GET: All students
    @GetMapping("/all")
    public List<Student> getAllStudents() {
        return studentService.fetchAllStudents();
    }

    // ✅ GET: Sorted students by category
    @GetMapping("/sorted")
public List<Student> getSortedBySubcategory(@RequestParam String subcategory) {
    try {
        return studentService.fetchSortedStudentsBySubcategory(subcategory);
    } catch (IllegalArgumentException e) {
        logger.warning("Invalid subcategory: " + subcategory);
        throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                e.getMessage()
        );
    }
}


    // GET: Print to logs (optional use)
    @GetMapping("/log")
    public String logAllStudents() {
        List<Student> students = studentService.fetchAllStudents();
        studentService.printStudents(students);
        return "Printed students to logs.";
    }
}
