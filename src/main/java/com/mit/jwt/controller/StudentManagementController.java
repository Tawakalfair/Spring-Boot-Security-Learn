package com.mit.jwt.controller;

import com.mit.jwt.model.Student;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/management/api/v1/students")
public class StudentManagementController {
    private static final List<Student> STUDENTS = Arrays.asList(
            new Student(1,"iqbal tawakal"),
            new Student(2,"Udin Jajang"),
            new Student(3,"Ujang Ujang")
    );

    @GetMapping
    public List<Student> getStudents(){
        return STUDENTS;
    }

    @PostMapping
    public void registerNewStudent(@RequestBody Student student){
        System.out.println(student);
    }

    @PutMapping(path="{studentId}")
    public void updateStudent(@PathVariable("studentId") Integer studentId, @RequestBody Student student){
        System.out.printf("%s %s",studentId,student);
    }

    @DeleteMapping(path="{studentId}")
    public void deleteStudent(@PathVariable Integer studentId){
        System.out.println(studentId);
    }

}
