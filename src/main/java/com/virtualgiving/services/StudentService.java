package com.virtualgiving.services;

import com.virtualgiving.entities.StudentEntity;
import com.virtualgiving.repositories.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public StudentEntity registerStudent(StudentEntity student) {
        return studentRepository.save(student);
    }

    public List<StudentEntity> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<StudentEntity> getStudentById(int id) {
        return studentRepository.findById((long) id);
    }

    public StudentEntity updateStudent(int id, StudentEntity updatedStudent) {
        Optional<StudentEntity> existingStudent = studentRepository.findById((long) id);
        if (existingStudent.isPresent()) {
            StudentEntity student = existingStudent.get();
            student.setName(updatedStudent.getName());
            student.setEmail(updatedStudent.getEmail());
            student.setContact_numbers(updatedStudent.getContact_numbers());
            student.setRole(updatedStudent.getRole());
            student.setProfile_picture(updatedStudent.getProfile_picture());
            student.setPassword(updatedStudent.getPassword());
            student.setStreet(updatedStudent.getStreet());
            student.setCity(updatedStudent.getCity());
            student.setState(updatedStudent.getState());
            return studentRepository.save(student);
        }
        return null;
    }

    public boolean deleteStudent(int id) {
        if (studentRepository.existsById((long) id)) {
            studentRepository.deleteById((long) id);
            return true;
        }
        return false;
    }

    public StudentEntity loginStudent(String email, String password) {
        List<StudentEntity> students = studentRepository.findAll();
        for (StudentEntity student : students) {
            if (student.getEmail().equals(email) && student.getPassword().equals(password)) {
                return student; // Login successful
            }
        }
        return null; // Login failed
    }
}
