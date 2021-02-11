package com.mit.jwt.controller;

import com.mit.jwt.model.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    private static final List<Student> STUDENTS = Arrays.asList(
      new Student(1,"iqbal tawakal"),
      new Student(2,"Udin Jajang"),
      new Student(3,"Ujang Ujang")
    );

    @GetMapping("{studentId}")
    public Student getStudent(@PathVariable("studentId") Integer studentId){
    return STUDENTS.stream().filter(s ->studentId.equals(s.getStudentId()))
            .findFirst()
            .orElseThrow(()->new IllegalStateException("Student "+ studentId+" Does not exist"));
    }

}
