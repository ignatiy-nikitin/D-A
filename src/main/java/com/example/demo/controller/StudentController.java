package com.example.demo.controller;

import com.example.demo.dao.StudentJdbc;
import com.example.demo.model.Student;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    private final StudentJdbc studentJdbc;

    public StudentController(StudentJdbc studentJdbc) {
        this.studentJdbc = studentJdbc;
    }

    @PostMapping("/students")
    int newStudent(@RequestBody Student newStudent) {
        try {
            return studentJdbc.UpdateStudent(newStudent);
        } catch (Exception e) {
            return -1;
        }
    }

    @GetMapping("/students/{id}")
    public Student getStudent(@PathVariable int id) {
        return studentJdbc.get(id);
    }

    @GetMapping("/students/groups/{student_group_id}")
    public List<Student> getStudentByGroup(@PathVariable int student_group_id) {
        return studentJdbc.getStudentByGroup(student_group_id);
    }

    @GetMapping("/students/")
    public List<Student> getStudentByGroup() {
        return studentJdbc.getAll();
    }
}