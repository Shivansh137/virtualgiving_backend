package com.virtualgiving.controllers;

import com.virtualgiving.dto.LoginRequest;
import com.virtualgiving.entities.StudentEntity;
import com.virtualgiving.services.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/register")
    public StudentEntity registerStudent(@RequestBody StudentEntity student) {
        return studentService.registerStudent(student);
    }

    @GetMapping
    public List<StudentEntity> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentEntity> getStudentById(@PathVariable int id) {
        Optional<StudentEntity> student = studentService.getStudentById(id);
        return student.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<StudentEntity> updateStudent(@PathVariable int id, @RequestBody StudentEntity updatedStudent) {
        StudentEntity student = studentService.updateStudent(id, updatedStudent);
        return (student != null) ? ResponseEntity.ok(student) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable int id) {
        boolean isDeleted = studentService.deleteStudent(id);
        return isDeleted ? ResponseEntity.ok("Student deleted successfully") : ResponseEntity.notFound().build();
    }

    @PostMapping("/login")
    public ResponseEntity<StudentEntity> loginStudent(@RequestBody LoginRequest loginRequest) {
    String email = loginRequest.getEmail();
    String password = loginRequest.getPassword();

    StudentEntity student = studentService.loginStudent(email, password);
    return (student != null) ? ResponseEntity.ok(student) : ResponseEntity.status(401).build();
    }

}
